package com.baedal.store.java.adapter.out.persistence.mapper;

import com.baedal.store.java.adapter.out.persistence.entity.StoreNameEntity;
import com.baedal.store.java.domain.model.Store;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreSearchPersistenceMapper {

  List<Store> toDomain(List<StoreNameEntity> entity);
}
