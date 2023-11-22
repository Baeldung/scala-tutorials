package com.baeldung.scala.companionobject

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TaskUnitTest extends AnyFlatSpec with Matchers {

  "Task" should "be instantiated with default constructor" in {
    val task = new Task("do something")
    task.description should be("do something")
    task.status should be("pending")
  }

  it should "be able to construct without new keywork" in {
    val task = Task("do something")
    task.description should be("do something")
    task.status should be("pending")
  }

  it should "have a contructor with status parameter" in {
    val task = Task("do something", "started")
    task.description should be("do something")
    task.status should be("started")
  }

  it should "extract status" in {
    val task = Task("do something")
    val (description, status) = Task.unapply(task)
    description should be("do something")
    status should be("pending")
  }
}
