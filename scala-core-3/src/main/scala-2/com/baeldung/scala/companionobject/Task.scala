package com.baeldung.scala.companionobject

class Task(val description: String) {
  private var _status: String = "pending"

  def this(description: String, status: String) = {
    this(description)
    this._status = status
  }

  def status(): String = _status
}

object Task {
  def apply(description: String): Task = new Task(description)

  def apply(description: String, status: String): Task = {
    val task = new Task(description)
    task._status = status
    task
  }

  def unapply(task: Task): Tuple2[String, String] =
    (task.description, task.status())
}
