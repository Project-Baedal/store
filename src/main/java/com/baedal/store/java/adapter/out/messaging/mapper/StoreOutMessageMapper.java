package com.baedal.store.java.adapter.out.messaging.mapper;

import com.baedal.store.java.adapter.out.messaging.dto.SendOrderValidate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreOutMessageMapper {

  SendOrderValidate orderValidate(boolean status, String errorMessage);

}
