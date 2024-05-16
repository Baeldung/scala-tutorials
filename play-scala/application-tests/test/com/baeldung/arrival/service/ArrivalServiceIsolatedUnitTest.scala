package com.baeldung.arrival.service

import com.baeldung.arrival.db.manager.DbManager
import com.baeldung.arrival.db.repository.ArrivalRepository
import com.baeldung.arrival.modules.ServiceModule
import com.baeldung.arrival.service.isolated.{
  InMemoryArrivalRepository,
  InMemoryDbManager
}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.{Application, Configuration, inject}

class ArrivalServiceIsolatedUnitTest
  extends AnyWordSpec
  with GuiceOneAppPerTest
  with ScalaFutures {

  override def fakeApplication(): Application = {
    GuiceApplicationBuilder(
      modules = Seq(new ServiceModule),
      configuration = Configuration(
        "play.http.router" -> "play.api.routing.Router",
        "short-name-max" -> 5,
        "medium-name-max" -> 8
      )
    )
      .bindings(inject.bind[DbManager].toInstance(new InMemoryDbManager))
      .bindings(
        inject.bind[ArrivalRepository].toInstance(new InMemoryArrivalRepository)
      )
      .build()
  }

  "ArrivalService#getArrivals" should {
    "use the InMemoryArrivalRepository without a connection to a database" in {
      val arrivalsF = app.injector.instanceOf[ArrivalService].getArrivals()
      whenReady(arrivalsF)(arrivals => {
        assert(arrivals.length === 2)
      })
    }
  }

}
