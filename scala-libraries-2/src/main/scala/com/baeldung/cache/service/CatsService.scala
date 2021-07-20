package com.baeldung.cache.service

import cats.effect.IO
import com.google.common.cache.CacheBuilder
import scalacache._
import scalacache.memoization._
import scalacache.guava.GuavaCache
import scala.concurrent.duration._

object GuavaCacheCatsConfig {
  val underlyingGuavaCacheCats =
    CacheBuilder.newBuilder().maximumSize(10000L).build[String, Entry[User]]

  implicit val guavaCache: Cache[User] = GuavaCache(
    underlyingGuavaCacheCats
  )
}

class CatsService {
  import GuavaCacheCatsConfig._
  implicit val mode: Mode[IO] = scalacache.CatsEffect.modes.async
  var count = 0

  def getUserPure(id: Long): IO[User] =
    memoizeF(None) {
      count = count + 1
      IO.pure(User(id, "cats-pure"))
    }

  def getUserIO(id: Long): IO[User] =
    memoizeF(None) {
      count = count + 1
      IO {
        // do some db queries here
        User(id, "db query result")
      }
    }

}
