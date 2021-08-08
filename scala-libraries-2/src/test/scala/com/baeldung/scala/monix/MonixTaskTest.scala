package com.baeldung.scala.monix

import monix.execution.schedulers.TestScheduler
import org.scalatest.{FlatSpec, Matchers}
import scala.util.Success

class MonixTaskTest extends FlatSpec with Matchers{
  import MonixTask.sampleMonixTask
  "sampleMonixTask" should "be able to create and return a Task which adds two integer parameters" in {
    implicit val s:TestScheduler = TestScheduler()
    val task = sampleMonixTask(5, 5)
    val f = task.runToFuture
    s.tick()
    f.value shouldEqual Some(Success(10))
  }
}
