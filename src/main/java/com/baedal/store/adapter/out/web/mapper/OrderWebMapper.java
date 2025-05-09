package com.baedal.store.adapter.out.web.mapper;

import com.baedal.store.adapter.out.web.response.GetOrderResponse;
import com.baedal.store.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderWebMapper {

  @Mapping(target = "id", source = "orderId")
  Order toDomain(GetOrderResponse res);
}
