package com.baedal.store.java.application.port.in;

import com.baedal.store.java.application.command.AddStoreCommand;
import com.baedal.store.java.application.command.DeliveryInfoCommand;
import com.baedal.store.java.application.command.SearchNameCommand;
import com.baedal.store.java.application.command.ValidateOrderInfoCommand;
import java.util.List;

public interface StoreUseCase {

  void addStore(AddStoreCommand.Request req);

  DeliveryInfoCommand.Response getDeliveryInfo(DeliveryInfoCommand.Request req);

  void validateStoreOrderInfo(ValidateOrderInfoCommand.Request req);

  List<SearchNameCommand.Response> getSearchName(SearchNameCommand.Request req);
}
