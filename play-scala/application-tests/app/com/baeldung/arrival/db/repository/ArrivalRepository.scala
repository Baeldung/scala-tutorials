package com.baeldung.arrival.db.repository

import play.api.libs.json.{Json, OFormat}
import slick.dbio.DBIO

case class Arrival(planeId: Long, origin: String, destination: String, plane: String)

object Arrival {
  implicit val format: OFormat[Arrival] = Json.format[Arrival]
}

trait ArrivalRepository {
  def getArrivals: DBIO[Seq[Arrival]]
}
