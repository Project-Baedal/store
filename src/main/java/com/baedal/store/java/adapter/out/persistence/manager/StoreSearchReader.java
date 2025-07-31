package com.baedal.store.java.adapter.out.persistence.manager;

import com.baedal.store.java.adapter.out.persistence.entity.StoreNameEntity;
import com.baedal.store.java.adapter.out.persistence.repository.StoreElasticRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreSearchReader {

  private final StoreElasticRepository storeElasticRepository;

  public List<StoreNameEntity> findByNameContaining(String name) {
    return storeElasticRepository.findByNameContaining(name);
  }

}
