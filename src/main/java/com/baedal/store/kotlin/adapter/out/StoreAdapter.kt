package com.baedal.store.kotlin.adapter.out

import com.baedal.store.java.domain.model.Store
import com.baedal.store.kotlin.application.out.StorePort
import org.springframework.stereotype.Component

@Component
class StoreAdapter(

): StorePort {

    override fun save(store: Store) {
        TODO("Not yet implemented")
    }

    override fun findById(storeId: Long): Store {
        TODO("Not yet implemented")
    }
}
