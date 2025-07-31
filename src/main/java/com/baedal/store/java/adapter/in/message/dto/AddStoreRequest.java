package com.baedal.store.java.adapter.in.message.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddStoreRequest {

  @Schema(description = "매장명")
  @NotBlank
  private String name;

  @Schema(description = "매장 부제목")
  private String title;

  @Schema(description = "매장 설명")
  private String content;

  @Schema(description = "매장 주소")
  @NotBlank
  private String address;

  @Schema(description = "매장 사진")
  private String pictureUrl;

  @Schema(description = "카테고리")
  private String category;

  @Schema(description = "영업 시작 시간")
  @NotBlank
  private LocalTime openTime;

  @Schema(description = "영업 종료 시간")
  @NotBlank
  private LocalTime closeTime;

  @Schema(description = "배달비")
  @PositiveOrZero
  private int deliveryAmount;


}
