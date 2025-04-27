package com.baedal.store.application.port.out;

import com.baedal.store.domain.model.Store;
import java.util.concurrent.CompletableFuture;

public interface StoreRepositoryPort {

  void save(Store store);

  Store findById(Long storeId);

  CompletableFuture<Store> findByIdAsync(Long storeId);
}
