package com.baedal.store.application.service;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.baedal.store.application.mapper.StoreApplicationMapper;
import com.baedal.store.application.port.out.ProductPort;
import com.baedal.store.application.port.out.ReviewPort;
import com.baedal.store.application.port.out.StoreRepositoryPort;
import com.baedal.store.domain.model.ProductInfo;
import com.baedal.store.domain.model.ReviewScore;
import com.baedal.store.domain.model.Store;
import com.baedal.store.domain.model.StoreReviewSummary;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class StoreServicePerformanceTest {

  @InjectMocks
  private StoreService subject;

  @Mock
  private StoreRepositoryPort storeRepositoryPort;

  @Mock
  private ReviewPort reviewPort;

  @Mock
  private ProductPort productPort;

  private StoreApplicationMapper mapper = Mappers.getMapper(StoreApplicationMapper.class);

  @BeforeEach
  void warmup() {
    ReflectionTestUtils.setField(subject, "mapper", mapper);

    Store store = stubStore();
    List<StoreReviewSummary> reviews = stubReviews();
    double avgScore = 4.5;
    List<ProductInfo> products = stubProducts();

    when(storeRepositoryPort.findById(anyLong()))
        .thenAnswer(inv -> {
          Thread.sleep(50);
          return store;
        });

    when(reviewPort.getTop10Reviews(anyLong()))
        .thenAnswer(inv -> {
          Thread.sleep(50);
          return reviews;
        });

    when(reviewPort.getAverageScore(anyLong()))
        .thenAnswer(inv -> {
          Thread.sleep(50);
          return avgScore;
        });

    when(productPort.findProductsByStoreId(anyLong()))
        .thenAnswer(inv -> {
          Thread.sleep(50);
          return products;
        });
  }

  @Test
  @DisplayName("[구조적 동시성 적용] getStoreDetail 100회 평균 실행 시간 측정")
  void measureAverageExecutionTime() {
    int loop = 100;
    long totalTimeNs = 0;

    for (int i = 0; i < loop; i++) {
      long start = System.nanoTime();
      subject.getStoreDetail(1L);
      long end = System.nanoTime();

      long durationMs = TimeUnit.NANOSECONDS.toMillis(end - start);
      System.out.printf("⏱️ [%2d회차] 실행 시간: %d ms%n", i + 1, durationMs);

      totalTimeNs += (end - start);
    }

    long avgMs = TimeUnit.NANOSECONDS.toMillis(totalTimeNs / loop);
    System.out.printf("✅ 평균 실행 시간: %d ms%n", avgMs);
  }

  @Test
  @DisplayName("[기존] getStoreDetail 100회 평균 실행 시간 측정")
  void measureAverageExecutionTimeV0() {
    int loop = 100;
    long totalTimeNs = 0;

    for (int i = 0; i < loop; i++) {
      long start = System.nanoTime();
      subject.getStoreDetailV0(1L);
      long end = System.nanoTime();

      long durationMs = TimeUnit.NANOSECONDS.toMillis(end - start);
      System.out.printf("⏱️ [%2d회차] 실행 시간: %d ms%n", i + 1, durationMs);

      totalTimeNs += (end - start);
    }

    long avgMs = TimeUnit.NANOSECONDS.toMillis(totalTimeNs / loop);
    System.out.printf("✅ 평균 실행 시간: %d ms%n", avgMs);
  }

  @Test
  @DisplayName("[구조적 동시성 적용] getStoreDetail 동시 요청 100회 평균 실행 시간 측정")
  void measureConcurrentExecutionTime() throws InterruptedException {
    int threadCount = 100;
    ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

    CountDownLatch latch = new CountDownLatch(threadCount);
    AtomicLong totalTimeNs = new AtomicLong(0);

    long globalStart = System.nanoTime();

    for (int i = 0; i < threadCount; i++) {
      executor.submit(() -> {
        long start = System.nanoTime();
        subject.getStoreDetail(1L);
        long end = System.nanoTime();

        totalTimeNs.addAndGet(end - start);
        latch.countDown();
      });
    }

    latch.await(); // 모든 요청 완료까지 대기
    long globalEnd = System.nanoTime();

    long avgMs = TimeUnit.NANOSECONDS.toMillis(totalTimeNs.get() / threadCount);
    long totalWallClockMs = TimeUnit.NANOSECONDS.toMillis(globalEnd - globalStart);

    System.out.printf("✅ [동시 요청] 평균 실행 시간: %d ms%n", avgMs);
    System.out.printf("⏱️ [동시 요청] 총 경과 시간(전체 요청 완료까지): %d ms%n", totalWallClockMs);

    executor.shutdown();
  }

  @Test
  @DisplayName("[기존] getStoreDetail 동시 요청 100회 평균 실행 시간 측정")
  void measureConcurrentExecutionTimeV0() throws InterruptedException {
    int threadCount = 100;
    ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

    CountDownLatch latch = new CountDownLatch(threadCount);
    AtomicLong totalTimeNs = new AtomicLong(0);

    long globalStart = System.nanoTime();

    for (int i = 0; i < threadCount; i++) {
      executor.submit(() -> {
        long start = System.nanoTime();
        subject.getStoreDetailV0(1L);
        long end = System.nanoTime();

        totalTimeNs.addAndGet(end - start);
        latch.countDown();
      });
    }

    latch.await(); // 모든 요청 완료까지 대기
    long globalEnd = System.nanoTime();

    long avgMs = TimeUnit.NANOSECONDS.toMillis(totalTimeNs.get() / threadCount);
    long totalWallClockMs = TimeUnit.NANOSECONDS.toMillis(globalEnd - globalStart);

    System.out.printf("✅ [동시 요청] 평균 실행 시간: %d ms%n", avgMs);
    System.out.printf("⏱️ [동시 요청] 총 경과 시간(전체 요청 완료까지): %d ms%n", totalWallClockMs);

    executor.shutdown();
  }


  private Store stubStore() {
    return Store.builder()
        .id(1L)
        .ownerId(10L)
        .name("테스트상점")
        .title("맛있는 치킨집")
        .content("항상 따뜻하고 바삭한 치킨을 제공합니다.")
        .address("서울시 강남구")
        .pictureUrl("http://example.com/store.jpg")
        .category("치킨")
        .openTime(LocalTime.of(11, 0))
        .closeTime(LocalTime.of(23, 0))
        .deliveryAmount(3000)
        .build();
  }

  private List<StoreReviewSummary> stubReviews() {
    return List.of(
        StoreReviewSummary.builder()
            .id(101L)
            .reviewScore(ReviewScore.FIVE)
            .content("정말 맛있어요!")
            .reviewer(StoreReviewSummary.Reviewer.builder()
                .customerId(1001L)
                .name("홍길동")
                .build())
            .build(),
        StoreReviewSummary.builder()
            .id(102L)
            .reviewScore(ReviewScore.FOUR)
            .content("빠르게 왔어요. 만족합니다.")
            .reviewer(StoreReviewSummary.Reviewer.builder()
                .customerId(1002L)
                .name("김영희")
                .build())
            .build(),
        StoreReviewSummary.builder()
            .id(101L)
            .reviewScore(ReviewScore.FIVE)
            .content("정말 맛있어요!")
            .reviewer(StoreReviewSummary.Reviewer.builder()
                .customerId(1001L)
                .name("홍길동")
                .build())
            .build(),
        StoreReviewSummary.builder()
            .id(102L)
            .reviewScore(ReviewScore.FOUR)
            .content("빠르게 왔어요. 만족합니다.")
            .reviewer(StoreReviewSummary.Reviewer.builder()
                .customerId(1002L)
                .name("김영희")
                .build())
            .build(),
        StoreReviewSummary.builder()
            .id(101L)
            .reviewScore(ReviewScore.FIVE)
            .content("정말 맛있어요!")
            .reviewer(StoreReviewSummary.Reviewer.builder()
                .customerId(1001L)
                .name("홍길동")
                .build())
            .build()
    );
  }

  private List<ProductInfo> stubProducts() {
    return List.of(
        ProductInfo.builder()
            .id(201L)
            .name("후라이드 치킨")
            .price(15000)
            .build(),
        ProductInfo.builder()
            .id(202L)
            .name("양념 치킨")
            .price(16000)
            .build(),
        ProductInfo.builder()
            .id(201L)
            .name("후라이드 치킨")
            .price(15000)
            .build(),
        ProductInfo.builder()
            .id(202L)
            .name("양념 치킨")
            .price(16000)
            .build(),
        ProductInfo.builder()
            .id(201L)
            .name("후라이드 치킨")
            .price(15000)
            .build(),
        ProductInfo.builder()
            .id(202L)
            .name("양념 치킨")
            .price(16000)
            .build(),
        ProductInfo.builder()
            .id(201L)
            .name("후라이드 치킨")
            .price(15000)
            .build(),
        ProductInfo.builder()
            .id(202L)
            .name("양념 치킨")
            .price(16000)
            .build()
    );
  }
}
