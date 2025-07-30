package com.baedal.store.java.application.port.out;

import com.baedal.store.java.domain.model.StoreReviewSummary;
import java.util.List;

public interface ReviewPort {

  List<StoreReviewSummary> getTop10Reviews(Long storeId);

  Double getAverageScore(Long storeId);
}
