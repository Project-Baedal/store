package com.baedal.store.kotlin.adapter.out.web.client

import com.baedal.store.kotlin.adapter.out.web.dto.response.ProductInfoResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "kotlin-product-service", url = "\${services.product.url}")
interface ProductClient {

    @GetMapping("/v0/{storeId}")
    fun findProductsByStoreId(@PathVariable("storeId") storeId: Long): List<ProductInfoResponse>

}