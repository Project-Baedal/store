package com.baedal.store.java.application.port.out;

import com.baedal.store.kotlin.domain.model.Store;
import java.util.List;

public interface StoreSearchRepositoryPort {

  List<Store> findByNameContaining(String name);

}
