package com.baedal.store.java.application.mapper;

import com.baedal.store.java.application.command.AddStoreCommand;
import com.baedal.store.java.application.command.DeliveryInfoCommand;
import com.baedal.store.java.application.command.GetStoreDetailCommand;
import com.baedal.store.java.application.command.SearchNameCommand;
import com.baedal.store.java.domain.model.ProductInfo;
import com.baedal.store.java.domain.model.Store;
import com.baedal.store.java.domain.model.StoreReviewSummary;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StoreApplicationMapper {

  Store addStoreToDomain(AddStoreCommand.Request req);

  @Mapping(target = "storeName", source = "name")
  DeliveryInfoCommand.Response getDeliveryInfoToResponse(Store store);

  @Mapping(target = "storeId", source = "store.id")
  GetStoreDetailCommand getStoreDetailToResponse(
      Store store,
      List<StoreReviewSummary> top10Reviews,
      double averageScore,
      List<ProductInfo> products
  );

  // 매장명 검색
  List<SearchNameCommand.Response> searchNameToResponse(List<Store> store);
}
