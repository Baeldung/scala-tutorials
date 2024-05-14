package com.baeldung.arrival.service

import com.baeldung.arrival.db.repository.Arrival
import org.scalatestplus.play.MixedPlaySpec
import play.api.Configuration
import play.api.inject.guice.GuiceApplicationBuilder

class ArrivalDecoratorServiceUnitTest extends MixedPlaySpec {

  "ArrivalDecoratorService#decorate" should {
    "mark as short an arrival with plane name length = 5" in new App(
      GuiceApplicationBuilder()
        .configure("play.http.router" -> "play.api.routing.Router")
        .loadConfig(env =>
          Configuration
            .load(env, Map("config.resource" -> "application.test.conf"))
        )
        .build()
    ) {
      private val testee = app.injector.instanceOf[ArrivalDecoratorService]
      private val arrival = Arrival(1L, "Athens", "Heathrow", "12345")
      assert(testee.decorate(arrival).short)
      assert(!testee.decorate(arrival).medium)
      assert(!testee.decorate(arrival).long)
    }

    "mark as medium an arrival with plane name length = 5 with overridden configuration" in new App(
      GuiceApplicationBuilder()
        .configure("play.http.router" -> "play.api.routing.Router")
        .loadConfig(env =>
          Configuration
            .load(env, Map("config.resource" -> "application.test.conf"))
        )
        .configure(
          "short-name-max" -> "3",
          "medium-name-max" -> "6"
        )
        .build()
    ) {
      private val testee = app.injector.instanceOf[ArrivalDecoratorService]
      private val arrival = Arrival(1L, "Athens", "Heathrow", "12345")
      assert(!testee.decorate(arrival).short)
      assert(testee.decorate(arrival).medium)
      assert(!testee.decorate(arrival).long)
    }
  }

}
