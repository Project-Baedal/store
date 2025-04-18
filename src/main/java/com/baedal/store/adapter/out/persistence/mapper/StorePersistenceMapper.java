package com.baedal.store.adapter.out.persistence.mapper;

import com.baedal.store.adapter.out.persistence.entity.StoreEntity;
import com.baedal.store.domain.model.Store;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StorePersistenceMapper {

  StoreEntity toEntity(Store store);
  Store toDomain(StoreEntity entity);

}
