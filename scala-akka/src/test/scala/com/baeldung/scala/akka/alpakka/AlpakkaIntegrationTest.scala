package com.baeldung.scala.akka.alpakka

import java.nio.file.{FileSystems, Path, Paths}

import akka.stream.alpakka.file.scaladsl.FileTailSource
import akka.stream.alpakka.mongodb.scaladsl.MongoSource
import akka.stream.scaladsl.{Sink, Source}
import com.typesafe.config.ConfigFactory
import de.flapdoodle.embed.mongo.MongodStarter
import de.flapdoodle.embed.mongo.config.{MongodConfigBuilder, Net}
import de.flapdoodle.embed.mongo.distribution.Version
import de.flapdoodle.embed.process.runtime.Network
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpec}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

class AlpakkaIntegrationTest
  extends WordSpec
  with Matchers
  with ScalaFutures
  with BeforeAndAfterAll {

  val starter = MongodStarter.getDefaultInstance
  val ip = ConfigFactory.load.getString("alpakka.mongo.connection.ip")
  val port = ConfigFactory.load.getInt("alpakka.mongo.connection.port")
  val mongoDBConfig = new MongodConfigBuilder()
    .version(Version.Main.PRODUCTION)
    .net(new Net(ip, port, Network.localhostIsIPv6()))
    .build()
  val mongod = starter.prepare(mongoDBConfig)

  override def beforeAll() = {
    mongod.start()
  }

  override def afterAll() = {
    mongod.stop()
  }

  "Alpakka MongoDB integration service" must {

    "read the data from the source and insert into the mongoDB collection successfully" in {

      val gpsData = List(
        "1, 70.23857, 16.239987",
        "1, 70.876, 16.188",
        "2, 17.87, 77.71443",
        "3, 55.7712, 16.9088"
      )
      val integration = new AlpakkaMongoIntegration(
        Collections.vehicleDataCollection
      )
      import Configs._

      integration.process(Source(gpsData)).flatMap { _ =>
        val documentsFuture = MongoSource(
          Collections.db
            .getCollection(classOf[VehicleData].getSimpleName)
            .find()
        ).runWith(Sink.seq)

        documentsFuture map { documents =>
          documents.size shouldBe 4
          documents
            .map(_.get("vehicleId")) should contain allElementsOf (Seq(1, 2, 3))

          documents.map(_.get("vehicleId")).size shouldBe 4
        }

      }

    }

    "read data from the flat file source and insert into mongoDB collection successfully" in {
      val integration = new AlpakkaMongoIntegration(
        Collections.vehicleDataCollection
      )

      val fs = FileSystems.getDefault
      val filePath = "vehicle_data.csv"
      val path: Path = Paths.get(
        Thread
          .currentThread()
          .getContextClassLoader()
          .getResource(filePath)
          .toURI()
      )
      val flatFileSource =
        FileTailSource.lines(
          path = path,
          maxLineSize = 200,
          pollingInterval = 10.millis
        )

      integration.process(flatFileSource)
      import Configs._

      Thread.sleep(2000)
      val documentsFuture = MongoSource(
        Collections.db.getCollection(classOf[VehicleData].getSimpleName).find()
      ).runWith(Sink.seq)

      documentsFuture.map { documents =>
        // 4 from the previous source and 2 from the file source
        documents.size shouldBe 6
        documents
          .map(_.get("vehicleId")) should contain allElementsOf (Seq(1, 2, 3,
          23, 24))

        documents.map(_.get("vehicleId")).size shouldBe 6
      }

    }
  }

}
