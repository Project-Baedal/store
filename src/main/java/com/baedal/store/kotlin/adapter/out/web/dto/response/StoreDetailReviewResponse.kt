package com.baedal.store.kotlin.adapter.out.web.dto.response

data class StoreDetailReviewResponse(
    val top10Reviews: List<StoreReviewSummaryResponse>,
    val averageScore: Double
)
