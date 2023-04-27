package com.baeldung.cache

import com.baeldung.cache.service.{
  AsyncGuavaCacheMemoizationConfig,
  AsyncQueryMemoizeService
}
import org.scalatest.BeforeAndAfterEach
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AsyncWordSpec

class ScalaCacheAsyncUnitTest
  extends AsyncWordSpec
  with Matchers
  with BeforeAndAfterEach {

  "Asynchronous memoization" should {
    "save the result to cache once the future is successful" in {
      val config = new AsyncGuavaCacheMemoizationConfig
      val asyncService = new AsyncQueryMemoizeService(config)
      config.memoizedUnderlyingGuavaCache
        .size() shouldBe 0
      val userFuture = asyncService.getUser(100)
      // wait for the prev operation to complete and set to cache
      Thread.sleep(1100)
      userFuture.map { _ =>
        config.memoizedUnderlyingGuavaCache
          .size() shouldBe 1
      }

    }

    "get result from cache for future operation" in {
      val config = new AsyncGuavaCacheMemoizationConfig
      val asyncService = new AsyncQueryMemoizeService(config)
      for {
        (main, memThread) <- asyncService.checkFutureThread(88)
      } yield {
        main should not be (memThread)
        config.memoizedUnderlyingGuavaCache
          .size() shouldBe 1
      }
    }

    "NOT save the result to cache if future is failed" in {
      val config = new AsyncGuavaCacheMemoizationConfig
      val asyncService = new AsyncQueryMemoizeService(config)
      asyncService.getUserFail(100)
      config.memoizedUnderlyingGuavaCache
        .size() shouldBe 0
      // wait for the prev operation to complete and set to cache
      Thread.sleep(200)
      config.memoizedUnderlyingGuavaCache
        .size() shouldBe 0
      asyncService.getUserFail(100).failed.map { _ =>
        Thread.sleep(50)
        // increment twice since caching is not done due to failure
        asyncService.queryCount shouldBe 2
      }
    }
  }

}
