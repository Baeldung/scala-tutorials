package com.baeldung.arrival.applicationfactory

import org.scalatestplus.play.FakeApplicationFactory
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.{Application, Configuration}

trait H2ApplicationFactory {
  self: FakeApplicationFactory =>

  override def fakeApplication(): Application = {
    GuiceApplicationBuilder(
      loadConfiguration = env => Configuration.load(env, Map("config.resource" -> "application.h2.conf"))
    ).build()
  }
}
