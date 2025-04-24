package com.baedal.store.adapter.in.message.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ValidateOrderInfoRequest {

  @Schema(description = "주문 고유 ID")
  private String orderTransactionId;

  @Schema(description = "매장 ID")
  private Long storeId;

  @Schema(description = "배달비")
  private int deliveryAmount;
}
