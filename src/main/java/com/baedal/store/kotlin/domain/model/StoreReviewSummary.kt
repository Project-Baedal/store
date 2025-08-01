package com.baedal.store.kotlin.domain.model


data class StoreReviewSummary(
    val id: Long,
    val reviewScore: Int,
    val content: String,
    val reviewer: Reviewer
)
