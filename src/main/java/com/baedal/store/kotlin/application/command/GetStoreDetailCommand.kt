package com.baedal.store.kotlin.application.command

import java.time.LocalTime

data class GetStoreDetailCommand(
    val storeId: Long,
    val name: String,
    val title: String,
    val content: String,
    val address: String,
    val pictureUrl: String,
    val category: String,
    val openTime: LocalTime,
    val closeTime: LocalTime,
    val deliveryAmount: Int,
    val averageScore: Double
)
