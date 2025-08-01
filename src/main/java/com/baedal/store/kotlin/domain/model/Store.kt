package com.baedal.store.kotlin.domain.model

import java.time.LocalTime

data class Store(

    val id: Long,

    val ownerId: Long,

    val name: String,

    val title: String,

    val content: String,

    val address: String,

    val pictureUrl: String,

    val category: String,

    val openTime: LocalTime,

    val closeTime: LocalTime,

    val deliveryAmount: Int
)
