package com.baedal.store.kotlin.util

import org.junit.jupiter.api.Test

class ObjectMapperUtilTest {

    @Test
    fun normalParameter() {
        val raw = MockStore("Store", 1L)
        val result = ObjectMapperUtil.toJson(raw)

        println(result)
    }

    @Test
    fun just_String() {
        val raw = "Store"
        val result = ObjectMapperUtil.toJson(raw)

        println(result)
    }

    class MockStore(val name: String, val id: Long)
}
