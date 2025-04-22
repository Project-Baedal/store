package com.baedal.store.adapter.in.web.mapper;

import com.baedal.store.adapter.in.web.dto.response.DeliveryInfoResponse;
import com.baedal.store.adapter.in.web.dto.response.ReviewInfoResponse;
import com.baedal.store.application.command.DeliveryInfoCommand;
import com.baedal.store.application.command.ReviewInfoCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreWebMapper {

  // 배달 정보 조회
  DeliveryInfoCommand.Request deliveryInfoToCommand(Long storeId);
  DeliveryInfoResponse deliveryInfoToResponse(DeliveryInfoCommand.Response response);

  // 리뷰 정보 조회
  ReviewInfoResponse reviewInfoToResponse(ReviewInfoCommand.Response response);
}
