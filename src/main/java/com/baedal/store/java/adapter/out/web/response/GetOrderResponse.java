package com.baedal.store.java.adapter.out.web.response;

import com.baedal.store.java.domain.model.OrderStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetOrderResponse {

  private Long orderId;

  private Long storeId;

  private OrderStatus orderStatus;
}
