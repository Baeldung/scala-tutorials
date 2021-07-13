package com.baeldung.cache.service

import com.google.common.cache.CacheBuilder
import scalacache.{Cache, Entry, Id, caching, cachingF}
import scalacache.guava.GuavaCache

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object GuavaCacheCachingBlockConfig {
  val underlyingGuavaCacheForCachingBlock =
    CacheBuilder.newBuilder().maximumSize(10000L).build[String, Entry[User]]

  implicit val guavaCache: Cache[User] = GuavaCache(
    underlyingGuavaCacheForCachingBlock
  )
}

class ScalaCacheCachingBlockSyncService {
  import GuavaCacheCachingBlockConfig._
  import scalacache.modes.sync._

  def getUser(id: Long) = {
    caching("id", id)(None) {
      queryResult(id)
    }
  }

  private def queryResult(id: Long): User = {
    User(id, "caching block")
  }

}

class ScalaCacheCachingBlockAsyncService {
  import scalacache.modes.scalaFuture._
  import GuavaCacheCachingBlockConfig._

  def getUserFuture(id: Long) = {
    cachingF("keyF", id)(None) {
      queryResultFuture(id)
    }
  }

  private def queryResultFuture(id: Long): Future[User] = {
    Future {
      Thread.sleep(1000)
      User(id, "caching block")
    }

  }
}
