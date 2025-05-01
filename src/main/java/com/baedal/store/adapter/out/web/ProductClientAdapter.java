package com.baedal.store.adapter.out.web;

import com.baedal.store.adapter.out.web.client.ProductClient;
import com.baedal.store.adapter.out.web.mapper.ProductWebMapper;
import com.baedal.store.adapter.out.web.response.ProductInfoResponse;
import com.baedal.store.application.port.out.ProductPort;
import com.baedal.store.domain.model.ProductInfo;
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
