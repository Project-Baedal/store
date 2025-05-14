package com.baedal.store.domain.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Order {

  private Long id;

  private Long storeId;

  private OrderStatus orderStatus;
}
