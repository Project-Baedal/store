package com.baedal.store.adapter.out.messaging.adapter;

import com.baedal.store.adapter.out.messaging.sender.KafkaSender;
import com.baedal.store.application.port.out.OrderCommandPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMessageAdapter implements OrderCommandPort {

  private final KafkaSender kafkaSender;

  public void accept(Long orderId) {
    kafkaSender.sendMessage("order.accept", orderId.toString(), orderId);
  }

  public void deny(Long orderId) {
    kafkaSender.sendMessage("order.deny", orderId.toString(), orderId);
  }
}
