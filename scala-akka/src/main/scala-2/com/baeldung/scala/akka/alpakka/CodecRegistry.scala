package com.baeldung.scala.akka.alpakka

import org.bson.codecs.configuration.CodecRegistries.{
  fromProviders,
  fromRegistries
}
<<<<<<< HEAD:scala-akka/src/main/scala/com/baeldung/scala/akka/alpakka/CodecRegistry.scala
import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY
=======
import org.mongodb.scala.MongoClient.DEFAULT_CODEC_REGISTRY
>>>>>>> 96fe189b888478a6d1a6b969a60b245b66f9308d:scala-akka/src/main/scala-2/com/baeldung/scala/akka/alpakka/CodecRegistry.scala
import org.mongodb.scala.bson.codecs.Macros._

final case class GPSLocation(lat: Double, lng: Double)

final case class VehicleData(vehicleId: Long, location: GPSLocation)

//final case class EngineParams(vehicleId: Long, temperature: Double, rpm: Long)

object CodecRegistry {
  val vehicleCodec = fromRegistries(
    fromProviders(classOf[VehicleData], classOf[GPSLocation]),
    DEFAULT_CODEC_REGISTRY
  )
//  val engineCodec  = fromRegistries(fromProviders(classOf[EngineParams]), DEFAULT_CODEC_REGISTRY)
}
