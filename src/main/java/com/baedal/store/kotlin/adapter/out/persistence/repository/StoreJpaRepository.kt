package com.baedal.store.kotlin.adapter.out.persistence.repository

import com.baedal.store.kotlin.adapter.out.persistence.entity.StoreEntity
import org.springframework.data.jpa.repository.JpaRepository

interface StoreJpaRepository: JpaRepository<StoreEntity, Long> {
}