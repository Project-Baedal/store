package com.baedal.store.adapter.in.web.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewInfoResponse {

  private Long storeId;
  private String name;

}
