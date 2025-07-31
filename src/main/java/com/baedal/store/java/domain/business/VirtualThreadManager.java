package com.baedal.store.java.domain.business;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class VirtualThreadManager {

  private final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

  public <T> Future<T> submitAsync(Callable<T> task) {
    return executorService.submit(task);
  }

  public <T> T extractResult(Future<T> future) {
    try {
      return future.get();
    } catch (Exception e) {
      throw new RuntimeException("Future 결과 추출 실패", e);
    }
  }
}
