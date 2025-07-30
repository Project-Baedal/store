package com.baedal.store.java.application.port.out;

import com.baedal.store.java.domain.model.Order;

public interface OrderPort {

  Order findById(Long orderId);
}
