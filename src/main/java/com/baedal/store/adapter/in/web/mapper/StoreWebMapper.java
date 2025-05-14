package com.baedal.store.adapter.in.web.mapper;

import com.baedal.store.adapter.in.web.dto.response.DeliveryInfoResponse;
import com.baedal.store.adapter.in.web.dto.response.SearchNameResponse;
import com.baedal.store.application.command.DeliveryInfoCommand;
import com.baedal.store.application.command.SearchNameCommand;
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

}
