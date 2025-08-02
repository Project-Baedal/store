package com.baedal.store.kotlin.application.usecase

import com.baedal.store.java.application.command.AddStoreCommand

interface StoreUseCase {

    fun addStore(req: AddStoreCommand.Request)
}
