package com.baedal.store.application.port.out;

import com.baedal.store.domain.model.Store;

public interface StoreRepositoryPort {

  void save(Store store);
}
