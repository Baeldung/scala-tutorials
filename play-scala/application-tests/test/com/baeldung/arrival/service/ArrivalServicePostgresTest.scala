package com.baeldung.arrival.service

import com.baeldung.arrival.applicationfactory.PostgresApplicationFactory
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest

import scala.language.postfixOps

class ArrivalServicePostgresTest
  extends AnyWordSpec
  with GuiceOneAppPerTest
  with ScalaFutures
  with PostgresApplicationFactory {

  "ArrivalService" should {
    "fetch data from Postgres" in {
      val arrivalsF = app.injector.instanceOf[ArrivalService].getArrivals()
      whenReady(arrivalsF)(arrivals => {
        assert(arrivals.length === 5)
      })
    }
  }

}
