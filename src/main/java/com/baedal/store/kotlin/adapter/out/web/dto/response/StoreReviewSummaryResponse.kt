package com.baedal.store.kotlin.adapter.out.web.dto.response

import com.baedal.store.kotlin.domain.model.Reviewer

data class StoreReviewSummaryResponse(
    val id: Long,
    val reviewScore: Int,
    val content: String,
    val reviewer: Reviewer
)
