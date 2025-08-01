package com.baedal.store.kotlin.application.port.out

import com.baedal.store.kotlin.domain.model.Store

interface StoreRepositoryPort {

    fun findById(storeId: Long): Store
    fun save(store: Store)

}