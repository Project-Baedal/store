package com.baedal.store.java.application.port.out;

import com.baedal.store.java.domain.model.ProductInfo;
import java.util.List;

public interface ProductPort {

  List<ProductInfo> findProductsByStoreId(Long storeId);
}
