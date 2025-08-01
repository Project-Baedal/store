package com.baedal.store.java.adapter.in.web.mapper;

import com.baedal.store.java.adapter.in.web.dto.response.DeliveryInfoResponse;
import com.baedal.store.java.adapter.in.web.dto.response.SearchNameResponse;
import com.baedal.store.java.application.command.DeliveryInfoCommand;
import com.baedal.store.java.application.command.SearchNameCommand;
import com.baedal.store.kotlin.adapter.in.web.dto.response.GetStoreDetailResponse;
import com.baedal.store.kotlin.application.command.GetStoreDetailCommand;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreWebMapper {

  // 배달 정보 조회
  DeliveryInfoCommand.Request deliveryInfoToCommand(Long storeId);

  DeliveryInfoResponse deliveryInfoToResponse(DeliveryInfoCommand.Response response);

  // 매장명 검색
  SearchNameCommand.Request searchNameToCommand(String name);
  List<SearchNameResponse> searchNameToResponse(List<SearchNameCommand.Response> response);

  GetStoreDetailResponse toResponse(GetStoreDetailCommand command);
}
