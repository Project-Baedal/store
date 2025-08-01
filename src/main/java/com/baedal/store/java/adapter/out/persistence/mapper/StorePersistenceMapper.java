package com.baedal.store.java.adapter.out.persistence.mapper;

import com.baedal.store.kotlin.adapter.out.persistence.entity.StoreEntity;
import com.baedal.store.kotlin.domain.model.Store;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StorePersistenceMapper {

  StoreEntity toEntity(Store store);
  Store toDomain(StoreEntity entity);

}
