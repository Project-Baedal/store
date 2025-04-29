package com.baedal.store.adapter.out.web.client;

import com.baedal.store.adapter.out.web.response.ProductInfoResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "${services.product.url}")
public interface ProductClient {

  @GetMapping("/v0/{storeId}")
  List<ProductInfoResponse> findProductsByStoreId(@PathVariable("storeId") Long storeId);

}