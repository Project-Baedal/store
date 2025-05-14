package com.baedal.store.domain.business;

import com.baedal.store.domain.model.Order;
import com.baedal.store.domain.model.OrderStatus;
import com.baedal.store.domain.model.Store;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderApprovalValidatorTest {

  OrderApprovalValidator validator = new OrderApprovalValidator();

  private final long storeId = 1L;
  private final long ownerId = 2L;
  private final long orderId = 3L;

  Store createStore(long storeId, long ownerId) {
    return Store.builder()
        .id(storeId)
        .ownerId(ownerId)
        .build();
  }

  Order createOrder(long orderId, long storeId, OrderStatus status) {
    return Order.builder()
        .id(orderId)
        .storeId(storeId)
        .orderStatus(status)
        .build();
  }

  @Test
  void validateOrder_정상조건이면_예외없음() {
    Store store = createStore(storeId, ownerId);
    Order order = createOrder(orderId, storeId, OrderStatus.PENDING);

    assertThatCode(() -> validator.validateOrder(store, order, ownerId))
        .doesNotThrowAnyException();
  }

  @Test
  void validateOrder_주인이아닌경우_예외발생() {
    Store store = createStore(storeId, ownerId);
    Order order = createOrder(orderId, storeId, OrderStatus.PENDING);

    assertThatThrownBy(() -> validator.validateOrder(store, order, 999L))
        .isInstanceOf(RuntimeException.class)
        .hasMessageContaining("매장의 주인만");
  }

  @Test
  void validateOrder_주문이_해당매장의것이_아닌경우_예외발생() {
    Store store = createStore(storeId, ownerId);
    Order order = createOrder(orderId, 999L, OrderStatus.PENDING);

    assertThatThrownBy(() -> validator.validateOrder(store, order, ownerId))
        .isInstanceOf(RuntimeException.class)
        .hasMessageContaining("매장의 주문만");
  }

  @Test
  void validateOrder_주문상태가_Pending이_아닌경우_예외발생() {
    Store store = createStore(storeId, ownerId);
    Order order = createOrder(orderId, storeId, OrderStatus.COMPLETED);

    assertThatThrownBy(() -> validator.validateOrder(store, order, ownerId))
        .isInstanceOf(RuntimeException.class)
        .hasMessageContaining("대기상태인 주문만");
  }

}