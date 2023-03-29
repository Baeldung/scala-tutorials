package com.baeldung.redis.leaderboard.model

import com.baeldung.redis.leaderboard.LeaderboardKey

case class EmployeeKey(department: String, project: String, id: String)
  extends LeaderboardKey {
  override def firstLevelKey: String = department

  override def secondLevelKey: String = project

  override def thirdLevelKey: String = id
}
