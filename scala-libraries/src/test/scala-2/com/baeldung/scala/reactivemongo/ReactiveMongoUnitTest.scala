package com.baeldung.scala.reactivemongo

<<<<<<< HEAD:scala-libraries/src/test/scala/com/baeldung/scala/reactivemongo/ReactiveMongoUnitTest.scala
import de.flapdoodle.embed.mongo.MongodStarter
import de.flapdoodle.embed.mongo.config.{MongodConfig, Net}
import de.flapdoodle.embed.mongo.distribution.Version
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FutureOutcome}
import MongoEntityImplicits._
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Sink
=======
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Sink
import com.baeldung.scala.reactivemongo.MongoEntityImplicits._
import de.flapdoodle.embed.mongo.config.Net
import de.flapdoodle.embed.mongo.distribution.Version
import de.flapdoodle.embed.mongo.transitions.{ImmutableMongod, Mongod}
import de.flapdoodle.reverse.transitions.Start
import org.scalatest.matchers.should.Matchers
>>>>>>> 96fe189b888478a6d1a6b969a60b245b66f9308d:scala-libraries/src/test/scala-2/com/baeldung/scala/reactivemongo/ReactiveMongoUnitTest.scala
import org.scalatest.wordspec.AsyncWordSpec
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FutureOutcome}
import reactivemongo.api.Cursor
import reactivemongo.api.bson.BSONDocument

