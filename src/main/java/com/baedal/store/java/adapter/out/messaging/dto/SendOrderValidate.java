package com.baedal.store.java.adapter.out.messaging.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SendOrderValidate {

  private String domain;
  private boolean status;
  private String message;

  @Builder
  public SendOrderValidate(String message, boolean status) {
    this.message = message;
    this.status = status;
    this.domain = "STORE";
  }
}
