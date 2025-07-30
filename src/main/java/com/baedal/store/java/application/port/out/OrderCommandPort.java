package com.baedal.store.java.application.port.out;

public interface OrderCommandPort {

  void accept(Long orderId);

  void deny(Long orderId);
}
