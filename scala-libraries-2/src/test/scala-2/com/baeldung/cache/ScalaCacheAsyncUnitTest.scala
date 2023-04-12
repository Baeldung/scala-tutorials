package com.baeldung.cache

import com.baeldung.cache.service.{AsyncGuavaCacheMemoizationConfig, AsyncQueryMemoizeService}
import org.scalatest.BeforeAndAfterEach
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AsyncWordSpec

class ScalaCacheAsyncUnitTest
  extends AsyncWordSpec
  with Matchers
  with BeforeAndAfterEach {

  override def beforeEach(): Unit = {
    AsyncGuavaCacheMemoizationConfig.memoizedUnderlyingGuavaCache
      .invalidateAll()
  }

  "Asynchronous memoization" should {
    "save the result to cache once the future is successful" in {
      val asyncService = new AsyncQueryMemoizeService()
      AsyncGuavaCacheMemoizationConfig.memoizedUnderlyingGuavaCache
        .size() shouldBe 0
      val userFuture = asyncService.getUser(100)
      // wait for the prev operation to complete and set to cache
      Thread.sleep(1100)
      userFuture.map { _ =>
        AsyncGuavaCacheMemoizationConfig.memoizedUnderlyingGuavaCache
          .size() shouldBe 1
      }

    }

    "get result from cache for future operation" in {
      val asyncService = new AsyncQueryMemoizeService()
      for {
        (main, memThread) <- asyncService.checkFutureThread(88)
      } yield {
        main should not be (memThread)
        AsyncGuavaCacheMemoizationConfig.memoizedUnderlyingGuavaCache
          .size() shouldBe 1
      }
    }

    "NOT save the result to cache if future is failed" in {
      val asyncService = new AsyncQueryMemoizeService()
      asyncService.getUserFail(100)
      AsyncGuavaCacheMemoizationConfig.memoizedUnderlyingGuavaCache
        .size() shouldBe 0
      // wait for the prev operation to complete and set to cache
      Thread.sleep(200)
      AsyncGuavaCacheMemoizationConfig.memoizedUnderlyingGuavaCache
        .size() shouldBe 0
      asyncService.getUserFail(100).failed.map { _ =>
        Thread.sleep(50)
        // increment twice since caching is not done due to failure
        asyncService.queryCount shouldBe 2
      }
    }
  }

}
