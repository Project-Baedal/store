package com.baedal.store.adapter.out.persistence.repository;

import com.baedal.store.adapter.out.persistence.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreJpaRepository extends JpaRepository<StoreEntity, Long> {

}
