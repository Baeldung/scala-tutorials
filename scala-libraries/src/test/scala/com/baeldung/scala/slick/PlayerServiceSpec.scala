package com.baeldung.scala.slick

import java.sql.SQLException
import java.time.LocalDate

import com.baeldung.scala.slick.SlickTables.PlayerTable
import org.scalactic.Equality
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{AsyncWordSpec, BeforeAndAfterAll, BeforeAndAfterEach, FutureOutcome, Matchers}
import slick.jdbc.H2Profile.api._

import scala.concurrent.Future

class PlayerServiceSpec
  extends AsyncWordSpec
    with Matchers
    with ScalaFutures
    with BeforeAndAfterAll
    with BeforeAndAfterEach {

  private val playerTable = TableQuery[PlayerTable]
  lazy val db = Connection.db

  "PlayerService" should {
    "insert a player to the table and filter by country" in {
      val steffi = Player(100L, "Steffi", "Germany", None)
      val insertPlayerQuery = playerTable += steffi
      db.run(insertPlayerQuery) flatMap { insertStatus =>
        insertStatus shouldBe 1

        // get inserted record from database and check
        val germanPlayersQuery = playerTable.filter(_.country === "Germany")
        val germanPlayers: Future[Seq[Player]] = db.run(germanPlayersQuery.result)
        germanPlayers map { allPlayers =>
          allPlayers.find(_.name == "Steffi").get shouldEqual steffi
        }
      }
    }

    "insert multiple players together" in {
      val steffi = Player(100L, "Steffi", "Germany", None)
      val sharapova = Player(100L, "Sharapova", "Russia", None)

      db.run(playerTable ++= (Seq(steffi, sharapova))) flatMap {
        insertMultipleResult =>
          insertMultipleResult should contain(2)

          db.run(playerTable.result) map { playersFromDB =>
            playersFromDB should contain allElementsOf (Seq(steffi, sharapova))
            playersFromDB
              .map(_.id)
              .forall(_ > 0) shouldBe true //verify auto-increment field
          }
      }
    }

    "insert and then update the country field of a player" in {
      //force insert a record
      val player = Player(500L, "Boris", "Deutschland", None)
      val forceInsertAction = playerTable.forceInsert(player)
      db.run(forceInsertAction).flatMap { insertedResult =>
        val updateCountryAction =
          playerTable
            .filter(_.id === 500L)
            .map(_.country)
            .update("Germany")
        db.run(updateCountryAction).flatMap { updateResult =>
          db.run(playerTable.filter(_.id === 500L).result).map {
            filterResult =>
              filterResult.size shouldBe 1
              filterResult.head.country shouldBe "Germany"
          }
        }
      }
    }

    "update DoB field of a player" in {
      db.run(playerTable.filter(_.name === "Serena").result) flatMap { foundSerena =>
        foundSerena should not be empty

        val newDoB = Option(LocalDate.parse("1981-09-26"))
        db.run(playerTable.filter(_.id === foundSerena.head.id).map(_.dob).update(newDoB)) flatMap {
          updateResult =>
            updateResult shouldBe 1

            db.run(playerTable.result) map { serenasAgain =>
              val serena = serenasAgain.find(_.name == "Serena")
              serena.flatMap(_.dob).get shouldBe newDoB.get
            }
        }
      }
    }

    "delete a player from the table by name" in {
      val deleteAction = playerTable.filter(_.name === "Nadal").delete
      db.run(deleteAction) flatMap { deleteStatus =>
        deleteStatus shouldBe 1

        db.run(playerTable.filter(_.name === "Nadal").result) map { nadal =>
          nadal shouldBe empty
        }
      }
    }

    "combine multiple actions together and execute" in {
      val steffi = Player(100L, "Steffi", "Germany", None)
      val sharapova = Player(100L, "Sharapova", "Russia", None)

      val insertAction = playerTable += steffi
      val insertAnotherAction = playerTable += sharapova
      val updateAction = playerTable
        .filter(_.name === "Federer")
        .map(_.country)
        .update("Swiss")
      val combinedAction =
        DBIO.seq(insertAction, updateAction, insertAnotherAction)
      db.run[Unit] {
        combinedAction.transactionally
      } flatMap { _ =>
        db.run(playerTable.result) map { allPlayers =>
          allPlayers should contain allOf(steffi, sharapova)
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
      val insertAction1 = playerTable.forceInsert(steffi)
      val insertAction2 = playerTable.forceInsert(sharapova)
      val transactionAction = insertAction1.andThen(insertAction2)

      recoverToExceptionIf[SQLException] {
        db.run(transactionAction.transactionally)
      } flatMap { ex =>
        ex.getMessage should include("primary key violation")

        db.run(playerTable.result) map { allPlayers =>
          allPlayers should contain noneOf(steffi, sharapova)
        }
      }
    }

    "update multiple records in a single query" in {
      val updateMultipleAction = playerTable.filter(_.country === "Swiss").map(_.country).update("Switzerland")
      val updatedRowsFut = db.run(updateMultipleAction)
      updatedRowsFut flatMap { updatedRows =>
        updatedRows shouldBe 2

        db.run(playerTable.filter(_.country === "Switzerland").result) map { swissPlayers =>
          swissPlayers.size shouldBe 2
          swissPlayers
            .map(_.name) should contain allElementsOf (Seq("Federer", "Hingis"))
        }
      }
    }
  }

  "retrieve the records from the database using sql interpolator" in {
    val selectCountryAction:DBIO[Seq[String]] = sql"""select "name" from "Player" where "country" = 'Spain' """.as[String]
    db.run(selectCountryAction) map { spainPlayers =>
      spainPlayers.size shouldBe 1
      spainPlayers.head shouldBe "Nadal"
    }
  }

  // This val will be initialized once for test suite with the result of a Future call to create a Table
  private val createTableFut = createTable
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
      _ <- clearAll
      //Insert multiple players together using ++=
      _ <- db.run(playerTable ++= players)
      testResult <- super.withFixture(test).toFuture
    } yield {
      testResult
    })

  def createTable: Future[Int] = {
    val createQuery: DBIO[Int] =
      sqlu"""create table "Player"(
           "player_id" bigserial primary key,
           "name" varchar not null,
           "country" varchar not null,
           "dob" date
          ) """

    db.run(createQuery)
  }

  def clearAll: Future[Int] = {
    db.run(playerTable.delete)
  }
}
