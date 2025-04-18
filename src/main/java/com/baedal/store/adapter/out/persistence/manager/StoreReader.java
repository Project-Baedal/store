package com.baedal.store.adapter.out.persistence.manager;

import com.baedal.store.adapter.out.persistence.entity.StoreEntity;
import com.baedal.store.adapter.out.persistence.repository.StoreJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreReader {

  private final StoreJpaRepository storeJpaRepository;

  public StoreEntity findById(Long storeId) {
    return storeJpaRepository.findById(storeId)
        .orElseThrow(() -> new RuntimeException("접근 권한이 없거나 존재하지 않는 매장입니다."));
  }

}
