package com.baeldung.scala.traitabstractclass

abstract class Task(name: String) {
  def run: Unit = println(s"${name} is running")
}

class JobWithAbstractClass(name: String) extends Task(name)

object RunAbstract extends App {
  new JobWithAbstractClass("New Job").run
}

// output: New Job is running
