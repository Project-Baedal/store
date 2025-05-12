package com.baedal.store.application.port.out;

import com.baedal.store.domain.model.Order;

public interface OrderPort {

  Order findById(Long orderId);
}
