package com.baedal.store.application.command;

import lombok.Builder;
import lombok.Getter;

public class ValidateOrderInfoCommand {

  @Getter
  @Builder
  public static class Request {
    private String orderTransactionId;
    private Long storeId;
    private int deliveryAmount;
  }
}
