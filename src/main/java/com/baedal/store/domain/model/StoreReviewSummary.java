package com.baedal.store.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoreReviewSummary {

  private Long id;

  private ReviewScore reviewScore;

  private String content;

  private Reviewer reviewer;

  @Getter
  @Builder
  public static class Reviewer {

    private final Long customerId;
    private final String name;
  }
}
