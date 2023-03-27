package com.baeldung.redis.pubsub

import com.baeldung.redis.util.WithRedis
import redis.clients.jedis.JedisPubSub

object Subscriber extends App with WithRedis {

  initialize()

  val channel = "channel1"
  getJedis().subscribe(
    new JedisPubSub {
      override def onMessage(channel: String, message: String): Unit = {
        println(s"Got message: $message on channel: $channel")
      }
    },
    channel
  )

  destroy()

}
