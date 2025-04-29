package com.baedal.store.adapter.in.web.controller;

import com.baedal.store.adapter.in.web.dto.response.DeliveryInfoResponse;
import com.baedal.store.adapter.in.web.mapper.StoreWebMapper;
import com.baedal.store.application.command.DeliveryInfoCommand;
import com.baedal.store.application.command.GetStoreDetailCommand;
import com.baedal.store.application.port.in.StoreUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/store/v0")
@RequiredArgsConstructor
public class StoreController {

  private final StoreWebMapper mapper;

  private final StoreUseCase storeUseCase;

  @GetMapping("/deliveryInfo/{storeId}")
  public ResponseEntity<DeliveryInfoResponse> deliveryInfo(@PathVariable Long storeId) {
    DeliveryInfoCommand.Request command = mapper.deliveryInfoToCommand(storeId);
    DeliveryInfoCommand.Response response = storeUseCase.getDeliveryInfo(command);
    return ResponseEntity.ok(mapper.deliveryInfoToResponse(response));
  }

  @GetMapping("/{storeId}")
  public ResponseEntity<GetStoreDetailCommand> getStoreDetail(@PathVariable Long storeId) {
    GetStoreDetailCommand response = storeUseCase.getStoreDetail(storeId);
    return ResponseEntity.ok(response);
  }
}
