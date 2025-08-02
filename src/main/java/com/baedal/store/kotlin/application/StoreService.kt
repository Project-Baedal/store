package com.baedal.store.kotlin.application

import com.baedal.store.java.application.command.AddStoreCommand
import com.baedal.store.java.application.mapper.StoreApplicationMapper
import com.baedal.store.kotlin.application.out.StorePort
import com.baedal.store.kotlin.application.usecase.StoreUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class StoreService(
    val storePort: StorePort,
    val mapper: StoreApplicationMapper
) : StoreUseCase {

    @Transactional
    override fun addStore(req: AddStoreCommand.Request) {
        val store = mapper.addStoreToDomain(req)
        storePort.save(store)
    }
}
