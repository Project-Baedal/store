package com.baedal.store.adapter.in.web.mapper;

import com.baedal.store.application.command.AddStoreCommand;
import com.baedal.store.application.command.AddStoreRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreWebMapper {

  // 매장 추가
  AddStoreCommand.Request addStoreToCommand(Long ownerId, AddStoreRequest addStoreRequest);
}
