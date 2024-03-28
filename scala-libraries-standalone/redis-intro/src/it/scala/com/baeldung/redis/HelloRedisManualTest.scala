package com.baeldung.redis

import com.baeldung.redis.util.RedisManualTest
import org.scalatest.flatspec.AnyFlatSpec

class HelloRedisManualTest extends AnyFlatSpec with RedisManualTest {

  "The Hello Redis object" should "retrieve stored values" in {
    getJedis().set("key1", "value1")
    assert(getJedis().get("key1") === "value1")
  }

}
