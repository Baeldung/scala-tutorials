package com.baeldung.arrival.db.repository

import slick.dbio.DBIO

class MockArrivalRepository extends ArrivalRepository {
  override def getArrivals: DBIO[Seq[Arrival]] = DBIO.successful(
    List(
      Arrival(1L, "origin-1", "destination-1", "F16"),
      Arrival(2L, "origin-2", "destination-2", "F22"),
      Arrival(3L, "origin-3", "destination-3", "SR71")
    )
  )
}
