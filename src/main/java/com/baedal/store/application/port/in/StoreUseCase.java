package com.baedal.store.application.port.in;

import com.baedal.store.application.command.AddStoreCommand;

public interface StoreUseCase {

	void addStore(AddStoreCommand.Request req);
}
