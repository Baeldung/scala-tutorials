package com.baeldung.scala.akka.alpakka

import akka.NotUsed
import akka.stream.alpakka.mongodb.scaladsl.MongoSink
import akka.stream.scaladsl.Source
import com.mongodb.reactivestreams.client.MongoCollection

class AlpakkaMongoIntegration(vehicleCollection: MongoCollection[VehicleData]) {

  import Configs._

  def process(source: Source[String, NotUsed]) = {
    source
      .map { data =>
        val v = data.trim.split(",")
        VehicleData(v(0).toLong, GPSLocation(v(1).toDouble, v(2).toDouble))
      }
      .runWith {
        MongoSink.insertOne(vehicleCollection)
      }

  }
}
