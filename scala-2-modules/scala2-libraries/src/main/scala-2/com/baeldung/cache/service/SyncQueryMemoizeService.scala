package com.baeldung.cache.service

import com.google.common.cache.CacheBuilder
import scalacache._
import scalacache.guava._
import scalacache.memoization._

import scala.concurrent.duration._
import scala.util.Try

object GuavaCacheMemoizationConfig {
  val memoizedUnderlyingGuavaCache =
    CacheBuilder.newBuilder().maximumSize(10000L).build[String, Entry[User]]
  implicit val guavaCache: Cache[User] = GuavaCache(
    memoizedUnderlyingGuavaCache
  )
}

class SyncQueryMemoizeService {
  import GuavaCacheMemoizationConfig._
  import scalacache.modes.sync._

  var queryCount = 0

  def getUser(userId: Long): User =
    memoizeSync(Some(10.seconds)) {
      queryUserFromDB(userId)
    }

  def getUserSpecial(
    id: Long
  )(implicit @cacheKeyExclude manifest: Manifest[User]): User =
    memoizeSync(Some(10.seconds)) {
      queryUserFromDB(id)
    }

  private def queryUserFromDB(userId: Long): User = {
    val user = User(userId, "Ronaldo")
    queryCount = queryCount + 1
    user
  }

}

class TryMemoizeService {
  import GuavaCacheMemoizationConfig._
  import scalacache.modes.try_._

  var queryCount = 0
  var failQueryCount = 0

  def getUserTry(userId: Long): Try[User] =
    memoize[Try, User](None) {
      val user = User(userId, "Ronaldo")
      queryCount = queryCount + 1
      user
    }

  def getUserTryFailure(userId: Long): Try[User] =
    memoize[Try, User](None) {
      failQueryCount = failQueryCount + 1
      throw new Exception("failed: " + userId)
    }
}
