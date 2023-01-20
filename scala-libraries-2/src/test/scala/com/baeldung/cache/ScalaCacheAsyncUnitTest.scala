package com.baeldung.cache

import com.baeldung.cache.service.{
  AsyncGuavaCacheMemoizationConfig,
  AsyncQueryMemoizeService,
  GuavaCacheCatsConfig
}
import org.scalatest.BeforeAndAfterEach
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ScalaCacheAsyncUnitTest
  extends AnyWordSpec
  with Matchers
  with BeforeAndAfterEach {

  override def beforeEach(): Unit = {
    AsyncGuavaCacheMemoizationConfig.memoizedUnderlyingGuavaCache
      .invalidateAll()
  }

  "Asynchronous memoization" should {
    "save the result to cache once the future is successful" in {
      import AsyncGuavaCacheMemoizationConfig._
      val asyncService = new AsyncQueryMemoizeService()
      asyncService.getUser(100)
      AsyncGuavaCacheMemoizationConfig.memoizedUnderlyingGuavaCache
        .size() shouldBe 0
      // wait for the prev operation to complete and set to cache
      Thread.sleep(1100)
      AsyncGuavaCacheMemoizationConfig.memoizedUnderlyingGuavaCache
        .size() shouldBe 1
    }

    "get result from cache for future operation" in {
      import AsyncGuavaCacheMemoizationConfig._
      import scala.concurrent.ExecutionContext.Implicits.global
      val asyncService = new AsyncQueryMemoizeService()
      val future = asyncService.checkFutureThread(88)
      future.foreach { case (main, memThread) =>
        main should not be (memThread)
      }
      // wait for the prev operation to complete and set to cache
      Thread.sleep(300)
      AsyncGuavaCacheMemoizationConfig.memoizedUnderlyingGuavaCache
        .size() shouldBe 1
    }

    "NOT save the result to cache if future is failed" in {
      import AsyncGuavaCacheMemoizationConfig._
      val asyncService = new AsyncQueryMemoizeService()
      asyncService.getUserFail(100)
      AsyncGuavaCacheMemoizationConfig.memoizedUnderlyingGuavaCache
        .size() shouldBe 0
      // wait for the prev operation to complete and set to cache
      Thread.sleep(200)
      AsyncGuavaCacheMemoizationConfig.memoizedUnderlyingGuavaCache
        .size() shouldBe 0
      asyncService.getUserFail(100)
      Thread.sleep(50)
      // increment twice since caching is not done due to failure
      asyncService.queryCount shouldBe 2
    }
  }

}
