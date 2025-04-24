package com.baedal.store.domain.business;

import com.baedal.store.domain.model.Store;
import java.time.LocalTime;
import org.springframework.stereotype.Component;

@Component
public class StoreValidator {

  public void validateOpenTimeBeforeCloseTime(LocalTime openTime, LocalTime closeTime) {
    if (openTime.isAfter(closeTime)) {
      throw new RuntimeException("영업 시작 시간은 종료 시간보다 이전이어야 합니다.");
    }
  }

  public void validateDeliveryAmount(Store store, int deliveryAmount) {
    if (store.getDeliveryAmount() != deliveryAmount) {
      throw new RuntimeException("유효한 배달비가 아닙니다.");
    }
  }

}
