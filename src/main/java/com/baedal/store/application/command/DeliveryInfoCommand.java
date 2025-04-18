package com.baedal.store.application.command;

import lombok.Builder;
import lombok.Getter;

public class DeliveryInfoCommand {

  @Getter
  @Builder
  public static class Request {
    private Long storeId;
  }

  @Getter
  @Builder
  public static class Response {
    private String storeName;
    private int deliveryAmount;
  }

}
