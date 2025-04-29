package com.baedal.store.adapter.out.web.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductInfoResponse {
  private Long id;
  private Long name;
  private int price;
}
