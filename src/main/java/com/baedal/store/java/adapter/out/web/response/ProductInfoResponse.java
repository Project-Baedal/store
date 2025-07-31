package com.baedal.store.java.adapter.out.web.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductInfoResponse {
  private Long id;
  private Long name;
  private Integer price;
}
