package com.baedal.store.application.command;

import lombok.Builder;
import lombok.Getter;

public class SearchNameCommand {

  @Getter
  @Builder
  public static class Request {
    private String name;
  }

  @Getter
  @Builder
  public static class Response {
    private String name;
  }


}
