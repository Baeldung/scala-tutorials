package com.baeldung.arrival.service

import com.baeldung.arrival.db.manager.DbManager
import com.baeldung.arrival.db.repository.{Arrival, ArrivalRepository}

import javax.inject.Inject
import scala.concurrent.Future

class ArrivalService @Inject()(db: DbManager, arrivalRepository: ArrivalRepository) {
  def getArrivals(): Future[Seq[Arrival]] = db.execute(arrivalRepository.getArrivals)
}
