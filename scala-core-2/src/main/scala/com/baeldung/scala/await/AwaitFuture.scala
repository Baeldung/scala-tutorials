package com.baeldung.scala.await

import scala.concurrent.{ Await, Future }
import scala.io.Source
import scala.concurrent.ExecutionContext.Implicits.global

object AwaitFuture extends App {

  // creating futures and calling a thread.sleep to wait
  Future {
    val contents = Source.fromURL("https://www.facebook.com").getLines().mkString
    println(contents)
  }
  Thread.sleep(1000)

  // using Await.ready to wait on futures
  val future: Future[String] = Future {
    Thread.sleep(3000)
    Source.fromURL("https://www.facebook.com", "UTF-8").getLines().mkString
  }

  import scala.concurrent.duration._ //needed to write 2 seconds
  println(future) // prints not completed
  val result: Future[String] = Await.ready(future, 5 seconds)
  println(result) // prints result as a Future[Success[String]]


  //Using Await.result to wait on futures
  val future2: Future[String] = Future {
    Thread.sleep(3000)
    Source.fromURL("https://www.facebook.com", "UTF-8").getLines().mkString
  }
  println(future2) // prints not completed
  val result2: String = Await.result(future2, 5 seconds)
  println(result2) // prints result as a string


  //differences between Await.ready and Await.result
  def f1: Future[String] = Future {
    Thread.sleep(1000)
    "Hello"
  }
  def f2: Future[String] = Future {
    Thread.sleep(1000)
    throw new NullPointerException
  }

  Await.ready(f1, 2 seconds) // Future(Success(Hello))
  Await.ready(f2, 2 seconds) // Future(Failure(java.lang.NullPointerException))

  Await.result(f1, 2 seconds) // "Hello"
  Await.result(f2, 2 seconds) // crashes with java.lang.NullPointerException

}
