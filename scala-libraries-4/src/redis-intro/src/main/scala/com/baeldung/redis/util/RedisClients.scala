package com.baeldung.redis.util

import redis.clients.jedis.{Jedis, JedisPool}

class RedisClients(host: String, port: Int) {

  private val pool = new JedisPool(host, port)

  def jedis: Jedis = pool.getResource

  def close: Unit = pool.close()

}
