package com.baeldung.arrival.service

import com.baeldung.arrival.applicationfactory.H2ApplicationFactory
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest

import scala.language.postfixOps

class ArrivalServiceH2UnitTest
  extends AnyWordSpec
  with GuiceOneAppPerTest
  with ScalaFutures
  with H2ApplicationFactory {

  "ArrivalService" should {
    "fetch data from H2" in {
      val arrivalsF = app.injector.instanceOf[ArrivalService].getArrivals()
      whenReady(arrivalsF)(arrivals => {
        assert(arrivals.length === 6)
      })
    }
  }

  override implicit def patienceConfig: PatienceConfig = PatienceConfig(
    timeout = scaled(Span(10, Seconds)),
    interval = scaled(Span(300, Millis))
  )

}
