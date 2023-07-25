package com.baeldung.arrival.modules

import com.baeldung.arrival.db.repository.{ArrivalRepository, SlickArrivalRepository}
import com.google.inject.AbstractModule

class SlickModule extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[ArrivalRepository]).to(classOf[SlickArrivalRepository])
  }

}
