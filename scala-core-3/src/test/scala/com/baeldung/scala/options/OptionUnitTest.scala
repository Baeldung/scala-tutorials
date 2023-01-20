package com.baeldung.scala.options

import org.scalatest.{Matchers, WordSpec}

class OptionUnitTest extends WordSpec with Matchers {
  val tournament: Tournament = new Tournament {
    private val scores = Map("TeamA" -> 11, "TeamB" -> 3, "TeamC" -> 19)

    override def getTopScore(team: String): Option[Int] = scores.get(team)
  }

  val player1: Player = new Player {
    override val name: String = "Player 1"
    override def getFavoriteTeam: Option[String] = Some("TeamA")
  }

  val player2: Player = new Player {
    override val name: String = "Player 2"
    override def getFavoriteTeam: Option[String] = Some("TeamC")
  }

  val player3: Player = new Player {
    override val name: String = "Player 3"
    override def getFavoriteTeam: Option[String] = None
  }

  val player4: Player = new Player {
    override val name: String = "Player 4"
    override def getFavoriteTeam: Option[String] = None
  }

  "Player High Scores" should {
    "Player 1 over Player 2" in {
      OptionExample.whoHasTopScoringTeam(player1, player2, tournament).foreach {
        case (winningPlayer, winningScore) =>
          assert(winningPlayer == player2)
          assert(winningScore == 19)
      }
    }

    "Player 1 over Player 3" in {
      OptionExample.whoHasTopScoringTeam(player1, player3, tournament).foreach {
        case (winningPlayer, winningScore) =>
          assert(winningPlayer == player1)
          assert(winningScore == 11)
      }
    }

    "Player 1 over Player 3 (reverse order of parameters)" in {
      OptionExample.whoHasTopScoringTeam(player3, player1, tournament).foreach {
        case (winningPlayer, winningScore) =>
          assert(winningPlayer == player1)
          assert(winningScore == 11)
      }
    }
    "Player 3 draws Player 4 (neither has favorite teams)" in {
      assert(
        OptionExample.whoHasTopScoringTeam(player3, player4, tournament).isEmpty
      )
    }
  }

  "Option Examples" should {
    "Successfully map" in {
      val o1: Option[Int] = Some(10)
      assert(o1.map(_.toString).contains("10"))
      assert(o1.map(_ * 2.0).contains(20))

      val o2: Option[Int] = None
      assert(o2.map(_.toString).isEmpty)
    }

    "Demonstrate flow control" in {
      val o1: Option[Int] = Some(10)
      val o2: Option[Int] = None

      def times2(n: Int): Int = n * 2

      assert(o1.map(times2).contains(20))
      o2.map(times2)
      assert(o2.map(times2).isEmpty)
    }
  }
}
