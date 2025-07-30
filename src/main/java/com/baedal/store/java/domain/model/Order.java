package com.baedal.store.java.domain.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Order {

  private Long id;

  private Long storeId;

  private OrderStatus orderStatus;
}
