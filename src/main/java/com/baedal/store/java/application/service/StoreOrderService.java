package com.baedal.store.java.application.service;

import com.baedal.store.java.application.port.out.OrderCommandPort;
import com.baedal.store.java.application.port.out.OrderPort;
import com.baedal.store.java.application.port.out.StoreRepositoryPort;
import com.baedal.store.java.domain.business.OrderApprovalValidator;
import com.baedal.store.java.domain.model.Order;
import com.baedal.store.java.domain.model.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreOrderService {

  private final OrderPort orderPort;

  private final OrderCommandPort orderCommandPort;

  private final StoreRepositoryPort storeRepositoryPort;

  private final OrderApprovalValidator validator;

  @Transactional(readOnly = true)
  public void acceptOrder(Long storeId, Long orderId, Long userId) {
    Store store = storeRepositoryPort.findById(storeId);
    Order order = orderPort.findById(orderId);

    // 검증
    validator.validateOrder(store, order, userId);

    // 주문 승인/거절 위임
    orderCommandPort.accept(orderId);
  }

  @Transactional(readOnly = true)
  public void denyOrder(Long storeId, Long orderId, Long userId) {
    Store store = storeRepositoryPort.findById(storeId);
    Order order = orderPort.findById(orderId);

    validator.validateOrder(store, order, userId);

    orderCommandPort.deny(orderId);
  }
}
