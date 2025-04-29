package com.baedal.store.adapter.out.web.mapper;

import com.baedal.store.adapter.out.web.response.ProductInfoResponse;
import com.baedal.store.domain.model.ProductInfo;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductWebMapper {

  List<ProductInfo> findProductsByStoreIdToDomain(List<ProductInfoResponse> response);
}
