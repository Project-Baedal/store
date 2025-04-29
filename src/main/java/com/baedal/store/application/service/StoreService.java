package com.baedal.store.application.service;

import com.baedal.store.application.command.AddStoreCommand;
import com.baedal.store.application.command.DeliveryInfoCommand;
import com.baedal.store.application.command.GetStoreDetailCommand;
import com.baedal.store.application.command.ValidateOrderInfoCommand;
import com.baedal.store.application.mapper.StoreApplicationMapper;
import com.baedal.store.application.port.in.StoreUseCase;
import com.baedal.store.application.port.out.MessageSenderPort;
import com.baedal.store.application.port.out.ProductPort;
import com.baedal.store.application.port.out.ReviewPort;
import com.baedal.store.application.port.out.StoreRepositoryPort;
import com.baedal.store.domain.business.StoreValidator;
import com.baedal.store.domain.business.VirtualThreadManager;
import com.baedal.store.domain.model.ProductInfo;
import com.baedal.store.domain.model.Store;
import com.baedal.store.domain.model.StoreReviewSummary;
import java.util.List;
import java.util.concurrent.Future;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService implements StoreUseCase {

  private final VirtualThreadManager virtualThreadManager = new VirtualThreadManager();

  private final StoreRepositoryPort storeRepositoryPort;
  private final StoreApplicationMapper mapper;
  private final StoreValidator validator;
  private final MessageSenderPort messageSenderPort;
  private final ReviewPort reviewPort;
  private final ProductPort productPort;

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
  public GetStoreDetailCommand getStoreDetail(Long storeId) {
    Future<Store> futureStore = virtualThreadManager.submitAsync(() ->
        storeRepositoryPort.findById(storeId)
    );

    Future<List<StoreReviewSummary>> futureTop10Reviews = virtualThreadManager.submitAsync(() ->
        reviewPort.getTop10Reviews(storeId)
    );

    Future<Double> futureAverageScore = virtualThreadManager.submitAsync(() ->
        reviewPort.getAverageScore(storeId)
    );

    CompletableFuture.allOf(
        storeFuture,
        top10ReviewsFuture,
        averageScoreFuture);

    try {
      Store store = storeFuture.get();
      List<StoreReviewSummary> top10Reviews = top10ReviewsFuture.get();
      double averageScore = averageScoreFuture.get();
    Store store = virtualThreadManager.extractResult(futureStore);
    List<StoreReviewSummary> top10Reviews = virtualThreadManager.extractResult(futureTop10Reviews);
    Double averageScore = virtualThreadManager.extractResult(futureAverageScore);

      return mapper.getStoreDetailToResponse(store, top10Reviews, averageScore);
    } catch (ExecutionException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
