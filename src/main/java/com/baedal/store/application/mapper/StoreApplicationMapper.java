package com.baedal.store.application.mapper;

import com.baedal.store.application.command.AddStoreCommand;
import com.baedal.store.domain.model.Store;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreApplicationMapper {

  Store addStoreToDomain(AddStoreCommand.Request req);
}
