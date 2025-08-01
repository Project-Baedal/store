package com.baedal.store.kotlin.domain.model

data class StoreDetailReview(
    val top10Reviews: List<StoreReviewSummary>,
    val averageScore: Double
)
