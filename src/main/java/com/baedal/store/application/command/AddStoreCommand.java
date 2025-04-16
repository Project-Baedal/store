package com.baedal.store.application.command;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

public class AddStoreCommand {

  @Getter
  @Setter
  public static class Request {

    private Long ownerId;
    private String name;
    private String title;
    private String content;
    private String address;
    private String pictureUrl;
    private String category;
    private LocalDateTime openTime;
    private LocalDateTime closeTime;

  }

}
