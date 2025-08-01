package com.baedal.store.kotlin.adapter.out.persistence.manager

import com.baedal.store.kotlin.adapter.out.persistence.entity.StoreEntity
import com.baedal.store.kotlin.adapter.out.persistence.repository.StoreJpaRepository
import org.springframework.stereotype.Component

@Component
class StoreReader (
    private val storeJpaRepository: StoreJpaRepository,
) {

    fun findById(storeId: Long): StoreEntity {
        return storeJpaRepository.findById(storeId)
            .orElseThrow { RuntimeException("접근 권한이 없거나 존재하지 않는 매장입니다. ")}
    }

}