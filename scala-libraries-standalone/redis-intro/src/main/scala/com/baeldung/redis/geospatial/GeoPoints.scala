package com.baeldung.redis.geospatial

import redis.clients.jedis.GeoCoordinate

case class GeoPoint(name: String, latLon: LatLon)

case class LatLon(lat: Double, lon: Double)

object LatLon {
  def toGeoCoordinate(latLong: LatLon): GeoCoordinate = {
    new GeoCoordinate(latLong.lon, latLong.lat)
  }
}

object GeoPoints {
  val Methana =
    GeoPoint("Methana", LatLon(37.58303564998219, 23.387115029515513))
  val Vromolimni =
    GeoPoint("Vromolimni", LatLon(37.589273863460754, 23.38577712741729))
  val Dritseika =
    GeoPoint("Dritseika", LatLon(37.58546506226565, 23.38088477825137))
  val Kipseli =
    GeoPoint("Kipseli", LatLon(37.61021878600531, 23.396505963307447))
  val MegaloPotami =
    GeoPoint("MegaloPotami", LatLon(37.597927893434225, 23.35013310734787))
  val Makriloggos =
    GeoPoint("Makriloggos", LatLon(37.621657733616175, 23.35880200674712))
  val MethanaIndex = "MethanaIndex"
}
