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

@Ignore //fixing in JAVA-11236
class ScalaCacheCachingBlockUnitTest
  extends AnyWordSpec
  with Matchers
  with BeforeAndAfterEach {

  override def beforeEach(): Unit = {
    GuavaCacheCachingBlockConfig.underlyingGuavaCacheForCachingBlock
      .invalidateAll()
  }

  "ScalaCacheCachingBlockSpec" should {
    "execute the method inside the caching block and set the value to cache" in {
      val service = new ScalaCacheCachingBlockSyncService()
      service.getUser(22)
      GuavaCacheCachingBlockConfig.underlyingGuavaCacheForCachingBlock
        .size() shouldBe 1
      GuavaCacheCachingBlockConfig.underlyingGuavaCacheForCachingBlock
        .asMap()
        .keySet()
        .asScala
        .toSet shouldBe Set("id:22")
    }

    "execute the method inside the caching block and set the value to cache with more than 1 keyparts" in {
      val service = new ScalaCacheCachingBlockSyncService()
      service.getUserWithMoreKeys(22)
      GuavaCacheCachingBlockConfig.underlyingGuavaCacheForCachingBlock
        .size() shouldBe 1
      GuavaCacheCachingBlockConfig.underlyingGuavaCacheForCachingBlock
        .asMap()
        .keySet()
        .asScala
        .toSet shouldBe Set("id:22:cache:key")
    }

    "execute the method inside the caching block and set the value to cache when future is done" in {
      val service = new ScalaCacheCachingBlockAsyncService()
      service.getUserFuture(42)
      GuavaCacheCachingBlockConfig.underlyingGuavaCacheForCachingBlock
        .size() shouldBe 0
      Thread.sleep(1100)
      GuavaCacheCachingBlockConfig.underlyingGuavaCacheForCachingBlock
        .size() shouldBe 1
      GuavaCacheCachingBlockConfig.underlyingGuavaCacheForCachingBlock
        .asMap()
        .keySet()
        .asScala
        .toSet shouldBe Set("keyF:42")
    }
  }

}
