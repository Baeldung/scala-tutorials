package com.baeldung.cache.service

import com.google.common.cache.CacheBuilder
import scalacache.guava.GuavaCache
import scalacache.memoization.{cacheKeyExclude, memoizeSync}
import scalacache.{Cache, Entry, Flags}
import scalacache.modes.sync._

object GuavaCacheFlagConfig {
  val underlyingGuavaCacheForFlag =
    CacheBuilder.newBuilder().maximumSize(10000L).build[String, Entry[User]]

  implicit val guavaCache: Cache[User] = GuavaCache(underlyingGuavaCacheForFlag)
}

class CacheFlagService {
  import GuavaCacheFlagConfig._
  var count = 0

  def getWithFlag(id: Long)(implicit @cacheKeyExclude flags: Flags): User = {
    memoizeSync(None) {
      count = count + 1
      User(id, "User With Flag")
    }
  }
}
