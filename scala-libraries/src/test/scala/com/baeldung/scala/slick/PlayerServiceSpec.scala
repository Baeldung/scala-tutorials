package com.baeldung.scala.slick

import java.sql.SQLException
import java.time.LocalDate
import org.scalactic.Equality
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{
  AsyncWordSpec,
  BeforeAndAfterAll,
  BeforeAndAfterEach,
  FutureOutcome,
  Matchers
}
import slick.jdbc.H2Profile.api._

import scala.concurrent.Future

class PlayerServiceSpec
  extends AsyncWordSpec
  with Matchers
  with ScalaFutures
  with BeforeAndAfterAll
  with BeforeAndAfterEach {
  private val playerService = new PlayerService

  "PlayerService" should {
    "insert a player to the table and filter by country" in {
      val steffi = Player(100L, "Steffi", "Germany", None)
      playerService.insertPlayer(steffi) flatMap { insertStatus =>
        insertStatus shouldBe 1

        // get inserted record from database and check
        playerService.getAllPlayers map { allPlayers =>
          allPlayers.find(_.name == "Steffi").get shouldEqual steffi
        }
        // filter for player by country
        val germanPlayersQuery =
          playerService.playerTable.filter(_.country === "Germany")
        val germanPlayers: Future[Seq[Player]] =
          playerService.db.run[Seq[Player]](germanPlayersQuery.result)
        germanPlayers.map { player =>
          player.size shouldBe 1
          player.head.name shouldBe "Steffi"
        }
      }
    }

    "insert multiple players together" in {
      val steffi = Player(100L, "Steffi", "Germany", None)
      val sharapova = Player(100L, "Sharapova", "Russia", None)

      playerService.insertPlayers(Seq(steffi, sharapova)) flatMap {
        insertMultipleResult =>
          insertMultipleResult should contain(2)

          playerService.getAllPlayers map { playersFromDB =>
            playersFromDB should contain allElementsOf (Seq(steffi, sharapova))
            playersFromDB
              .map(_.id)
              .forall(_ > 0) shouldBe true //verify auto-increment field
          }
      }
    }

    "insert and then update the country field of a player" in {
      //force insert a record
      val boris = Player(500L, "Boris", "Deutschland", None)
      val playerTable = playerService.playerTable
      val borisQuery = playerTable.forceInsert(boris)
      playerService.db.run(borisQuery).flatMap { insertedResult =>
        val updateCountryAction =
          playerTable
            .filter(_.id === 500L)
            .map(_.country)
            .update("Germany")
        playerService.db.run(updateCountryAction).flatMap { updateResult =>
          playerService.db.run(playerTable.filter(_.id === 500L).result).map {
            filterResult =>
              filterResult.size shouldBe 1
              filterResult.head.country shouldBe "Germany"
          }
        }
      }
    }

    "update DoB field of a player" in {
      playerService.filterByName("Serena") flatMap { foundSerena =>
        foundSerena should not be empty

        val newDoB = LocalDate.parse("1981-09-26")
        playerService.updateDob(foundSerena.head.id, newDoB) flatMap {
          updateResult =>
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
      val steffi = Player(100L, "Steffi", "Germany", None)
      val sharapova = Player(100L, "Sharapova", "Russia", None)

      val insertAction = playerService.playerTable += steffi
      val insertAnotherAction = playerService.playerTable += sharapova
      val updateAction = playerService.playerTable
        .filter(_.name === "Federer")
        .map(_.country)
        .update("Swiss")
      val combinedAction =
        DBIO.seq(insertAction, updateAction, insertAnotherAction)
      playerService.db.run[Unit] {
        combinedAction.transactionally
      } flatMap { _ =>
        playerService.getAllPlayers map { allPlayers =>
          allPlayers should contain allOf (steffi, sharapova)
          allPlayers.find(_.name == "Federer").map(_.country) should contain(
            "Swiss"
          )
        }
      }
    }

    "rollback the entire transaction if a failure occur" in {
      val steffi = Player(100L, "Steffi", "Germany", None)
      val sharapova = Player(100L, "Sharapova", "Russia", None)
      //doing force insert to make the second insertion fail
      val insertAction1 = playerService.playerTable.forceInsert(steffi)
      val insertAction2 = playerService.playerTable.forceInsert(sharapova)
      val transactionAction = insertAction1.andThen(insertAction2)

      recoverToExceptionIf[SQLException] {
        playerService.db.run(transactionAction.transactionally)
      } flatMap { ex =>
        ex.getMessage should include("primary key violation")

        playerService.getAllPlayers map { allPlayers =>
          allPlayers should contain noneOf (steffi, sharapova)
        }
      }
    }

    "update multiple records in a single query" in {
      val updatedRowsFut = playerService.updateCountry("Swiss", "Switzerland")
      updatedRowsFut flatMap { updatedRows =>
        updatedRows shouldBe 2

        playerService.filterByCountry("Switzerland") map { swissPlayers =>
          swissPlayers.size shouldBe 2
          swissPlayers
            .map(_.name) should contain allElementsOf (Seq("Federer", "Hingis"))
        }
      }
    }
  }

  // This val will be initialized once for test suite with the result of a Future call to create a Table
  private val createTableFut = playerService.createTable
  private val federer =
    Player(0L, "Federer", "Swiss", Some(LocalDate.parse("1981-08-08")))
  private val nadal =
    Player(0L, "Nadal", "Spain", Some(LocalDate.parse("1986-06-03")))
  private val serena = Player(0L, "Serena", "USA", None)
  private val murray = Player(0L, "Murray", "Britain", None)
  private val higis = Player(0L, "Hingis", "Swiss", None)
  private val players = Seq(federer, nadal, serena, murray, higis)

  // Implicit comparator to skip ID field comparison
  implicit private val aEq: Equality[Player] = (a: Player, b: Any) =>
    b match {
      case Player(_, name, country, dob) =>
        a.name == name && a.country == country && a.dob == dob
      case _ => false
    }

  override def withFixture(test: NoArgAsyncTest) =
    new FutureOutcome(for {
      _ <- createTableFut
      _ <- playerService.clearAll
      _ <- playerService.insertPlayers(players)
      testResult <- super.withFixture(test).toFuture
    } yield { testResult })
}
