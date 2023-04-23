package com.baeldung.cache.service

import cats.effect.IO
import com.google.common.cache.CacheBuilder
import scalacache._
import scalacache.memoization._
import scalacache.guava.GuavaCache

class GuavaCacheCatsConfig {
  val underlyingGuavaCacheCats =
    CacheBuilder.newBuilder().maximumSize(10000L).build[String, Entry[User]]

  implicit val guavaCache: Cache[User] = GuavaCache(
    underlyingGuavaCacheCats
  )
}

class CatsService(config: GuavaCacheCatsConfig) {
  import config._
  implicit val mode: Mode[IO] = scalacache.CatsEffect.modes.async
  var count = 0

  def getUserPure(id: Long): IO[User] =
    memoizeF(None) {
      count = count + 1
      IO.pure(User(id, "cats-pure"))
    }

  def getUserCatsIO(id: Long): IO[User] =
    memoize[IO, User](None) {
      User(id, "io-user")
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
