package com.baeldung.redis.ratelimit

import com.baeldung.redis.util.RedisManualTest
import org.scalatest.flatspec.AnyFlatSpec

import scala.concurrent.duration._
import scala.language.postfixOps

class RateLimitManualTest extends AnyFlatSpec with RedisManualTest {

  "RateLimit" should "respond with not allowed for the given duration" in {
    val limitedKey = "limited-key"
    val limitDuration = 5 seconds
    val rateLimit = new RateLimit(getJedis())
    rateLimit.setLimit(limitedKey, limitDuration)
    assert(rateLimit.isLimited(limitedKey) === true)
    Thread.sleep(limitDuration.toMillis)
    assert(rateLimit.isLimited(limitedKey) === false)
  }

}
