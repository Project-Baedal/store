package com.baedal.store.java.adapter.out.web.mapper;

import com.baedal.store.java.adapter.out.web.response.GetOrderResponse;
import com.baedal.store.java.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderWebMapper {

  @Mapping(target = "id", source = "orderId")
  Order toDomain(GetOrderResponse res);
}
