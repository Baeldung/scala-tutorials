package com.baeldung.scala.await

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.io.Source

object AwaitFuture {

  def fetchDataFromUrl(url: String): Future[String] = Future {
    s"Mock response from $url"
  }

  def fetchDataFrom(url: String, waitTime: Long = 0L): Future[String] = Future {
    Thread.sleep(waitTime)
    s"Mock response from $url"
  }

  def futureWithoutException(): Future[String] = Future {
    "Hello"
  }
  def futureWithException(): Future[String] = Future {
    Thread.sleep(250)
    throw new NullPointerException
  }
}
