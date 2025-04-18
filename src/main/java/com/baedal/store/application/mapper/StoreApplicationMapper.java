package com.baedal.store.application.mapper;

import com.baedal.store.application.command.AddStoreCommand;
import com.baedal.store.application.command.DeliveryInfoCommand;
import com.baedal.store.domain.model.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StoreApplicationMapper {

  Store addStoreToDomain(AddStoreCommand.Request req);

  @Mapping(target = "storeName", source = "name")
  DeliveryInfoCommand.Response getDeliveryInfoToResponse(Store store);
}
