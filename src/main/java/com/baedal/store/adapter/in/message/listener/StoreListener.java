package com.baedal.store.adapter.in.message.listener;

import com.baedal.store.adapter.in.message.mapper.StoreMessageMapper;
import com.baedal.store.application.command.AddStoreCommand;
import com.baedal.store.adapter.in.message.dto.AddStoreRequest;
import com.baedal.store.application.port.in.StoreUseCase;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.baedal.store.util.Converter;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StoreListener {

  private final Converter converter;
  private final StoreMessageMapper mapper;
  private final StoreUseCase storeUseCase;

  @KafkaListener(topics = "store.addStore", groupId = "owner-group")
  public void addStore(ConsumerRecord<String, String> record) {
    Long ownerId = Long.parseLong(record.key());
    AddStoreRequest req = converter.jsonToDto(record.value(), AddStoreRequest.class);
    AddStoreCommand.Request command = mapper.addStoreToCommand(ownerId, req);
    storeUseCase.addStore(command);
  }

}
