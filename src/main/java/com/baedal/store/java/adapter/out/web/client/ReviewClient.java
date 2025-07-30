package com.baedal.store.java.adapter.out.web.client;

import com.baedal.store.java.adapter.out.web.response.GetAverageScoreResponse;
import com.baedal.store.java.adapter.out.web.response.GetStoreTop10ReviewsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "review-service", url = "${services.review.url}")
public interface ReviewClient {

  @GetMapping("/v0/{storeId}/summary")
  GetStoreTop10ReviewsResponse getTop10Reviews(@PathVariable("storeId") Long storeId);

  @GetMapping("/v0/{storeId}/average-score")
  GetAverageScoreResponse getAverageScore(@PathVariable("storeId") Long storeId);
}