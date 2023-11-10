package com.baeldung.redis.leaderboard

import com.baeldung.redis.leaderboard.model.EmployDB
import com.baeldung.redis.util.RedisSpec
import org.scalatest.flatspec.AnyFlatSpec

class LeaderboardSpec extends AnyFlatSpec with RedisSpec {

  "Leaderboard#count" should "return correct counts for Employees after plus one calls" in {
    val commitLeaderboardKey = "commits"
    val commitLeaderboard = new LeaderBoard(commitLeaderboardKey, getJedis())
    commitLeaderboard.plusOne(EmployDB.Emp1)
    commitLeaderboard.plusOne(EmployDB.Emp2)
    commitLeaderboard.plusOne(EmployDB.Emp3)
    commitLeaderboard.plusOne(EmployDB.Emp4)
    assert(commitLeaderboard.count(EmployDB.Emp1.firstLevelKey) === 3)
    assert(commitLeaderboard.count(EmployDB.Emp1.secondLevelKey) === 2)
    assert(commitLeaderboard.count(EmployDB.Emp1.thirdLevelKey) === 1)
  }

  "Leaderboard#count" should "return correct counts for Employees after plus N calls" in {
    val commitLeaderboardKey = "commits"
    val commitLeaderboard = new LeaderBoard(commitLeaderboardKey, getJedis())
    commitLeaderboard.plusN(EmployDB.Emp1, 3)
    commitLeaderboard.plusN(EmployDB.Emp2, 2)
    commitLeaderboard.plusN(EmployDB.Emp3, 12)
    commitLeaderboard.plusN(EmployDB.Emp5, 4)
    assert(commitLeaderboard.count(EmployDB.Emp1.firstLevelKey) === 17)
    assert(commitLeaderboard.count(EmployDB.Emp1.secondLevelKey) === 3)
    assert(commitLeaderboard.count(EmployDB.Emp2.secondLevelKey) === 6)
    assert(commitLeaderboard.count(EmployDB.Emp1.thirdLevelKey) === 3)
    assert(commitLeaderboard.count(EmployDB.Emp3.secondLevelKey) === 12)
  }

}
