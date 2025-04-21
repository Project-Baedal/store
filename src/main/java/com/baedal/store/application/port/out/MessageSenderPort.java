package com.baedal.store.application.port.out;

public interface MessageSenderPort {

  void sendSuccessOrderValidate();

  void sendFailOrderValidate(String errorMessage);
}
