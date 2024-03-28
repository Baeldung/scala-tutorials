package com.baeldung.redis.geospatial

import com.baeldung.redis.geospatial.GeoPoints.{
  Dritseika,
  Kipseli,
  Makriloggos,
  MegaloPotami,
  Methana,
  MethanaIndex,
  Vromolimni
}
import com.baeldung.redis.util.RedisManualTest
import org.scalatest.flatspec.AnyFlatSpec

class GeospatialManualTest extends AnyFlatSpec with RedisManualTest {

  private def loadGeopoints(geoSpatial: Geospatial): Unit = {
    geoSpatial.add(MethanaIndex)(Methana.name)(Methana.latLon)
    geoSpatial.add(MethanaIndex)(Vromolimni.name)(Vromolimni.latLon)
    geoSpatial.add(MethanaIndex)(Kipseli.name)(Kipseli.latLon)
    geoSpatial.add(MethanaIndex)(Dritseika.name)(Dritseika.latLon)
    geoSpatial.add(MethanaIndex)(MegaloPotami.name)(MegaloPotami.latLon)
    geoSpatial.add(MethanaIndex)(Makriloggos.name)(Makriloggos.latLon)
  }

  "Geospatial#dist" should "return correct distance for geo points - Vromolimni is closer to Mathana than Kipseli" in {
    val geoSpatial = new Geospatial(getJedis())
    loadGeopoints(geoSpatial)
    val methanaToVromolimni =
      geoSpatial.dist(MethanaIndex)(Methana.name, Vromolimni.name)
    val methanaToKipseli =
      geoSpatial.dist(MethanaIndex)(Methana.name, Kipseli.name)
    assert(methanaToVromolimni < methanaToKipseli)
  }

  "Geospatial#searchRadius" should "return only Dritseika, Vromolimni and Methana for 2km radius from Methana" in {
    val geoSpatial = new Geospatial(getJedis())
    loadGeopoints(geoSpatial)
    val result = geoSpatial.searchRadius(MethanaIndex)(Methana.latLon)(2.0)
    assert(result.size === 3)
    assert(result.exists(_.getMemberByString == Vromolimni.name))
    assert(result.exists(_.getMemberByString == Dritseika.name))
    assert(result.exists(_.getMemberByString == Methana.name))
  }

  "Geospatial#searchBox" should "return only MegaloPotami, Makriloggos and Kipseli for 4 x 12 km box from Kipseli" in {
    val geoSpatial = new Geospatial(getJedis())
    loadGeopoints(geoSpatial)
    val result = geoSpatial.searchBox(MethanaIndex)(Kipseli.latLon)(12.0, 4.0)
    assert(result.size === 3)
    assert(result.exists(_.getMemberByString == MegaloPotami.name))
    assert(result.exists(_.getMemberByString == Makriloggos.name))
    assert(result.exists(_.getMemberByString == Kipseli.name))
  }

}
