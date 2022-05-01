package com.baeldung.scalamock.service

import com.baeldung.scalamock.model.{Model1, Model2}

trait UnitService1 {
  def doSomething(m1: Model1, m2: Model2): Unit
  def doSomethingElse(m1: Model1, m2: Model2): Unit
}

class UnitService2Impl(srv1: UnitService1) {
  def doSomething(m1: Model1, m2: Model2): Unit = {
    srv1.doSomething(m1, m2)
  }

  def doManyThings(m1: Model1, m2: Model2): Unit = {
    srv1.doSomething(m1, m2)
    srv1.doSomethingElse(m1, m2)
  }

  def doManyThingsInverted(m1: Model1, m2: Model2): Unit = {
    srv1.doSomethingElse(m1, m2)
    srv1.doSomething(m1, m2)
  }
}
