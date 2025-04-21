package com.baedal.store.adapter.out.messaging.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SendOrderValidate {

  private String domain;
  private boolean status;
  private String errorMessage;

  @Builder
  public SendOrderValidate(String errorMessage, boolean status) {
    this.errorMessage = errorMessage;
    this.status = status;
    this.domain = "STORE";
  }
}
