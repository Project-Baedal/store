package com.baedal.store.adapter.out.web;

import com.baedal.store.adapter.out.web.client.OrderClient;
import com.baedal.store.adapter.out.web.mapper.OrderWebMapper;
import com.baedal.store.adapter.out.web.response.GetOrderResponse;
import com.baedal.store.application.port.out.OrderPort;
import com.baedal.store.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderClientAdapter implements OrderPort {

  private final OrderClient client;

  private final OrderWebMapper mapper;

  public Order findById(Long orderId) {
    GetOrderResponse order = client.getOrder(orderId).getBody();
    return mapper.toDomain(order);
  }
}
