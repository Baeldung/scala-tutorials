package com.baeldung.cache

import com.baeldung.cache.service.{
  GuavaCacheConfig,
  GuavaCacheCustomMemoizationKeyConfig,
  GuavaCacheMemoizationConfig,
  SyncQueryCustomMemoizeKeyService,
  SyncQueryMemoizeService,
  SyncQueryService,
  User
}
import org.scalatest.BeforeAndAfterEach
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.collection.JavaConverters._

class ScalaCacheSpec extends AnyWordSpec with Matchers with BeforeAndAfterEach {

  override def beforeEach() = {
    GuavaCacheConfig.underlyingGuavaCache.invalidateAll()
    GuavaCacheMemoizationConfig.memoizedUnderlyingGuavaCache.invalidateAll()
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
  }
}
