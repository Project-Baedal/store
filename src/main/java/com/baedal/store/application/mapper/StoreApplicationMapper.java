package com.baedal.store.application.mapper;

import com.baedal.store.application.command.AddStoreCommand;
import com.baedal.store.application.command.DeliveryInfoCommand;
import com.baedal.store.application.command.GetStoreDetailCommand;
import com.baedal.store.application.command.ReviewInfoCommand;
import com.baedal.store.domain.model.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StoreApplicationMapper {

  Store addStoreToDomain(AddStoreCommand.Request req);

  @Mapping(target = "storeName", source = "name")
  DeliveryInfoCommand.Response getDeliveryInfoToResponse(Store store);

  // 리뷰 정보 조회
  @Mapping(target = "storeId", source = "id")
  ReviewInfoCommand.Response getReviewInfoToResponse(Store store);

  @Mapping(target = "storeId", source = "id")
  GetStoreDetailCommand getStoreDetailToResponse(Store store);
}
