package com.baedal.store.application.service;

import com.baedal.store.application.command.AddStoreCommand;
import com.baedal.store.application.command.DeliveryInfoCommand;
import com.baedal.store.application.command.GetStoreDetailCommand;
import com.baedal.store.application.command.SearchNameCommand.Request;
import com.baedal.store.application.command.SearchNameCommand.Response;
import com.baedal.store.application.command.ValidateOrderInfoCommand;
import com.baedal.store.application.mapper.StoreApplicationMapper;
import com.baedal.store.application.port.in.StoreUseCase;
import com.baedal.store.application.port.out.MessageSenderPort;
import com.baedal.store.application.port.out.ProductPort;
import com.baedal.store.application.port.out.ReviewPort;
import com.baedal.store.application.port.out.StoreRepositoryPort;
import com.baedal.store.application.port.out.StoreSearchRepositoryPort;
import com.baedal.store.domain.business.StoreValidator;
import com.baedal.store.domain.business.VirtualThreadManager;
import com.baedal.store.domain.model.ProductInfo;
import com.baedal.store.domain.model.Store;
import com.baedal.store.domain.model.StoreReviewSummary;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreService implements StoreUseCase {

  private final VirtualThreadManager virtualThreadManager = new VirtualThreadManager();

  private final StoreRepositoryPort storeRepositoryPort;
  private final StoreApplicationMapper mapper;
  private final StoreValidator validator;
  private final MessageSenderPort messageSenderPort;
  private final ReviewPort reviewPort;
  private final ProductPort productPort;
  private final StoreSearchRepositoryPort storeSearchRepositoryPort;

  @Transactional
  public void addStore(AddStoreCommand.Request req) {

    // 영업 시작 시간이 영업 종료 시간보다 작은지 확인
    validator.validateOpenTimeBeforeCloseTime(req.getOpenTime(), req.getCloseTime());

    Store store = mapper.addStoreToDomain(req);
    storeRepositoryPort.save(store);
  }

  @Transactional(readOnly = true)
  public DeliveryInfoCommand.Response getDeliveryInfo(DeliveryInfoCommand.Request req) {

    Store store = storeRepositoryPort.findById(req.getStoreId());
    return mapper.getDeliveryInfoToResponse(store);
  }

  @Transactional(readOnly = true)
  public void validateStoreOrderInfo(ValidateOrderInfoCommand.Request req) {

    try {
      Store store = storeRepositoryPort.findById(req.getStoreId());
      validator.validateDeliveryAmount(store, req.getDeliveryAmount());
      messageSenderPort.sendSuccessOrderValidate(req.getOrderTransactionId());
    } catch (Exception e) {
      messageSenderPort.sendFailOrderValidate(req.getOrderTransactionId(), e.getMessage());
    }
  }

  @Transactional(readOnly = true)
  public GetStoreDetailCommand getStoreDetailV0(Long storeId) {
    Future<Store> futureStore = virtualThreadManager.submitAsync(() ->
        storeRepositoryPort.findById(storeId)
    );

    Future<List<StoreReviewSummary>> futureTop10Reviews = virtualThreadManager.submitAsync(() ->
        reviewPort.getTop10Reviews(storeId)
    );

    Future<Double> futureAverageScore = virtualThreadManager.submitAsync(() ->
        reviewPort.getAverageScore(storeId)
    );

    Future<List<ProductInfo>> futureProducts = virtualThreadManager.submitAsync(() ->
        productPort.findProductsByStoreId(storeId)
    );

    Store store = virtualThreadManager.extractResult(futureStore);
    List<StoreReviewSummary> top10Reviews = virtualThreadManager.extractResult(futureTop10Reviews);
    Double averageScore = virtualThreadManager.extractResult(futureAverageScore);
    List<ProductInfo> products = virtualThreadManager.extractResult(futureProducts);

    return mapper.getStoreDetailToResponse(store, top10Reviews, averageScore, products);
  }

  @Transactional(readOnly = true)
  public GetStoreDetailCommand getStoreDetail(Long storeId) {
    try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
      Subtask<Store> store = scope.fork(() ->
          storeRepositoryPort.findById(storeId));

      Subtask<List<StoreReviewSummary>> top10Reviews = scope.fork(
          () -> reviewPort.getTop10Reviews(storeId));

      Subtask<Double> averageScore = scope.fork(() ->
          reviewPort.getAverageScore(storeId));

      Subtask<List<ProductInfo>> products = scope.fork(() ->
          productPort.findProductsByStoreId(storeId));

      scope.join()
          .throwIfFailed();

      return mapper.getStoreDetailToResponse(
          store.get(),
          top10Reviews.get(),
          averageScore.get(),
          products.get()
      );
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      log.debug("인터럽트 발생: [{}]", e.toString());
      throw new RuntimeException("스레드 인터럽트 발생", e);
    } catch (ExecutionException e) {
      Throwable cause = e.getCause();
      log.debug("비동기 작업 중 예외 발생: [{}]", cause.toString());
      throw new RuntimeException("비동기 작업 실패", cause);
    }
  }

  @Override
  public List<Response> getSearchName(Request req) {
    List<Store> store = storeSearchRepositoryPort.findByNameContaining(req.getName());
    return mapper.searchNameToResponse(store);
  }
}
