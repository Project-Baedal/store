package com.baedal.store.application.service;

import com.baedal.store.application.command.AddStoreCommand;
import com.baedal.store.application.mapper.StoreApplicationMapper;
import com.baedal.store.application.port.in.StoreUseCase;
import com.baedal.store.application.port.out.StoreRepositoryPort;
import com.baedal.store.domain.model.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService implements StoreUseCase {

  private final StoreRepositoryPort storeRepositoryPort;
  private final StoreApplicationMapper mapper;

  @Override
  @Transactional
  public void addStore(AddStoreCommand.Request req) {
    Store store = mapper.addStoreToDomain(req);
    storeRepositoryPort.save(store);
  }
}
