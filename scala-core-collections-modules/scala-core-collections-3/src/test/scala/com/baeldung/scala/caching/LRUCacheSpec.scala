package com.baeldung.scala.caching

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class LRUCacheSpec extends AnyFlatSpec with Matchers:

  "An LRU Cache" should "throw IllegalArgumentException if created with zero or negative size" in {
    an[IllegalArgumentException] should be thrownBy {
      new LRUCache[Int, String](0)
    }

    an[IllegalArgumentException] should be thrownBy {
      new LRUCache[Int, String](-1)
    }
  }

  it should "handle a cache size of 1 correctly" in {
    val cache = new LRUCache[String, Int](1)
    cache.put("key1", 1)
    cache.get("key1") should be(Some(1))

    // Adding another element should evict "key1"
    cache.put("key2", 2)
    cache.get("key1") should be(None)
    cache.get("key2") should be(Some(2))

    // Accessing "key2" and then adding a new key should evict "key2"
    cache.get("key2")
    cache.put("key3", 3)
    cache.get("key2") should be(None)
    cache.get("key3") should be(Some(3))
  }

  it should "return None for missing values" in {
    val cache = new LRUCache[String, Int](2)
    cache.get("missing") should be(None)
  }

  it should "return the value for a key if present" in {
    val cache = new LRUCache[String, Int](2)
    cache.put("key1", 1)
    cache.get("key1") should be(Some(1))
  }

  it should "evict the least recently used item when exceeding maxSize" in {
    val cache = new LRUCache[String, Int](2)
    cache.put("key1", 1)
    cache.put("key2", 2)
    cache.put("key3", 3) // This should evict "key1"

    cache.get("key1") should be(None)
    cache.get("key2") should be(Some(2))
    cache.get("key3") should be(Some(3))
  }

  it should "update the position of a key when accessed" in {
    val cache = new LRUCache[String, Int](2)
    cache.put("key1", 1)
    cache.put("key2", 2)
    cache.get("key1") // This should move "key1" to the end
    cache.put("key3", 3) // This should evict "key2"

    cache.get("key1") should be(Some(1))
    cache.get("key2") should be(None)
    cache.get("key3") should be(Some(3))
  }

  it should "update the position of a key when updated" in {
    val cache = new LRUCache[String, Int](2)
    cache.put("key1", 1)
    cache.put("key2", 2)
    cache.put(
      "key1",
      10
    ) // This should update "key1" value and move it to the end
    cache.put("key3", 3) // This should evict "key2"

    cache.get("key1") should be(Some(10))
    cache.get("key2") should be(None)
    cache.get("key3") should be(Some(3))
  }
