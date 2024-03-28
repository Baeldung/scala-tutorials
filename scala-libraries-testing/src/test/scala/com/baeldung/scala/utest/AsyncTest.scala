package com.baeldung.scala.utest

import utest._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object AsyncTest extends TestSuite {
  def getFromDB(): Future[Int] = Future { 42 }

  override def tests: Tests = Tests {
    test("get something from database") {
      getFromDB().map(v => assert(v == 42))
    }
  }
}
