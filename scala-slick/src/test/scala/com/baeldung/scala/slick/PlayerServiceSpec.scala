package com.baeldung.scala.slick

import java.sql.SQLException
import java.time.LocalDate

import org.scalatest.concurrent.PatienceConfiguration.Timeout
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpec}
import slick.jdbc.H2Profile.api._

import scala.concurrent.duration._

/**
 * Created by yadu on 05/08/20
 */

class PlayerServiceSpec extends WordSpec with Matchers with ScalaFutures with BeforeAndAfterAll {

  val playerService = new PlayerService
  val timeout       = Timeout(1.second)

  "PlayerService" should {
    "create table for Player" in {
      val createResult = playerService.createTable.futureValue(timeout)
      createResult should be >= 0
    }

    "insert a player to the table" in {
      val federer      = Player(0L, "Federer", "Switzerland", Some(LocalDate.parse("1981-08-08")))
      val insertStatus = playerService.insertPlayer(federer).futureValue(timeout)
      insertStatus shouldBe 1
    }

    "get inserted record from database" in {
      val allPlayers = playerService.getAllPlayers.futureValue(timeout)
      allPlayers.size shouldBe 1
      allPlayers.head.name shouldBe "Federer"
      allPlayers.head.id should not be 0 //to verify auto-increment for primary key
    }

    "insert multiple players together" in {
      val player1              = Player(0L, "Nadal", "Spain", Some(LocalDate.parse("1986-06-03")))
      val player2              = Player(0L, "Serena", "USA", None)
      val insertMultipleResult = playerService.insertPlayers(Seq(player1, player2)).futureValue(timeout)
      insertMultipleResult should contain(2)
      val playersFromDB = playerService.getAllPlayers.futureValue(timeout)
      playersFromDB.map(_.name) should contain allElementsOf (Seq("Nadal", "Federer", "Serena"))
      playersFromDB.map(_.id).forall(_ > 0) shouldBe true //verify auto-increment field
    }

    "update DoB field of a player" in {
      val serenaOpt = playerService.filterByName("Serena").futureValue(timeout).headOption
      serenaOpt should not be empty
      val newDoB       = LocalDate.parse("1981-09-26")
      val updateResult = playerService.updateDob(serenaOpt.get.id, newDoB).futureValue(timeout)
      updateResult shouldBe 1
      val serenaOptAgain = playerService.getAllPlayers.futureValue(timeout).find(_.name == "Serena")
      serenaOptAgain.flatMap(_.dob) should contain(newDoB)
    }

    "delete a player from the table by name" in {
      val deleteStatus = playerService.deleteByName("Nadal").futureValue(timeout)
      deleteStatus shouldBe 1
      val nadal = playerService.filterByName("Nadal").futureValue(timeout)
      nadal shouldBe empty
    }

    "combine multiple actions together and execute" in {
      val player         = Player(0L, "Murray", "Britain", None)
      val insertAction   = playerService.playerTable += player
      val updateAction   = playerService.playerTable.filter(_.name === "Federer").map(_.country).update("Swiss")
      val combinedAction = DBIO.seq(insertAction, updateAction)
      playerService.db.run[Unit](combinedAction.transactionally).futureValue(timeout)
      val allPlayers = playerService.getAllPlayers.futureValue(timeout)
      allPlayers.map(_.name) should contain allElementsOf (Seq("Federer", "Serena", "Murray"))
      allPlayers.find(_.name == "Federer").map(_.country) should contain("Swiss")
    }

    "rollback the entire transaction if a failure occur" in {
      val player1           = Player(100L, "Steffi", "Germany", None)
      val player2           = Player(100L, "Sharapova", "Russia", None)
      //doing force insert to make the second insertion fail
      val insertAction1     = playerService.playerTable.forceInsert(player1)
      val insertAction2     = playerService.playerTable.forceInsert(player2)
      val transactionAction = insertAction1.andThen(insertAction2)
      val result            = playerService.db.run(transactionAction.transactionally)
      whenReady(result.failed){ ex =>
        ex shouldBe a[SQLException]
        ex.getMessage should include("primary key violation")
        val allPlayers = playerService.getAllPlayers.futureValue(timeout)
        allPlayers.map(_.name) should contain noneOf("Steffi", "Sharapova")
      }

    }
  }

}
