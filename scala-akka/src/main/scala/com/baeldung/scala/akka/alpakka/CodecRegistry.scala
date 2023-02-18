package com.baeldung.scala.akka.alpakka

import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.mongodb.scala.MongoClient.DEFAULT_CODEC_REGISTRY
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
