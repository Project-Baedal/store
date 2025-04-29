package com.baedal.store.application.port.out;

import com.baedal.store.domain.model.ProductInfo;
import java.util.List;

public interface ProductPort {

  List<ProductInfo> findProductsByStoreId(Long storeId);
}
