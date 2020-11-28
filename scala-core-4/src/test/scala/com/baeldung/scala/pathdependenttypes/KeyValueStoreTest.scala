package com.baeldung.scala.pathdependenttypes

import org.scalatest.{FlatSpec, Matchers}

class KeyValueStoreTest extends FlatSpec with Matchers {

  "KeyValueStore" should "be able to store typed key with specify the type of the value of that key" in {
    val db = Database()
    val k1 = Database.key[String]("key1")
    val k2 = Database.key[Double]("key2")

    db.set(k1)("One")
    db.set(k2)(1.0)
    assert(db.get(k1).contains("One"))
    assert(db.get(k2).contains(1.0))
  }
}
