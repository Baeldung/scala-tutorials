package com.baeldung.cache.service

import scalacache._
import scalacache.guava._
import com.google.common.cache.CacheBuilder
import scalacache.modes.scalaFuture._
import scalacache.serialization.binary._
import scalacache.memoization._
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future
import scala.concurrent.duration._

object AsyncGuavaCacheMemoizationConfig {
  val memoizedUnderlyingGuavaCache =
    CacheBuilder.newBuilder().maximumSize(10000L).build[String, Entry[User]]
  implicit val guavaCache: Cache[User] = GuavaCache(
    memoizedUnderlyingGuavaCache
  )
}

class AsyncQueryMemoizeService {
  import AsyncGuavaCacheMemoizationConfig._

  var queryCount = 0

  def getUser(userId: Long): Future[User] =
    memoizeF[Future, User](Some(10.seconds)) {
      queryUserFromDB(userId)
    }

  def getUserFail(userId: Long): Future[User] =
    memoizeF[Future, User](Some(10.seconds)) {
      queryCount = queryCount + 1
      Future.failed[User](new Exception("query failed"))
    }

  private def queryUserFromDB(userId: Long): Future[User] = {
    Future {
      Thread.sleep(1000)
      val user = User(userId, "Ronaldo")
      queryCount = queryCount + 1
      user
    }
  }

}
