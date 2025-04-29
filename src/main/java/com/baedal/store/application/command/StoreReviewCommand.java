package com.baedal.store.application.command;

import com.baedal.store.domain.model.ReviewScore;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoreReviewCommand {

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
