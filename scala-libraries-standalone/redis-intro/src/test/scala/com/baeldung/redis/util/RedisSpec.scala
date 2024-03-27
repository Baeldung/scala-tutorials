package com.baeldung.redis.util

import org.scalatest.{BeforeAndAfter, BeforeAndAfterEach, Suite}
import redis.clients.jedis.args.FlushMode

trait RedisSpec extends WithRedis with BeforeAndAfter with BeforeAndAfterEach {

  this: Suite =>

  before {
    initialize()
  }

  after {
    getJedis().flushAll(FlushMode.SYNC)
    destroy()
  }

}
