package com.baedal.store.java.adapter.out.persistence.adapter;

import com.baedal.store.java.adapter.out.persistence.entity.StoreNameEntity;
import com.baedal.store.java.adapter.out.persistence.manager.StoreSearchReader;
import com.baedal.store.java.adapter.out.persistence.mapper.StoreSearchPersistenceMapper;
import com.baedal.store.java.application.port.out.StoreSearchRepositoryPort;
import com.baedal.store.kotlin.domain.model.Store;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StoreSearchRepositoryAdapter implements StoreSearchRepositoryPort {

  private final StoreSearchReader storeSearchReader;
  private final StoreSearchPersistenceMapper storeMapper;

  @Override
  public List<Store> findByNameContaining(String name) {
    List<StoreNameEntity> entity = storeSearchReader.findByNameContaining(name);
    return storeMapper.toDomain(entity);
  }
}
