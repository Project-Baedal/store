package com.baedal.store.application.command;

import lombok.Builder;
import lombok.Getter;


public class ReviewInfoCommand {

  @Getter
  @Builder
  public static class Response {
    private Long storeId;
    private String name;
  }
}
