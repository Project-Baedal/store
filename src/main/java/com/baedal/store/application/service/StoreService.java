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
import com.baedal.store.util.StructuredTaskUtil;
import java.util.List;
import java.util.concurrent.StructuredTaskScope.ShutdownOnFailure;
import java.util.concurrent.StructuredTaskScope.Subtask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.function.ThrowingFunction;

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
  public GetStoreDetailCommand getStoreDetail(Long storeId) {
    ThrowingFunction<ShutdownOnFailure, GetStoreDetailCommand> function = (ShutdownOnFailure scope) -> {
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

      GetStoreDetailCommand result = mapper.getStoreDetailToResponse(
          store.get(),
          top10Reviews.get(),
          averageScore.get(),
          products.get()
      );

      return result;
    };

    return StructuredTaskUtil.shutdownOnFailure(function);
  }

  @Override
  public List<Response> getSearchName(Request req) {
    List<Store> store = storeSearchRepositoryPort.findByNameContaining(req.getName());
    return mapper.searchNameToResponse(store);
  }
}
