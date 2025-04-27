package com.baedal.store.adapter.out.web;

import com.baedal.store.adapter.out.web.client.ReviewClient;
import com.baedal.store.adapter.out.web.mapper.ReviewWebMapper;
import com.baedal.store.adapter.out.web.response.GetAverageScoreResponse;
import com.baedal.store.adapter.out.web.response.GetStoreTop10ReviewsResponse;
import com.baedal.store.application.port.out.ReviewPort;
import com.baedal.store.domain.model.StoreReviewSummary;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewClientAdapter implements ReviewPort {

  private final ReviewClient client;

  private final ReviewWebMapper mapper;

  @Override
  public List<StoreReviewSummary> getTop10Reviews(Long storeId) {
    GetStoreTop10ReviewsResponse res = client.getTop10Reviews(storeId);
    return mapper.toDomain(res);
  }

  @Override
  public double getAverageScore(Long storeId) {
    GetAverageScoreResponse res = client.getAverageScore(storeId);
    return mapper.toDouble(res);
  }
}
