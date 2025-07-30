package com.baedal.store.java.adapter.out.persistence.repository;

import com.baedal.store.java.adapter.out.persistence.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreJpaRepository extends JpaRepository<StoreEntity, Long> {

}
