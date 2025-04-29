package com.baedal.store.application.command;

import com.baedal.store.domain.model.StoreReviewSummary;
import java.time.LocalTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetStoreDetailCommand {

  private Long storeId;

  private String name;

  private String title;

  private String content;

  private String address;

  private String pictureUrl;

  private String category;

  private LocalTime openTime;

  private LocalTime closeTime;

  private int deliveryAmount;

  private List<StoreReviewCommand> top10Reviews;

  private List<ProductInfoCommand> products;

  private double averageScore;
}
