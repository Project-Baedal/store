package com.baedal.store.adapter.out.persistence.adapter;

import com.baedal.store.adapter.out.persistence.entity.StoreEntity;
import com.baedal.store.adapter.out.persistence.manager.StoreCreator;
import com.baedal.store.adapter.out.persistence.manager.StoreReader;
import com.baedal.store.adapter.out.persistence.mapper.StorePersistenceMapper;
import com.baedal.store.application.port.out.StoreRepositoryPort;
import com.baedal.store.domain.model.Store;
import java.util.concurrent.CompletableFuture;
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

  @Override
  public CompletableFuture<Store> findByIdAsync(Long storeId) {
    StoreEntity entity = storeReader.findById(storeId);
    return CompletableFuture.completedFuture(mapper.toDomain(entity));
  }
}
