package com.baedal.store.adapter.out.messaging.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SendOrderValidate {

  private String orderTransactionId;
  private boolean status;
  private String errorMessage;


}
