package com.baeldung.cache

import com.baeldung.cache.service.{CaffeineCacheConfig, GenericCacheService}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import scala.collection.JavaConverters.collectionAsScalaIterableConverter

class GenericCacheUnitTest extends AnyWordSpec with Matchers {

  "Generic Cache Service" should {
    "work for caffeine cache with caffeine cache implicit" in {
      import com.baeldung.cache.service.CaffeineCacheConfig.caffeineCache
      val caffeineCacheService = new GenericCacheService()
      val user = caffeineCacheService.getUser(100)
      user.name shouldBe "Messi"
      caffeineCacheService.queryCount shouldBe 1
      val user2 = caffeineCacheService.getUser(100)
      caffeineCacheService.queryCount shouldBe 1
      CaffeineCacheConfig.underlyingCaffeineCache
        .asMap()
        .keySet()
        .asScala
        .toSet shouldBe Set(
        "com.baeldung.cache.service.GenericCacheService.getUser(100)"
      )
    }

    "work for guava cache with guava cache implicit" in {
      import com.baeldung.cache.service.GuavaCacheMemoizationConfig.guavaCache
      val guavaCacheService = new GenericCacheService()
      val user = guavaCacheService.getUser(100)
      user.name shouldBe "Messi"
      guavaCacheService.queryCount shouldBe 1
      val user2 = guavaCacheService.getUser(100)
      guavaCacheService.queryCount shouldBe 1
      CaffeineCacheConfig.underlyingCaffeineCache
        .asMap()
        .keySet()
        .asScala
        .toSet shouldBe Set(
        "com.baeldung.cache.service.GenericCacheService.getUser(100)"
      )
    }
  }

}
