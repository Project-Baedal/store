package com.baedal.store.application.command;

import java.time.LocalTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetStoreDetailCommand {

  private Long id;

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
