package com.baeldung.redis

import com.baeldung.redis.util.RedisSpec
import org.scalatest.flatspec.AnyFlatSpec

class HelloRedisSpec extends AnyFlatSpec with RedisSpec {

  "The Hello Redis object" should "retrieve stored values" in {
    getJedis().set("key1", "value1")
    assert(getJedis().get("key1") === "value1")
  }

}
