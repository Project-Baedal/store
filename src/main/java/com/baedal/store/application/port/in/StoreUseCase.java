package com.baedal.store.application.port.in;

import com.baedal.store.application.command.AddStoreCommand;
import com.baedal.store.application.command.DeliveryInfoCommand;
import com.baedal.store.application.command.ReviewInfoCommand;

public interface StoreUseCase {

  void addStore(AddStoreCommand.Request req);

  DeliveryInfoCommand.Response getDeliveryInfo(DeliveryInfoCommand.Request req);

  ReviewInfoCommand.Response getReviewInfo(Long storeId);
}
