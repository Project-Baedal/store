package com.baedal.store.adapter.out.web.response;

import com.baedal.store.domain.model.StoreReviewSummary;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetStoreTop10ReviewsResponse {

  private List<StoreReviewSummary> data;
}
