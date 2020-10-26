package com.baeldung.scala.await

import scala.concurrent.{ Await, Future }
import scala.io.Source
import scala.concurrent.ExecutionContext.Implicits.global

object AwaitFuture  {

  def fetchDataFromUrl(url : String) : Future[String] = Future {
    Source.fromURL(url).getLines().mkString
  }

  def fetchDataFrom(url : String, waitTime : Long = 0l) : Future[String] =  Future {
    Thread.sleep(waitTime)
    Source.fromURL(url).getLines().mkString
  }

  def futureWithoutException(): Future[String] = Future {
    "Hello"
  }
  def futureWithException(): Future[String] = Future {
    throw new NullPointerException
  }
}
