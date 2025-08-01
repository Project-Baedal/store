package com.baedal.store.java.application.service;

import com.baedal.store.java.application.command.AddStoreCommand;
import com.baedal.store.java.application.command.DeliveryInfoCommand;
import com.baedal.store.java.application.command.SearchNameCommand.Request;
import com.baedal.store.java.application.command.SearchNameCommand.Response;
import com.baedal.store.java.application.command.ValidateOrderInfoCommand;
import com.baedal.store.java.application.mapper.StoreApplicationMapper;
import com.baedal.store.java.application.port.in.StoreUseCase;
import com.baedal.store.java.application.port.out.MessageSenderPort;
import com.baedal.store.java.application.port.out.StoreSearchRepositoryPort;
import com.baedal.store.java.domain.business.StoreValidator;
import com.baedal.store.kotlin.application.port.out.StoreRepositoryPort;
import com.baedal.store.kotlin.domain.model.Store;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService implements StoreUseCase {

  private final StoreRepositoryPort storeRepositoryPort;
  private final StoreApplicationMapper mapper;
  private final StoreValidator validator;
  private final MessageSenderPort messageSenderPort;
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

  @Override
  public List<Response> getSearchName(Request req) {
    List<Store> store = storeSearchRepositoryPort.findByNameContaining(req.getName());
    return mapper.searchNameToResponse(store);
  }
}
