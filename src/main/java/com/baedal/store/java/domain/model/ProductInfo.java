package com.baedal.store.java.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductInfo {

  private Long id;
  private String name;
  private Integer price;
}
