package com.baeldung.scala.slick

import java.sql.SQLException
import java.time.LocalDate

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{AsyncWordSpec, BeforeAndAfterAll, Matchers}
import slick.jdbc.H2Profile.api._

import scala.concurrent.Await
import scala.concurrent.duration._

class PlayerServiceSpec extends AsyncWordSpec with Matchers with ScalaFutures with BeforeAndAfterAll {

  val playerService = new PlayerService

  override def beforeAll(): Unit = {
    // This call should be sync, because it is a setup phase
    // and tests could start only after the table creation
    Await.result(playerService.createTable, 10.seconds)
  }

"PlayerService" should {
    "insert a player to the table" in {
      val federer      = Player(0L, "Federer", "Switzerland", Some(LocalDate.parse("1981-08-08")))

      playerService.insertPlayer(federer) map { insertStatus =>
        insertStatus shouldBe 1
      }
    }

    "get inserted record from database" in {
      playerService.getAllPlayers map { allPlayers =>
        allPlayers.size shouldBe 1
        allPlayers.head.name shouldBe "Federer"
        allPlayers.head.id should not be 0 //to verify auto-increment for primary key
      }
    }

    "insert multiple players together" in {
      val player1              = Player(0L, "Nadal", "Spain", Some(LocalDate.parse("1986-06-03")))
      val player2              = Player(0L, "Serena", "USA", None)

      playerService.insertPlayers(Seq(player1, player2)) flatMap { insertMultipleResult =>
        insertMultipleResult should contain(2)

        // Sequential call, should be made after insertion
        playerService.getAllPlayers map { playersFromDB =>
          playersFromDB.map(_.name) should contain allElementsOf(Seq("Nadal", "Federer", "Serena"))
          playersFromDB.map(_.id).forall(_ > 0) shouldBe true //verify auto-increment field
        }
      }
    }

    "update DoB field of a player" in {
      playerService.filterByName("Serena") flatMap { serenas =>
        serenas should not be empty

        val newDoB = LocalDate.parse("1981-09-26")
        playerService.updateDob(serenas.head.id, newDoB) flatMap { updateResult =>
          updateResult shouldBe 1

          playerService.getAllPlayers map { serenasAgain =>
            val serena = serenasAgain.find(_.name == "Serena")
            serena.flatMap(_.dob) should contain(newDoB)
          }
        }
      }
    }

    "delete a player from the table by name" in {
      playerService.deleteByName("Nadal") flatMap { deleteStatus =>
        deleteStatus shouldBe 1

        playerService.filterByName("Nadal") map { nadal =>
          nadal shouldBe empty
        }
      }
    }

    "combine multiple actions together and execute" in {
      val player              = Player(0L, "Murray", "Britain", None)
      val insertAction        = playerService.playerTable += player
      val anotherPlayer       = Player(0L, "Hingis", "Swiss", None)
      val insertAnotherAction = playerService.playerTable += anotherPlayer
      val updateAction        = playerService.playerTable.filter(_.name === "Federer").map(_.country).update("Swiss")
      val combinedAction      = DBIO.seq(insertAction, updateAction, insertAnotherAction)
      playerService.db.run[Unit] {
        combinedAction.transactionally
      } flatMap { _ =>
        playerService.getAllPlayers map { allPlayers =>
          allPlayers.map(_.name) should contain allElementsOf(Seq("Federer", "Serena", "Murray", "Hingis"))
          allPlayers.find(_.name == "Federer").map(_.country) should contain("Swiss")
        }
      }
    }

    "rollback the entire transaction if a failure occur" in {
      val player1           = Player(100L, "Steffi", "Germany", None)
      val player2           = Player(100L, "Sharapova", "Russia", None)
      //doing force insert to make the second insertion fail
      val insertAction1     = playerService.playerTable.forceInsert(player1)
      val insertAction2     = playerService.playerTable.forceInsert(player2)
      val transactionAction = insertAction1.andThen(insertAction2)

      recoverToExceptionIf[SQLException] {
        playerService.db.run(transactionAction.transactionally)
      } flatMap { ex =>
        ex.getMessage should include("primary key violation")

        playerService.getAllPlayers map { allPlayers =>
          allPlayers.map(_.name) should contain noneOf("Steffi", "Sharapova")
        }
      }
    }

    "update multiple records in a single query" in {
      val updatedRowsFut = playerService.updateCountry("Swiss", "Switzerland")
      updatedRowsFut flatMap { updatedRows =>
        updatedRows shouldBe 2

        playerService.filterByCountry("Switzerland") map { swissPlayers =>
          swissPlayers.size shouldBe 2
          swissPlayers.map(_.name) should contain allElementsOf (Seq("Federer", "Hingis"))
        }
      }
    }
  }
}
