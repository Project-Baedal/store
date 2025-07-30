package com.baedal.store.java.adapter.out.web;

import com.baedal.store.java.adapter.out.web.client.ProductClient;
import com.baedal.store.java.adapter.out.web.mapper.ProductWebMapper;
import com.baedal.store.java.adapter.out.web.response.ProductInfoResponse;
import com.baedal.store.java.application.port.out.ProductPort;
import com.baedal.store.java.domain.model.ProductInfo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductClientAdapter implements ProductPort {

  private final ProductClient productClient;
  private final ProductWebMapper productMapper;

  @Override
  public List<ProductInfo> findProductsByStoreId(Long storeId) {
    List<ProductInfoResponse> response = productClient.findProductsByStoreId(storeId);
    return productMapper.findProductsByStoreIdToDomain(response);
  }
}
