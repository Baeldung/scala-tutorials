package com.baeldung.cache

import com.baeldung.cache.service.{
  CacheFlagService,
  GuavaCacheConfig,
  GuavaCacheCustomMemoizationKeyConfig,
  GuavaCacheFlagConfig,
  GuavaCacheMemoizationConfig,
  SyncQueryCustomMemoizeKeyService,
  SyncQueryMemoizeService,
  SyncQueryService,
  User
}
import org.scalatest.BeforeAndAfterEach
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import scalacache.Flags

import scala.collection.JavaConverters._

class ScalaCacheSyncSpec
  extends AnyWordSpec
  with Matchers
  with BeforeAndAfterEach {

  override def beforeEach() = {
    GuavaCacheConfig.underlyingGuavaCache.invalidateAll()
    GuavaCacheMemoizationConfig.memoizedUnderlyingGuavaCache.invalidateAll()
    GuavaCacheCustomMemoizationKeyConfig.memoizedUnderlyingCustomKeyGuavaCache
      .invalidateAll()
    GuavaCacheFlagConfig.underlyingGuavaCacheForFlag.invalidateAll()
  }

  "Sync cache operations caching" should {
    "get from cache if the data already exist in cache" in {
      val sycQueryService = new SyncQueryService()
      sycQueryService.queryCount shouldBe 0
      GuavaCacheConfig.underlyingGuavaCache.size() shouldBe 0

      //query from db
      sycQueryService.getUser(9)
      sycQueryService.queryCount shouldBe 1
      GuavaCacheConfig.underlyingGuavaCache.asMap().keySet().size() shouldBe 1

      //query same user again
      sycQueryService.getUser(9)
      sycQueryService.queryCount shouldBe 1
      GuavaCacheConfig.underlyingGuavaCache.size() shouldBe 1

      //query for a new user
      sycQueryService.getUser(5)
      sycQueryService.queryCount shouldBe 2
      GuavaCacheConfig.underlyingGuavaCache.size() shouldBe 2
      GuavaCacheConfig.underlyingGuavaCache
        .asMap()
        .keySet()
        .asScala
        .toSet shouldBe Set(
        "user_9",
        "user_5"
      )

    }

    "get from cache using memoization if the data already exist in cache" in {
      val service = new SyncQueryMemoizeService()
      service.queryCount shouldBe 0
      GuavaCacheMemoizationConfig.memoizedUnderlyingGuavaCache.size() shouldBe 0

      //query from db
      service.getUser(1)
      service.queryCount shouldBe 1
      GuavaCacheMemoizationConfig.memoizedUnderlyingGuavaCache
        .asMap()
        .keySet()
        .size() shouldBe 1

      //query same user again
      service.getUser(1)
      service.queryCount shouldBe 1
      GuavaCacheMemoizationConfig.memoizedUnderlyingGuavaCache.size() shouldBe 1

      //query for a new user
      service.getUser(2)
      service.queryCount shouldBe 2
      GuavaCacheMemoizationConfig.memoizedUnderlyingGuavaCache.size() shouldBe 2
      GuavaCacheMemoizationConfig.memoizedUnderlyingGuavaCache
        .asMap()
        .keySet()
        .asScala
        .toSet shouldBe Set(
        "com.baeldung.cache.service.SyncQueryMemoizeService.getUser(1)",
        "com.baeldung.cache.service.SyncQueryMemoizeService.getUser(2)"
      )

    }

    "have memoization key WITHOUT the manifest field " in {
      val service = new SyncQueryMemoizeService()
      implicit val manifest: Manifest[User] =
        Manifest.classType[User](classOf[User])
      val user = service.getUserSpecial(1)
      GuavaCacheMemoizationConfig.memoizedUnderlyingGuavaCache
        .asMap()
        .keySet()
        .asScala
        .toSet shouldBe Set(
        "com.baeldung.cache.service.SyncQueryMemoizeService.getUserSpecial(1)()"
      )
    }

    "have custom memoization key WITHOUT full package path" in {
      val service = new SyncQueryCustomMemoizeKeyService()
      implicit val manifest: Manifest[User] =
        Manifest.classType[User](classOf[User])
      val user = service.getUserWithCustomKey(44, "F1")(10)
      GuavaCacheCustomMemoizationKeyConfig.memoizedUnderlyingCustomKeyGuavaCache
        .asMap()
        .keySet()
        .asScala
        .toSet shouldBe Set(
        "getUserWithCustomKey#44_F1-10"
      )
    }

    "NOT get from the cache and NOT set to cache if both the flags readsEnabled and writesEnabled" in {
      val service = new CacheFlagService()
      implicit val cacheDisableFlag = Flags(false, false)
      service.getWithFlag(10)
      GuavaCacheFlagConfig.underlyingGuavaCacheForFlag.size() shouldBe 0
      service.getWithFlag(10)
      GuavaCacheFlagConfig.underlyingGuavaCacheForFlag.size() shouldBe 0
      service.count shouldBe 2
    }

    "NOT get from the cache for first time, but set to cache if readsEnabled is false" in {
      val service = new CacheFlagService()
      implicit val cacheReadNormalFlag = Flags(true, true)
      service.getWithFlag(10)
      GuavaCacheFlagConfig.underlyingGuavaCacheForFlag.size() shouldBe 1
      service.count shouldBe 1
      // make the read flag as false, so that next call will act as cache miss
      val cacheReadDisableFlag = Flags(false, true)
      service.getWithFlag(10)(cacheReadDisableFlag)
      GuavaCacheFlagConfig.underlyingGuavaCacheForFlag.size() shouldBe 1
      // count should be 2 since the method will be invoked again
      service.count shouldBe 2
    }

    "always get from the actual method when writesEnabled is set to false" in {
      val service = new CacheFlagService()
      implicit val cacheMissFlag = Flags(true, false)
      service.getWithFlag(1)
      GuavaCacheFlagConfig.underlyingGuavaCacheForFlag.size() shouldBe 0
      service.getWithFlag(1)
      GuavaCacheFlagConfig.underlyingGuavaCacheForFlag.size() shouldBe 0
      service.getWithFlag(2)
      GuavaCacheFlagConfig.underlyingGuavaCacheForFlag.size() shouldBe 0
    }
  }
}
