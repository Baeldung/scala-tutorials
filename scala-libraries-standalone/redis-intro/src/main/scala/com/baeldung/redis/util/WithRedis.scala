package com.baeldung.redis.util

import redis.clients.jedis.Jedis

trait WithRedis {

  private var clients: RedisClients = scala.compiletime.uninitialized

  protected def initialize(): Unit = {
    clients = new RedisClients("localhost", 6379)
  }

  protected def destroy(): Unit = {
    clients.close
  }

  protected def getJedis(): Jedis = clients.jedis

}
