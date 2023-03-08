package com.baeldung.cache.service

import scalacache.Cache
import scalacache.memoization.memoizeSync

import scala.concurrent.duration._
import scalacache.modes.sync._

class GenericCacheService(implicit val cache: Cache[User]) {

  var queryCount = 0

  def getUser(userId: Long): User =
    memoizeSync(Some(10.seconds)) {
      queryUserFromDB(userId)
    }

  private def queryUserFromDB(userId: Long): User = {
    val user = User(userId, "Messi")
    queryCount = queryCount + 1
    user
  }
}
