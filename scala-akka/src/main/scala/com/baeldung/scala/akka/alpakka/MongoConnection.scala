package com.baeldung.scala.akka.alpakka

import com.mongodb.reactivestreams.client.{MongoClients, MongoCollection}
import com.typesafe.config.ConfigFactory

class MongoConnection(url: String) {

  final val client = MongoClients.create(url)
  final val db = client.getDatabase("vehicle-tracker")

}

object Collections {

  final val conf = ConfigFactory.load()
  val port = conf.getLong("alpakka.mongo.connection.port")
  val ipAddress = conf.getString("alpakka.mongo.connection.ip")
  val connectionUrl = s"mongodb://$ipAddress:$port"

  final val connection = new MongoConnection(connectionUrl)
  val db = connection.db

  val vehicleDataCollection: MongoCollection[VehicleData] = db
    .getCollection(classOf[VehicleData].getSimpleName, classOf[VehicleData])
    .withCodecRegistry(CodecRegistry.vehicleCodec)
}
