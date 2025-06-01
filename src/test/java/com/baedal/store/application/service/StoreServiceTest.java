package com.baedal.store.application.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.baedal.store.application.command.GetStoreDetailCommand;
import com.baedal.store.application.mapper.StoreApplicationMapper;
import com.baedal.store.application.port.out.ProductPort;
import com.baedal.store.application.port.out.ReviewPort;
import com.baedal.store.application.port.out.StoreRepositoryPort;
import com.baedal.store.domain.model.ProductInfo;
import com.baedal.store.domain.model.Store;
import com.baedal.store.domain.model.StoreReviewSummary;
import io.micrometer.observation.Observation.CheckedCallable;
import java.util.List;
import java.util.concurrent.Callable;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StoreServiceTest {

  @InjectMocks
  private StoreService subject;

  @Mock
  private StoreRepositoryPort storeRepositoryPort;

  @Mock
  private ReviewPort reviewPort;

  @Mock
  private ProductPort productPort;

  @Mock
  private StoreApplicationMapper mapper;

  @Test
  void getStoreDetail_Success() {
    // GIVEN
    long storeId = 1L;
    Store store = mock(Store.class);
    when(storeRepositoryPort.findById(storeId)).thenReturn(
        store
    );

    List<StoreReviewSummary> reviews = mock(List.class);
    when(reviewPort.getTop10Reviews(storeId)).thenReturn(
        reviews
    );

    Double score = 1D;
    when(reviewPort.getAverageScore(storeId)).thenReturn(
        score
    );

    List<ProductInfo> products = mock(List.class);
    when(productPort.findProductsByStoreId(storeId)).thenReturn(
        products
    );

    // WHEN
    GetStoreDetailCommand command = subject.getStoreDetail(storeId);

    // THEN
    Mockito.verify(mapper).getStoreDetailToResponse(
        store, reviews, score, products
    );
  }

  // TODO: 예외 상황 테스트
  @Test
  void getStoreDetail_Failure_StoreNotFound() {
    // GIVEN
    long storeId = 1L;
    Store store = mock(Store.class);
    when(storeRepositoryPort.findById(storeId))
        .thenThrow(RuntimeException.class);

    List<StoreReviewSummary> reviews = mock(List.class);
    when(reviewPort.getTop10Reviews(storeId)).thenReturn(
        reviews
    );

    Double score = 1D;
    when(reviewPort.getAverageScore(storeId)).thenReturn(
        score
    );

    List<ProductInfo> products = mock(List.class);
    when(productPort.findProductsByStoreId(storeId)).thenReturn(
        products
    );

    // WHEN
    ThrowingCallable callable = () -> subject.getStoreDetail(storeId);

    // THEN
    assertThatThrownBy(callable)
        .isInstanceOf(RuntimeException.class);

    verifyNoInteractions(mapper);
  }

  @Test
  void getStoreDetail_Failure_ThreadInterrupted() {
    long storeId = 1L;

    // 태스크들이 다 느리게 실행되도록 설정
    when(storeRepositoryPort.findById(storeId)).thenAnswer(invocation -> {
      Thread.sleep(10000); // 일부러 블로킹
      return mock(Store.class);
    });

    // 현재 테스트 스레드 interrupt
    Thread.currentThread().interrupt();

    // WHEN
    ThrowingCallable callable = () -> subject.getStoreDetail(storeId);

    // THEN
    assertThatThrownBy(callable)
        .isInstanceOf(RuntimeException.class)
        .hasMessage("스레드 인터럽트 발생");

    assertThat(Thread.currentThread().isInterrupted()).isTrue(); // 복구 여부 확인 가능
  }
}
