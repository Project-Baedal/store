package com.baedal.store.kotlin.application.service

import com.baedal.store.java.application.mapper.StoreApplicationMapper
import com.baedal.store.kotlin.application.command.GetStoreDetailCommand
import com.baedal.store.kotlin.application.port.`in`.StoreUseCase
import com.baedal.store.kotlin.application.port.out.ProductPort
import com.baedal.store.kotlin.application.port.out.ReviewPort
import com.baedal.store.kotlin.application.port.out.StoreRepositoryPort
import com.baedal.store.kotlin.domain.model.ProductInfo
import com.baedal.store.kotlin.domain.model.Store
import com.baedal.store.kotlin.domain.model.StoreDetailReview
import com.baedal.store.kotlin.domain.model.StoreReviewSummary
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


/**
 *
 * note.
 *
 * Kotlin 의 클래스는, 기본적으로 Final 로 정의가 되기 때문에 Proxy 등을 생성하는
 * @Transactional 등을 활용할 경우, Open 키워드를 활용해야만 한다.
 *
 * Kotlin-Spring Plugin 을 활용하면 이러한 과정을 자동으로 처리해줘, 생략할 수 있도록 도와준다.
 */
//
@Service("kotlinStoreService")
open class StoreService (
    private val storeRepositoryPort: StoreRepositoryPort,
    private val storeMapper: StoreApplicationMapper,
    private val reviewPort: ReviewPort,
    private val productPort: ProductPort
): StoreUseCase {


    /**
     * note.
     *
     * 기존 ReviewScore 은, 1~5 사이의 정수를 강제하도록 Service 단에서 Enum 형식으로 데이터를 변환하고,
     * 이를 검증하였으나 어노테이션을 활용해 Controller 단에서 입력되지 않도록 방지하는 방안은 어떨지?
     * 만약, 위와 같이 변경하였다면 Service 단에서 2차 검증을 진행할 필요가 있을지?
     *
     */
    //
    @Transactional(readOnly = true)
    override fun getStoreDetail(storeId: Long): GetStoreDetailCommand {

        val store: Store = storeRepositoryPort.findById(storeId)

        val reviewInfo: StoreDetailReview = reviewPort.storeDetailReview(storeId)
        val top10Reviews: List<StoreReviewSummary> = reviewInfo.top10Reviews
        val averageScore: Double = reviewInfo.averageScore

        val products: List<ProductInfo> = productPort.findProductsByStoreId(storeId)

        return storeMapper.toCommand(store, top10Reviews, averageScore, products)
    }

}