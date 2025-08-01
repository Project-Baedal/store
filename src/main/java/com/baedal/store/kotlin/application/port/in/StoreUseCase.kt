package com.baedal.store.kotlin.application.port.`in`

import com.baedal.store.kotlin.application.command.GetStoreDetailCommand

interface StoreUseCase {

    fun getStoreDetail(storeId: Long): GetStoreDetailCommand
}