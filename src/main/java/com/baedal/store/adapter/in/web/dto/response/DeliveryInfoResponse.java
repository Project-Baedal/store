package com.baedal.store.adapter.in.web.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeliveryInfoResponse {

  private String storeName;
  private int deliveryAmount;

}
