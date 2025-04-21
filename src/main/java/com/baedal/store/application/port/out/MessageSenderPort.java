package com.baedal.store.application.port.out;

public interface MessageSenderPort {

  void sendSuccessOrderValidate(String orderTransactionId);

  void sendFailOrderValidate(String orderTransactionId, String errorMessage);
}
