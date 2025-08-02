package com.baedal.store.kotlin.util

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper

object ObjectMapperUtil {

    val mapper = ObjectMapper()

    fun toJson(raw: Any): String {
        val message = if (raw is String) JsonMessage(raw) else raw
        return mapper.writeValueAsString(message)
    }

    fun <T> fromJson(json: String, clazz: Class<T>): T {
        try {
            return mapper.readValue(json, clazz)
        } catch (e: JsonProcessingException) {
            throw RuntimeException(e)
        }
    }

    class JsonMessage(val message: String)
}
