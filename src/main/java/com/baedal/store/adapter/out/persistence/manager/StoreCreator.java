package com.baedal.store.adapter.out.persistence.manager;

import com.baedal.store.adapter.out.persistence.entity.StoreEntity;
import com.baedal.store.adapter.out.persistence.repository.StoreJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreCreator {

  private final StoreJpaRepository storeJpaRepository;

  public void save(StoreEntity entity) {
    storeJpaRepository.save(entity);
  }

}
