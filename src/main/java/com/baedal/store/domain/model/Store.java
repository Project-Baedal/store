package com.baedal.store.domain.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Store {

  private String name;

  private String title;

  private String content;

  private String address;

  private String pictureUrl;

  private String category;

  private LocalDateTime openTime;

  private LocalDateTime closeTime;
}
