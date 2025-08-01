package com.baedal.store.kotlin.adapter.out.web.client

import com.baedal.store.kotlin.adapter.out.web.dto.response.StoreDetailReviewResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "kotlin-review-service", url = "\${services.review.url}")
interface ReviewClient {

    @GetMapping("/v0/{storeId}/getStoreDetailReview")
    fun getStoreDetailReview(@PathVariable("storeId") storeId: Long): StoreDetailReviewResponse

}