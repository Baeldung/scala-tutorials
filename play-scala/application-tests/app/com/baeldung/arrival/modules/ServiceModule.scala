package com.baeldung.arrival.modules

import com.baeldung.arrival.service.{ArrivalDecoratorService, ArrivalService}
import com.google.inject.AbstractModule

import javax.inject.Singleton

class ServiceModule extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[ArrivalService]).in(classOf[Singleton])
    bind(classOf[ArrivalDecoratorService]).in(classOf[Singleton])
  }

}
