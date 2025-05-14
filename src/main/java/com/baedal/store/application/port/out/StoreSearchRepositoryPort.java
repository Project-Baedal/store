package com.baedal.store.application.port.out;

import com.baedal.store.domain.model.Store;
import java.util.List;

public interface StoreSearchRepositoryPort {

  List<Store> findByNameContaining(String name);

}
