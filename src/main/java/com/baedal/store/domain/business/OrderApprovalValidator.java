package com.baedal.store.domain.business;

import com.baedal.store.domain.model.Order;
import com.baedal.store.domain.model.OrderStatus;
import com.baedal.store.domain.model.Store;
import org.springframework.stereotype.Component;

@Component
public class OrderApprovalValidator {

  public void validateOrder(Store store, Order order, Long userId) {
    validateStoreOwner(store, userId);
    validateOrdersStore(store, order);
    validateOrderStatus(order);
  }

  private void validateStoreOwner(Store store, Long userId) {
    //  - 검증1. store가 owner의 것 인지
    if (!store.getOwnerId().equals(userId)) {
      throw new RuntimeException("매장의 주인만 접근 가능합니다.");
    }
  }

  private void validateOrdersStore(Store store, Order order) {
    //  - 검증2. order가 store의 것 인지
    if (!order.getStoreId().equals(store.getId())) {
      throw new RuntimeException("매장의 주문만 접근 가능합니다.");
    }
  }

  private void validateOrderStatus(Order order) {
    //  - 검증3. order가 PENDING 상태 인지
    if (!order.getOrderStatus().equals(OrderStatus.PENDING)) {
      throw new RuntimeException("대기상태인 주문만 승인할 수 있습니다.");
    }
  }
}
