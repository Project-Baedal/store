package com.baedal.store.application.business;

import java.time.LocalTime;
import org.springframework.stereotype.Component;

@Component
public class StoreValidator {

  public void validateOpenTimeBeforeCloseTime(LocalTime openTime, LocalTime closeTime) {
    if (openTime.isAfter(closeTime)) {
      throw new RuntimeException("영업 시작 시간은 종료 시간보다 이전이어야 합니다.");
    }
  }

}
