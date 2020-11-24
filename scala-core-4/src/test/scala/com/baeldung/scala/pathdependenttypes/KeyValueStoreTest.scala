package com.baeldung.scala.pathdependenttypes

import org.scalatest.{FlatSpec, Matchers}

class KeyValueStoreTest extends FlatSpec with Matchers {

  "KeyValueStore" should "be able to store typed key with specify the type of the value of that key" in {
    val db = Database()
    val k = Database.key[String]("key")

    db.set(k)("Hello")
    assert(db.get(Database.key[String]("key")).exists(_.isInstanceOf[String]))
  }
}
