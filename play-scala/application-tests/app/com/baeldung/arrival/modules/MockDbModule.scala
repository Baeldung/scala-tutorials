package com.baeldung.arrival.modules

import com.baeldung.arrival.db.repository.{ArrivalRepository, MockArrivalRepository}
import com.google.inject.AbstractModule

class MockDbModule extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[ArrivalRepository]).to(classOf[MockArrivalRepository])
  }

}
