package com.baeldung.arrival.service

import com.baeldung.arrival.applicationfactory.H2ApplicationFactory
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest

import scala.language.postfixOps

class ArrivalServiceH2Test extends AnyWordSpec with GuiceOneAppPerTest with ScalaFutures with H2ApplicationFactory {

  "ArrivalService" should {
    "fetch data from H2" in {
      val arrivalsF = app.injector.instanceOf[ArrivalService].getArrivals()
      whenReady(arrivalsF)(arrivals => {
        assert(arrivals.length === 6)
      })
    }
  }

}
