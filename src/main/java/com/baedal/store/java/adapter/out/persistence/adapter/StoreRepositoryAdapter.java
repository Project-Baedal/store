package com.baedal.store.java.adapter.out.persistence.adapter;

import com.baedal.store.java.adapter.out.persistence.entity.StoreEntity;
import com.baedal.store.java.adapter.out.persistence.manager.StoreCreator;
import com.baedal.store.java.adapter.out.persistence.manager.StoreReader;
import com.baedal.store.java.adapter.out.persistence.mapper.StorePersistenceMapper;
import com.baedal.store.java.application.port.out.StoreRepositoryPort;
import com.baedal.store.java.domain.model.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreRepositoryAdapter implements StoreRepositoryPort {

  private final StoreCreator storeCreator;
  private final StoreReader storeReader;
  private final StorePersistenceMapper mapper;

  @Override
  public void save(Store store) {
    StoreEntity entity = mapper.toEntity(store);
    storeCreator.save(entity);
  }

  @Override
  public Store findById(Long storeId) {
    StoreEntity entity = storeReader.findById(storeId);
    return mapper.toDomain(entity);
  }

}
