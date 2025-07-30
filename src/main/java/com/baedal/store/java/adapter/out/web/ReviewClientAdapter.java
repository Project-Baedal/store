package com.baedal.store.java.adapter.out.web;

import com.baedal.store.java.adapter.out.web.client.ReviewClient;
import com.baedal.store.java.adapter.out.web.mapper.ReviewWebMapper;
import com.baedal.store.java.adapter.out.web.response.GetAverageScoreResponse;
import com.baedal.store.java.adapter.out.web.response.GetStoreTop10ReviewsResponse;
import com.baedal.store.java.application.port.out.ReviewPort;
import com.baedal.store.java.domain.model.StoreReviewSummary;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewClientAdapter implements ReviewPort {

  private final ReviewClient client;

  private final ReviewWebMapper mapper;

  public List<StoreReviewSummary> getTop10Reviews(Long storeId) {
    GetStoreTop10ReviewsResponse res = client.getTop10Reviews(storeId);
    return mapper.toDomain(res);
  }

  public Double getAverageScore(Long storeId) {
    GetAverageScoreResponse res = client.getAverageScore(storeId);
    return mapper.toDouble(res);
  }
}
