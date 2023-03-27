package com.baeldung.redis.cache

import com.baeldung.redis.db.{Author, Book, VirtualDatabase}
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.scala.{
  ClassTagExtensions,
  DefaultScalaModule
}
import redis.clients.jedis.Jedis

class CacheThrough(jedis: Jedis, db: VirtualDatabase) {

  def books(): List[Book] =
    cachedLookup("books", () => db.books(), deserializeList[Book])

  def authors(): List[Author] =
    cachedLookup("authors", () => db.authors(), deserializeList[Author])

  private val mapper = JsonMapper
    .builder()
    .addModule(DefaultScalaModule)
    .build() :: ClassTagExtensions

  private def serialize(obj: AnyRef): String = {
    mapper.writeValueAsString(obj)
  }

  private def deserialize[A](obj: String)(implicit manifest: Manifest[A]): A = {
    mapper.readValue[A](obj)
  }

  private def deserializeList[A](
    obj: String
  )(implicit manifest: Manifest[A]): List[A] = {
    mapper.readValue[List[A]](obj)
  }

  private def cachedLookup[A <: AnyRef](
    key: String,
    backup: () => A,
    deserializer: String => A
  )(implicit manifest: Manifest[A]): A = {
    jedis.get(key) match {
      case null =>
        val backedUp = backup()
        jedis.set(key, serialize(backedUp))
        backedUp
      case a =>
        deserializer(a)
    }

  }

}
