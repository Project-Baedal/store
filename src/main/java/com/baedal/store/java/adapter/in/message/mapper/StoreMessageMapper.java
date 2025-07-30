package com.baedal.store.java.adapter.in.message.mapper;

import com.baedal.store.java.adapter.in.message.dto.ValidateOrderInfoRequest;
import com.baedal.store.java.application.command.AddStoreCommand;
import com.baedal.store.java.adapter.in.message.dto.AddStoreRequest;
import com.baedal.store.java.application.command.ValidateOrderInfoCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreMessageMapper {

  // 매장 추가
  AddStoreCommand.Request addStoreToCommand(Long ownerId, AddStoreRequest addStoreRequest);

  // 주문 검증
  ValidateOrderInfoCommand.Request validateOrderInfoToCommand(ValidateOrderInfoRequest req);
}
