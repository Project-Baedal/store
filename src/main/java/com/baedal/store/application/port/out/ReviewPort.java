package com.baedal.store.application.port.out;

import com.baedal.store.domain.model.StoreReviewSummary;
import java.util.List;

public interface ReviewPort {

  List<StoreReviewSummary> getTop10Reviews(Long storeId);

  Double getAverageScore(Long storeId);
}
