package com.baeldung.cache.service
import scalacache._
import scalacache.guava._
import com.google.common.cache.CacheBuilder
import scalacache.modes.sync._
import scalacache.serialization.binary._

object GuavaCacheConfig {
  val underlyingGuavaCache =
    CacheBuilder.newBuilder().maximumSize(10000L).build[String, Entry[User]]
  implicit val guavaCache: Cache[User] = GuavaCache(underlyingGuavaCache)
}

class SyncQueryService {
  import GuavaCacheConfig._

  def buildUserKey(id: Long) = "user_" + id
  var queryCount = 0

  def getUser(userId: Long): User = {
    val cacheResult = get(buildUserKey(userId))
    if (cacheResult.isEmpty) {
      val fromDB = queryUserFromDB(userId)
      put(buildUserKey(userId))(fromDB)
      fromDB
    } else cacheResult.get
  }

  private def queryUserFromDB(userId: Long): User = {
    val user = User(userId, "Messi")
    queryCount = queryCount + 1
    user
  }

}
