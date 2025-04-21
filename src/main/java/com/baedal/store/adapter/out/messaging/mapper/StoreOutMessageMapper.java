package com.baedal.store.adapter.out.messaging.mapper;

import com.baedal.store.adapter.out.messaging.dto.SendOrderValidate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreOutMessageMapper {

  SendOrderValidate orderValidate(
      String orderTransactionId, boolean status, String errorMessage
  );

}
