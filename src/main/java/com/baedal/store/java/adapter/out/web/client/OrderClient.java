package com.baedal.store.java.adapter.out.web.client;

import com.baedal.store.java.adapter.out.web.response.GetOrderResponse;
import com.baedal.store.java.domain.model.OrderStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "order-service", url = "${services.order.url}")
public interface OrderClient {

  @GetMapping("/v0/{orderId}")
  public ResponseEntity<GetOrderResponse> getOrder(@PathVariable Long orderId);

  @PatchMapping("/v0/{orderId}")
  public ResponseEntity<Void> changeOrderStatus(
      @PathVariable Long orderID,
      @RequestParam("status") OrderStatus status);
}
