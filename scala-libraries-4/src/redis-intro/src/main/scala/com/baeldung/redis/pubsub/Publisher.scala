package com.baeldung.redis.pubsub

import com.baeldung.redis.util.WithRedis
import redis.clients.jedis.Jedis

class Publisher(jedis: Jedis) {

  def publish(channel: String, message: String): Unit = {
    jedis.publish(channel, message)
  }

}

object Publisher extends App with WithRedis {

  initialize()
  val channel = "channel1"
  val publisher = new Publisher(getJedis())
  publisher.publish(channel, "my message")
  destroy()

}
