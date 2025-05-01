package com.baedal.store.application.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductInfoCommand {

  private Long id;
  private Long name;
  private int price;

}
