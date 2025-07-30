package com.baedal.store.java.adapter.out.persistence.repository;

import com.baedal.store.java.adapter.out.persistence.entity.StoreNameEntity;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface StoreElasticRepository extends ElasticsearchRepository<StoreNameEntity, Long> {

  List<StoreNameEntity> findByNameContaining(String name);
}
