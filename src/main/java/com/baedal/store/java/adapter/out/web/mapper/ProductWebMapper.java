package com.baedal.store.java.adapter.out.web.mapper;

import com.baedal.store.java.adapter.out.web.response.ProductInfoResponse;
import com.baedal.store.java.domain.model.ProductInfo;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductWebMapper {

  List<ProductInfo> findProductsByStoreIdToDomain(List<ProductInfoResponse> response);
}
