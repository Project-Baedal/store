package com.baedal.store.java.adapter.in.web.controller;

import com.baedal.store.java.adapter.in.web.dto.response.DeliveryInfoResponse;
import com.baedal.store.java.adapter.in.web.dto.response.SearchNameResponse;
import com.baedal.store.java.adapter.in.web.mapper.StoreWebMapper;
import com.baedal.store.java.application.command.DeliveryInfoCommand;
import com.baedal.store.java.application.command.GetStoreDetailCommand;
import com.baedal.store.java.application.command.SearchNameCommand;
import com.baedal.store.java.application.port.in.StoreUseCase;
import com.baedal.store.java.application.service.StoreOrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/store/v0")
@RequiredArgsConstructor
public class StoreController {

  private final StoreWebMapper mapper;

  private final StoreUseCase storeUseCase;

  private final StoreOrderService storeOrderService;

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


  @PreAuthorize("hasRole('OWNER')")
  @PostMapping("/{storeId}/orders/{orderId}/accept")
  public ResponseEntity<Void> acceptOrder(
      @AuthenticationPrincipal Long userId,
      @PathVariable Long storeId,
      @PathVariable Long orderId) {
    storeOrderService.acceptOrder(storeId, orderId, userId);
    return ResponseEntity.noContent().build();
  }

  @PreAuthorize("hasRole('OWNER')")
  @PostMapping("/{storeId}/orders/{orderId}/deny")
  public ResponseEntity<Void> denyOrder(
      @AuthenticationPrincipal Long userId,
      @PathVariable Long storeId,
      @PathVariable Long orderId) {
    storeOrderService.denyOrder(storeId, orderId, userId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/searchName")
  public ResponseEntity<List<SearchNameResponse>> searchName(@RequestPart String name) {
    SearchNameCommand.Request req = mapper.searchNameToCommand(name);
    List<SearchNameCommand.Response> response = storeUseCase.getSearchName(req);
    return ResponseEntity.ok(mapper.searchNameToResponse(response));
  }
}
