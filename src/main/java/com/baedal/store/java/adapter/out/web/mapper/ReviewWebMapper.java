package com.baedal.store.java.adapter.out.web.mapper;

import com.baedal.store.java.adapter.out.web.response.GetAverageScoreResponse;
import com.baedal.store.java.adapter.out.web.response.GetStoreTop10ReviewsResponse;
import com.baedal.store.java.domain.model.StoreReviewSummary;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewWebMapper {

  default List<StoreReviewSummary> toDomain(GetStoreTop10ReviewsResponse res) {
    return res.getData();
  }

  default double toDouble(GetAverageScoreResponse res) {
    return res.average();
  }
}
