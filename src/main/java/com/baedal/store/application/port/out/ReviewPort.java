package com.baedal.store.application.port.out;

import com.baedal.store.domain.model.StoreReviewSummary;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ReviewPort {

  CompletableFuture<List<StoreReviewSummary>> getTop10Reviews(Long storeId);

  CompletableFuture<Double> getAverageScore(Long storeId);
}
