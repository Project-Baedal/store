package com.baedal.store.java.adapter.out.web.mapper;

import com.baedal.store.kotlin.adapter.out.web.dto.response.StoreDetailReviewResponse;
import com.baedal.store.kotlin.domain.model.StoreDetailReview;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewWebMapper {

  StoreDetailReview toDomain(StoreDetailReviewResponse response);


}
