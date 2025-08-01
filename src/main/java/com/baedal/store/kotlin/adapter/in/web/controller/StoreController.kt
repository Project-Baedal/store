package com.baedal.store.kotlin.adapter.`in`.web.controller

import com.baedal.store.java.adapter.`in`.web.mapper.StoreWebMapper
import com.baedal.store.kotlin.adapter.`in`.web.dto.response.GetStoreDetailResponse
import com.baedal.store.kotlin.application.command.GetStoreDetailCommand
import com.baedal.store.kotlin.application.port.`in`.StoreUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController("storeControllerKotlin")
@RequestMapping("/api/store/v0")
class StoreController (
    private val storeUseCase: StoreUseCase,
    private val storeMapper: StoreWebMapper
){

    @GetMapping("/{storeId}")
    fun getStoreDetail(@PathVariable storeId: Long): ResponseEntity<GetStoreDetailResponse> {
        val command: GetStoreDetailCommand = storeUseCase.getStoreDetail(storeId)
        val response: GetStoreDetailResponse = storeMapper.toResponse(command)
        return ResponseEntity.ok(response)
    }
}