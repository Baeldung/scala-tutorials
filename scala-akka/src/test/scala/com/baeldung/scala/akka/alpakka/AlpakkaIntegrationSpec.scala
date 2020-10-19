package com.baeldung.scala.akka.alpakka

import akka.stream.alpakka.mongodb.scaladsl.MongoSource
import akka.stream.scaladsl.{Sink, Source}
import com.typesafe.config.ConfigFactory
import de.flapdoodle.embed.mongo.MongodStarter
import de.flapdoodle.embed.mongo.config.{MongodConfigBuilder, Net}
import de.flapdoodle.embed.mongo.distribution.Version
import de.flapdoodle.embed.process.runtime.Network
import org.bson.Document
import org.scalatest.concurrent.PatienceConfiguration.Timeout
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpec}

import scala.collection.immutable
import scala.concurrent.Future
import scala.concurrent.duration._

class AlpakkaIntegrationSpec
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
      implicit val timeout = Timeout(3.seconds)

      val gpsData = List(
        "1, 70.23857, 16.239987",
        "1, 70.876, 16.188",
        "2, 17.87, 77.71443",
        "3, 55.7712, 16.9088"
      )
      val integration = new AlpakkaMongoIntegration(
        Collections.vehicleDataCollection
      )
      integration.process(Source(gpsData)).futureValue(timeout)
      import Configs._

      val documents: immutable.Seq[Document] = MongoSource(
        Collections.db.getCollection(classOf[VehicleData].getSimpleName).find()
      ).runWith(Sink.seq).futureValue(timeout)

      documents.size shouldBe 4
      documents
        .map(_.get("vehicleId")) should contain allElementsOf (Seq(1, 2, 3))

      documents.map(_.get("vehicleId")).size shouldBe 4

    }
  }

}
