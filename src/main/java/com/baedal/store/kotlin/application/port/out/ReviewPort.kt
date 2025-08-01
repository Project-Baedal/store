package com.baedal.store.kotlin.application.port.out

import com.baedal.store.kotlin.domain.model.StoreDetailReview

interface ReviewPort {

    fun storeDetailReview(storeId: Long): StoreDetailReview

}