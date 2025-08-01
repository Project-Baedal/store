package com.baedal.store.kotlin.adapter.out.persistence.entity

import jakarta.persistence.*
import java.time.LocalTime


/**
 * note.
 *
 * 1. Nullable 관리를 < ? = null > 이외의 방식으로 깔끔하게 할 수는 없을까?
 * 2. Kotlin 지원하는 Getter 는 Private 을 붙이면 활용할 수 없어, Public 을 활용해 외부에 노출 시켜도 괜찮을까?
 *
 */
//
@Entity
@Table(name = "stores")
class   StoreEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val ownerId: Long? = null,

    @Column(nullable = false)
    val name: String? = null,

    val title: String? = null,

    val content: String? = null,

    @Column(nullable = false)
    val address: String? = null,

    val pictureUrl: String? = null,

    @Column(nullable = false)
    val category: String? = null,

    val openTime: LocalTime? = null,

    val closeTime: LocalTime? = null,

    val deliveryAmount: Int? = null
)