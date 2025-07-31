package com.baedal.store.java.domain.model;

import java.time.LocalTime;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Store {

  private Long id;

  private Long ownerId;

  private String name;

  private String title;

  private String content;

  private String address;

  private String pictureUrl;

  private String category;

  private LocalTime openTime;

  private LocalTime closeTime;

  private int deliveryAmount;
}
