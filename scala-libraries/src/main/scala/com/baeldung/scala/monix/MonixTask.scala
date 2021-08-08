package com.baeldung.scala.monix

import monix.eval.Task
import monix.execution.schedulers.TestScheduler

object MonixTask extends App{

  implicit val s:TestScheduler = TestScheduler()
  def sampleMonixTask(a:Int, b:Int): Task[Int] = Task {
    val result = {
      a + b
    }
    result
  }
  val task = sampleMonixTask(5, 5)

  // Tasks get evaluated on invoking runToFuture
  val f = task.runToFuture
  println(f.value)  // Prints Some(Success(10))

  // Invoking using a Callback:
  val cancelable = task.runAsync { result =>
    result match {
      case Right(value) => println(value)
      case Left(ex) => println(s"Exception: ${ex.getMessage}")
    }
  } // prints 10

}
