package com.baedal.store.adapter.out.messaging.adapter;

import com.baedal.store.adapter.out.messaging.dto.SendOrderValidate;
import com.baedal.store.adapter.out.messaging.mapper.StoreOutMessageMapper;
import com.baedal.store.adapter.out.messaging.sender.KafkaSender;
import com.baedal.store.application.port.out.MessageSenderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

// note. 이후 Topic 은 SecretKey 로 관리, Key/Value 는 암호화 고려해보면 좋을 것같습니다.
@Component
@RequiredArgsConstructor
public class MessageSenderAdapter implements MessageSenderPort {

  private final KafkaSender kafkaSender;
  private final StoreOutMessageMapper storeMapper;

  @Override
  public void sendSuccessOrderValidate(String orderTransactionId) {
    SendOrderValidate req = storeMapper.orderValidate(true, null);
    kafkaSender.sendMessage("order.orderValidate", orderTransactionId, req);
  }

  @Override
  public void sendFailOrderValidate(String orderTransactionId, String errorMessage) {
    SendOrderValidate req = storeMapper.orderValidate(false, errorMessage);
    kafkaSender.sendMessage("order.orderValidate", orderTransactionId, req);
  }
}
