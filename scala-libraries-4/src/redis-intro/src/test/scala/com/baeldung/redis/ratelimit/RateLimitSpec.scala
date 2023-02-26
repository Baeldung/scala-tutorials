package com.baeldung.redis.ratelimit

import com.baeldung.redis.util.RedisSpec
import org.scalatest.flatspec.AnyFlatSpec

import scala.concurrent.duration._
import scala.language.postfixOps

class RateLimitSpec extends AnyFlatSpec with RedisSpec {

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
