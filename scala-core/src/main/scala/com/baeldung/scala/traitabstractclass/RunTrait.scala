package com.baeldung.scala.traitabstractclass

trait Base {
  def stop: Unit = println("Base way of stop")
}

trait Stoppable extends Base {
  override def stop: Unit = println("Stoppable way of stop")
}

trait Terminable extends Base {
  override def stop: Unit = println("Terminable way of stop")
}

class JobWithTraits extends Terminable with Stoppable

object RunTrait extends App {
  new JobWithTraits().stop
}

// output: Stoppable way of stop
