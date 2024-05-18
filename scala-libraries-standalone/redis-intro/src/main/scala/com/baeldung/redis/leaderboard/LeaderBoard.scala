package com.baeldung.redis.leaderboard

import redis.clients.jedis.Jedis

import java.util.UUID

class LeaderBoard(hllKey: String, jedis: Jedis) {

  def plusOne(key: LeaderboardKey): Unit = {
    val randValue = random()
    jedis.pfadd(s"$hllKey:${key.firstLevelKey}", randValue)
    jedis.pfadd(s"$hllKey:${key.secondLevelKey}", randValue)
    jedis.pfadd(s"$hllKey:${key.thirdLevelKey}", randValue)
  }

  def plusN(key: LeaderboardKey, n: Int): Unit = {
    val randValues = (0 until n) map (_ => random())
    jedis.pfadd(s"$hllKey:${key.firstLevelKey}", randValues*)
    jedis.pfadd(s"$hllKey:${key.secondLevelKey}", randValues*)
    jedis.pfadd(s"$hllKey:${key.thirdLevelKey}", randValues*)
  }

  def count(key: String): Long = {
    jedis.pfcount(s"$hllKey:$key")
  }

  private def random(): String = UUID.randomUUID().toString

}

trait LeaderboardKey {
  def firstLevelKey: String

  def secondLevelKey: String

  def thirdLevelKey: String
}
