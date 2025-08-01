package com.baedal.store.kotlin.adapter.out.persistence.adapters

import com.baedal.store.java.adapter.out.persistence.mapper.StorePersistenceMapper
import com.baedal.store.kotlin.adapter.out.persistence.entity.StoreEntity
import com.baedal.store.kotlin.adapter.out.persistence.manager.StoreCreator
import com.baedal.store.kotlin.adapter.out.persistence.manager.StoreReader
import com.baedal.store.kotlin.application.port.out.StoreRepositoryPort
import com.baedal.store.kotlin.domain.model.Store
import org.springframework.stereotype.Component

@Component
class StoreRepositoryAdapter(
    private val storeReader: StoreReader,
    private val storeCreator: StoreCreator,
    private val storeMapper: StorePersistenceMapper
): StoreRepositoryPort
{

    override fun findById(storeId: Long): Store {
        val storeEntity: StoreEntity = storeReader.findById(storeId)
        return storeMapper.toDomain(storeEntity)
    }

    override fun save(store: Store) {
        val storeEntity: StoreEntity = storeMapper.toEntity(store)
        storeCreator.save(storeEntity)
    }

}