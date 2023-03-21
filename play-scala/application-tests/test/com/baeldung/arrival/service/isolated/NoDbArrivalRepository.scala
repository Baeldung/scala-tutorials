package com.baeldung.arrival.service.isolated

import com.baeldung.arrival.db.repository.{Arrival, ArrivalRepository}
import slick.dbio.{DBIO, SuccessAction}

class NoDbArrivalRepository extends ArrivalRepository {
  override def getArrivals: DBIO[Seq[Arrival]] = {
    SuccessAction(Seq(
      Arrival(12L, "Istanbul", "Athens", "A380"),
      Arrival(17L, "Dublin", "Oslo", "A380")
    ))
  }
}
