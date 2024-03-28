package com.baeldung.cache

import com.baeldung.cache.service.{
  GuavaCacheCachingBlockConfig,
  ScalaCacheCachingBlockAsyncService,
  ScalaCacheCachingBlockSyncService
}
import org.scalatest.BeforeAndAfterEach
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.Ignore

import scala.collection.JavaConverters.collectionAsScalaIterableConverter

class ScalaCacheCachingBlockUnitTest
  extends AnyWordSpec
  with Matchers
  with BeforeAndAfterEach {

  "ScalaCacheCachingBlockSpec" should {
    "execute the method inside the caching block and set the value to cache" in {
      val config = new GuavaCacheCachingBlockConfig
      val service = new ScalaCacheCachingBlockSyncService(config)
      service.getUser(22)
      config.underlyingGuavaCacheForCachingBlock
        .size() shouldBe 1
      config.underlyingGuavaCacheForCachingBlock
        .asMap()
        .keySet()
        .asScala
        .toSet shouldBe Set("id:22")
    }

    "execute the method inside the caching block and set the value to cache with more than 1 keyparts" in {
      val config = new GuavaCacheCachingBlockConfig
      val service = new ScalaCacheCachingBlockSyncService(config)
      service.getUserWithMoreKeys(22)
      config.underlyingGuavaCacheForCachingBlock
        .size() shouldBe 1
      config.underlyingGuavaCacheForCachingBlock
        .asMap()
        .keySet()
        .asScala
        .toSet shouldBe Set("id:22:cache:key")
    }

    "execute the method inside the caching block and set the value to cache when future is done" in {
      val config = new GuavaCacheCachingBlockConfig
      val service = new ScalaCacheCachingBlockAsyncService(config)
      service.getUserFuture(42)
      config.underlyingGuavaCacheForCachingBlock
        .size() shouldBe 0
      Thread.sleep(1100)
      config.underlyingGuavaCacheForCachingBlock
        .size() shouldBe 1
      config.underlyingGuavaCacheForCachingBlock
        .asMap()
        .keySet()
        .asScala
        .toSet shouldBe Set("keyF:42")
    }
  }

}
