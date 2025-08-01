package com.baedal.store.kotlin.application.port.out

import com.baedal.store.kotlin.domain.model.ProductInfo

interface ProductPort {

    fun findProductsByStoreId(storeId: Long): List<ProductInfo>

}