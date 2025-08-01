package com.baedal.store.kotlin.adapter.out.web.adapters

import com.baedal.store.java.adapter.out.web.mapper.ProductWebMapper
import com.baedal.store.kotlin.adapter.out.web.client.ProductClient
import com.baedal.store.kotlin.adapter.out.web.dto.response.ProductInfoResponse
import com.baedal.store.kotlin.application.port.out.ProductPort
import com.baedal.store.kotlin.domain.model.ProductInfo
import org.springframework.stereotype.Component

@Component("kotlinProductClientAdapter")
class ProductClientAdapter (
    private val productClient: ProductClient,
    private val productMapper: ProductWebMapper
): ProductPort {

    override fun findProductsByStoreId(storeId: Long): List<ProductInfo> {
        val response: List<ProductInfoResponse> = productClient.findProductsByStoreId(storeId)
        return productMapper.toDomain(response)
    }

}