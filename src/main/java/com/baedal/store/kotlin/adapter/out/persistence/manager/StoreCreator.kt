package com.baedal.store.kotlin.adapter.out.persistence.manager

import com.baedal.store.kotlin.adapter.out.persistence.entity.StoreEntity
import com.baedal.store.kotlin.adapter.out.persistence.repository.StoreJpaRepository
import org.springframework.stereotype.Component

@Component
class StoreCreator(
    private val storeJpaRepository: StoreJpaRepository
) {

    fun save(storeEntity: StoreEntity) {
        storeJpaRepository.save(storeEntity)
    }
}