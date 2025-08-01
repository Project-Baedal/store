package com.baedal.store.java.adapter.out.web.mapper;

import com.baedal.store.kotlin.adapter.out.web.dto.response.ProductInfoResponse;
import com.baedal.store.kotlin.domain.model.ProductInfo;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductWebMapper {

  List<ProductInfo> toDomain(List<ProductInfoResponse> response);
}
