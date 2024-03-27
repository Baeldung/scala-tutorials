package com.baeldung.cache.service

import com.github.benmanes.caffeine.cache.Caffeine
import scalacache.caffeine.CaffeineCache
import scalacache.{Cache, Entry}

object CaffeineCacheConfig {
  val underlyingCaffeineCache =
    Caffeine.newBuilder().maximumSize(10000L).build[String, Entry[User]]

  implicit val caffeineCache: Cache[User] = CaffeineCache(
    underlyingCaffeineCache
  )

}
