package com.baedal.store.java.application.port.out;

import com.baedal.store.java.domain.model.Store;

public interface StoreRepositoryPort {

  void save(Store store);

  Store findById(Long storeId);
}
