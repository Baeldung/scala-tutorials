package com.baeldung.arrival.modules

import com.baeldung.arrival.controller.ArrivalController
import com.google.inject.AbstractModule

import javax.inject.Singleton

class ControllerModule extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[ArrivalController]).in(classOf[Singleton])
  }

}
