package com.baeldung.redis.ratelimit

import redis.clients.jedis.Jedis

import scala.concurrent.duration.Duration

class RateLimit(jedis: Jedis) {

  private final val existenceValue = "Exists"

  def setLimit(key: String, duration: Duration): Unit = {
    jedis.setex(key, duration.toSeconds, existenceValue)
  }

  def isLimited(key: String): Boolean = {
    jedis.exists(key)
  }

}
