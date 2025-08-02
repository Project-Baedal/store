package com.baedal.store.kotlin.application.out

import com.baedal.store.java.domain.model.Store

interface StorePort {

    fun save(store: Store)

    fun findById(storeId: Long): Store
}
