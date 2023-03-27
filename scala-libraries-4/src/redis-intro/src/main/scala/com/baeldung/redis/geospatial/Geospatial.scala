package com.baeldung.redis.geospatial

import com.baeldung.redis.geospatial.LatLon.toGeoCoordinate
import redis.clients.jedis.Jedis
import redis.clients.jedis.args.GeoUnit
import redis.clients.jedis.resps.GeoRadiusResponse

import scala.jdk.CollectionConverters._

class Geospatial(jedis: Jedis) {

  def add(geoIndexName: String)(memberName: String)(latLon: LatLon): Long = {
    jedis.geoadd(geoIndexName, latLon.lon, latLon.lat, memberName)
  }

  def dist(geoIndexName: String)(member1: String, member2: String): Double = {
    jedis.geodist(geoIndexName, member1, member2, GeoUnit.KM)
  }

  def searchRadius(
    geoIndexName: String
  )(latLon: LatLon)(radius: Double): List[GeoRadiusResponse] = {
    jedis
      .geosearch(geoIndexName, toGeoCoordinate(latLon), radius, GeoUnit.KM)
      .asScala
      .toList
  }

  def searchBox(
    geoIndexName: String
  )(latLon: LatLon)(width: Double, height: Double): List[GeoRadiusResponse] = {
    jedis
      .geosearch(
        geoIndexName,
        toGeoCoordinate(latLon),
        width,
        height,
        GeoUnit.KM
      )
      .asScala
      .toList
  }

}
