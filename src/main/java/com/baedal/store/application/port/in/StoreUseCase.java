package com.baedal.store.application.port.in;

import com.baedal.store.application.command.AddStoreCommand;
import com.baedal.store.application.command.DeliveryInfoCommand;
import com.baedal.store.application.command.GetStoreDetailCommand;
import com.baedal.store.application.command.SearchNameCommand;
import com.baedal.store.application.command.ValidateOrderInfoCommand;
import java.util.List;

public interface StoreUseCase {

  void addStore(AddStoreCommand.Request req);

  DeliveryInfoCommand.Response getDeliveryInfo(DeliveryInfoCommand.Request req);

  void validateStoreOrderInfo(ValidateOrderInfoCommand.Request req);

  GetStoreDetailCommand getStoreDetail(Long storeId);

  List<SearchNameCommand.Response> getSearchName(SearchNameCommand.Request req);
}
