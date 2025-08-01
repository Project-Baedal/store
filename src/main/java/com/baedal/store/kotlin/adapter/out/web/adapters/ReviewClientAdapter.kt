package com.baedal.store.kotlin.adapter.out.web.adapters

import com.baedal.store.java.adapter.out.web.mapper.ReviewWebMapper
import com.baedal.store.kotlin.adapter.out.web.client.ReviewClient
import com.baedal.store.kotlin.adapter.out.web.dto.response.StoreDetailReviewResponse

import com.baedal.store.kotlin.application.port.out.ReviewPort
import com.baedal.store.kotlin.domain.model.StoreDetailReview
import org.springframework.stereotype.Component

@Component("kotlinReviewClientAdapter")
class ReviewClientAdapter (
    private val reviewClient: ReviewClient,
    private val reviewMapper: ReviewWebMapper
): ReviewPort {

    override fun storeDetailReview(storeId: Long): StoreDetailReview {
        val response: StoreDetailReviewResponse = reviewClient.getStoreDetailReview(storeId)
        return reviewMapper.toDomain(response)
    }

}