class ReactiveMongoUnitTest
  extends AsyncWordSpec
  with Matchers
  with BeforeAndAfterAll
  with BeforeAndAfterEach {

  val PORT = 27079
  val IP = "localhost"
<<<<<<< HEAD:scala-libraries/src/test/scala/com/baeldung/scala/reactivemongo/ReactiveMongoUnitTest.scala
  val mongodConfig = MongodConfig
    .builder()
    .version(Version.Main.V4_0)
    .net(new Net(IP, PORT, false))
    .build();
  val exec = starter.prepare(mongodConfig)
=======

  val mongodInstance: ImmutableMongod = Mongod
    .builder()
    .net(Start.to(classOf[Net]).initializedWith(Net.of(IP, PORT, false)))
    .build()
>>>>>>> 96fe189b888478a6d1a6b969a60b245b66f9308d:scala-libraries/src/test/scala-2/com/baeldung/scala/reactivemongo/ReactiveMongoUnitTest.scala

  override def beforeAll(): Unit = {
    mongodInstance.start(Version.Main.V4_0)
    super.beforeAll()
  }

  override protected def afterAll(): Unit = {
    super.afterAll()
  }

  lazy val connection =
    new MongoDBConnection(s"mongodb://${IP}:$PORT", "movies")

  "ReactiveMongo spec" should {

    "connect to the mongo and insert a movie into the collection" in {

      val movie =
        Movie("The Shawshank Redemption", "Morgan Freeman", "Drama", 144)
      connection.getCollection("Movie").flatMap { col =>
        val insertResultFuture = col.insert.one(movie)
        insertResultFuture.flatMap { writeRes =>
          writeRes.writeErrors shouldBe empty
          col.find(BSONDocument("name" -> movie.name)).one[Movie].map { sr =>
            sr shouldBe defined
            sr.get.leadActor shouldBe "Morgan Freeman"
          }
        }
      }

    }

    "insert multiple movies into the collection" in {
      val allMovies: Seq[Movie] = getMoviesList
      val insertListStatus =
        connection.getCollection("Movie").flatMap(_.insert.many(allMovies))
      insertListStatus.map { insertList =>
        insertList.totalN shouldBe allMovies.size
      }
    }

    "get all records from database for matching condition" in {
      val dramaMovies = connection
        .getCollection("Movie")
        .flatMap(
          _.find(BSONDocument("genre" -> "Drama"))
            .cursor[Movie]()
            .collect(err = Cursor.FailOnError[List[Movie]]())
        )
      dramaMovies.map { dramaList =>
        dramaList.map(_.name) should contain allElementsOf (getMoviesList
          .filter(_.genre == "Drama")
          .map(_.name))
      }
    }

    "get all records matching multiple conditions" in {
      val bradPittDramasFuture = connection
        .getCollection("Movie")
        .flatMap(
          _.find(
            BSONDocument(
              "genre" -> "Drama",
              "leadActor" -> "Brad Pitt",
              "durationInMin" -> BSONDocument("$gt" -> 130)
            )
          ).cursor[Movie]().collect(err = Cursor.FailOnError[List[Movie]]())
        )
      bradPittDramasFuture.map { bradPittDramas =>
        bradPittDramas.size shouldBe 2
        bradPittDramas
          .map(_.name) should contain allElementsOf (Seq("Troy", "Fight Club"))
      }
    }

    "update a record by matching filter" in {
      connection.getCollection("Movie").flatMap { col =>
        val updateStatus = col.findAndUpdate(
          BSONDocument("name" -> "Fight Club"),
          BSONDocument("$set" -> BSONDocument("durationInMin" -> 145))
        )
        updateStatus.flatMap { upResult =>
          // upResult.lastError shouldBe empty
          // Get from mongo and verify
          val fightClubFuture =
            col.find(BSONDocument("name" -> "Fight Club")).one[Movie]
          fightClubFuture.map { fcOpt =>
            fcOpt shouldBe defined
            fcOpt.get.durationInMin shouldBe 145
          }
        }
      }
    }

    "update multiple records for the matching condition" in {
      val updateFuture = connection.getCollection("Movie").flatMap { col =>
        val updateBuilder = col.update(true)
        val updates = updateBuilder.element(
          q = BSONDocument("genre" -> "Drama"),
          u = BSONDocument("$set" -> BSONDocument("genre" -> "Dramatic")),
          multi = true
        )
        updates.flatMap(updateEle => updateBuilder.many(Seq(updateEle)))
      }

      updateFuture.flatMap { updateResult =>
        updateResult.totalN shouldBe 4
        // get from mongo and verify updated field
        val filteredMovies = connection
          .getCollection("Movie")
          .flatMap(
            _.find(BSONDocument("genre" -> "Dramatic"))
              .cursor[Movie]()
              .collect(err = Cursor.FailOnError[List[Movie]]())
          )
        filteredMovies.map { dramaticMovies =>
          dramaticMovies.map(_.name) should contain allElementsOf (getMoviesList
            .filter(_.genre == "Drama")
            .map(_.name))
          dramaticMovies.map(_.genre).distinct shouldBe Seq("Dramatic")
        }
      }
    }

    "return empty result if a non-existing field name is used in find" in {
      connection.getCollection("Movie").flatMap { col =>
        col
          .find(BSONDocument("director" -> "Quentin Tarantino"))
          .one[Movie]
          .map { findResult =>
            findResult shouldBe empty
          }
      }
    }

    "delete a record matching the condition" in {
      connection.getCollection("Movie").flatMap { col =>
        col.findAndRemove(BSONDocument("name" -> "Troy")).flatMap { _ =>
          // verify from mongo
          col.find(BSONDocument("name" -> "Troy")).one[Movie].map { troy =>
            troy shouldBe empty
          }
        }
      }
    }

    "filter by condition and fetch only a limited records" in {
      connection.getCollection("Movie").flatMap { col =>
        val dramasLimited = col
          .find(BSONDocument("genre" -> "Drama"))
          // get only 3 records out of 4
          .cursor[Movie]()
          .collect(3, Cursor.FailOnError[List[Movie]]())
        dramasLimited.map { dramas =>
          dramas.size shouldBe 3
        }
      }
    }

    "filter and sort by movie duration" in {
      connection.getCollection("Movie").flatMap { col =>
        // use -1 for descending order and 1 for ascending order of sorting
        val longestTwoDramas = col
          .find(BSONDocument("genre" -> "Drama"))
          .sort(BSONDocument("durationInMin" -> -1))
          .cursor[Movie]()
          .collect(2, Cursor.FailOnError[List[Movie]]())
        longestTwoDramas.map { dramas =>
          dramas.size shouldBe 2
          // first result should be movie with longest duration
          dramas.head.name shouldBe "Troy"
          dramas.head.durationInMin shouldBe 163
          // second result should be the movie with 2nd longest duration
          dramas.last.name shouldBe "Fight Club"
          dramas.last.durationInMin shouldBe 140
        }
      }
    }

    "stream the movies and calculate total duration using akka stream api" in {
      // Note: This import(cursorProducer) is required for reactive mongo and akka stream integration
<<<<<<< HEAD:scala-libraries/src/test/scala/com/baeldung/scala/reactivemongo/ReactiveMongoUnitTest.scala
      import reactivemongo.akkastream.{State, cursorProducer}
=======
      import reactivemongo.akkastream.cursorProducer
>>>>>>> 96fe189b888478a6d1a6b969a60b245b66f9308d:scala-libraries/src/test/scala-2/com/baeldung/scala/reactivemongo/ReactiveMongoUnitTest.scala
      implicit val system = ActorSystem("reactive-mongo-stream")
      implicit val materializer = ActorMaterializer()
      connection.getCollection("Movie").flatMap { col =>
        val source = col
          .find(BSONDocument())
          .cursor[Movie]()
          .documentSource(100, Cursor.FailOnError())
        val totalDurationFuture =
          source.map(_.durationInMin).runWith(Sink.fold(0)(_ + _))
        totalDurationFuture.map(
          _ shouldBe getMoviesList.map(_.durationInMin).sum
        )
      }
    }

  }

  private def getMoviesList = {
    val fightClub = Movie("Fight Club", "Brad Pitt", "Drama", 140)
    val pulpFiction = Movie("Pulp Fiction", "Samuel Jackson", "Crime", 154)
    val wonderWoman = Movie("Wonder Woman", "Gal Gadot", "Fantasy", 141)
    val prestige = Movie("The Prestige", "Hugh Jackman", "Mystery", 130)
    val reader = Movie("The Reader", "Kate Winslet", "Drama", 124)
    val silenceOfLambs =
      Movie("The Silence of the Lambs", "Anthony Hopkins", "Thriller", 130)
    val troy = Movie("Troy", "Brad Pitt", "Drama", 163)
    val seven = Movie("Se7en", "Brad Pitt", "Drama", 127)
    val allMovies = Seq(
      fightClub,
      pulpFiction,
      wonderWoman,
      prestige,
      reader,
      silenceOfLambs,
      troy,
      seven
    )
    allMovies
  }

  override def withFixture(test: NoArgAsyncTest) =
    new FutureOutcome(for {
      _ <- connection.getCollection("Movie").flatMap(_.drop())
      _ <- connection
        .getCollection("Movie")
        .flatMap(_.insert.many(getMoviesList))
      testResult <- super.withFixture(test).toFuture
    } yield {
      testResult
    })
}
