package com.baeldung.cache.service

import com.google.common.cache.CacheBuilder
import scalacache._
import scalacache.guava._
import scalacache.memoization._
import scalacache.modes.sync._

import scala.concurrent.duration._

object CustomKeyGenerator extends MethodCallToStringConverter {
  override def toString(
    fullClassName: String,
    constructorParamss: IndexedSeq[IndexedSeq[Any]],
    methodName: String,
    paramss: IndexedSeq[IndexedSeq[Any]]
  ): String = {
    val keyPart = paramss
      .map { methParams =>
        methParams.map(_.toString).mkString("_")
      }
      .mkString("-")
    methodName + "#" + keyPart
  }
}

object GuavaCacheCustomMemoizationKeyConfig {
  val memoizedUnderlyingCustomKeyGuavaCache =
    CacheBuilder.newBuilder().maximumSize(10000L).build[String, Entry[User]]

  implicit val customKeyCacheConfig = CacheConfig(memoization =
    MemoizationConfig(toStringConverter = CustomKeyGenerator)
  )

  implicit val guavaCache: Cache[User] = GuavaCache(
    memoizedUnderlyingCustomKeyGuavaCache
  )
}

class SyncQueryCustomMemoizeKeyService {
  import GuavaCacheCustomMemoizationKeyConfig._

  var queryCount = 0

  def getUserWithCustomKey(
    id: Long,
    foo: String
  )(bar: Int): User =
    memoizeSync(Some(10.seconds)) {
      queryUserFromDB(id)
    }

  private def queryUserFromDB(userId: Long): User = {
    val user = User(userId, "Ronaldo")
    queryCount = queryCount + 1
    user
  }

}
