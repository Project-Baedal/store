package com.baedal.store.application.service;

import com.baedal.store.application.business.StoreValidator;
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
  private final StoreValidator validator;

  @Override
  @Transactional
  public void addStore(AddStoreCommand.Request req) {

    // 영업 시작 시간이 영업 종료 시간보다 작은지 확인
    validator.validateOpenTimeBeforeCloseTime(req.getOpenTime(), req.getCloseTime());

    Store store = mapper.addStoreToDomain(req);
    storeRepositoryPort.save(store);
  }
}
