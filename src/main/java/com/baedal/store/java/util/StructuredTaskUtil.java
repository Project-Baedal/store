package com.baedal.store.java.util;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.ShutdownOnFailure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.function.ThrowingFunction;

@Slf4j
public class StructuredTaskUtil {

  public static <R> R shutdownOnFailure(ThrowingFunction<ShutdownOnFailure, R> function) {
    try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
      return function.apply(scope);
    } catch (Exception e) {
      if (e.getCause() != null) {
        throw handleException(e.getCause());
      }
      throw handleException(e);
    }
  }

  private static RuntimeException handleException(Throwable e) {
    if (e instanceof InterruptedException) {
      Thread.currentThread().interrupt();
      log.debug("인터럽트 발생: [{}]", e.toString());

      return new RuntimeException("스레드 인터럽트 발생", e);
    } else if (e instanceof ExecutionException) {
      Throwable cause = e.getCause();
      log.debug("비동기 작업 중 예외 발생: [{}]", cause.toString());

      return new RuntimeException("비동기 작업 실패", cause);
    }
    log.debug("기타 예외 발생: [{}]", e.toString());

    return new RuntimeException("기타 예외 발생", e);
  }
}
