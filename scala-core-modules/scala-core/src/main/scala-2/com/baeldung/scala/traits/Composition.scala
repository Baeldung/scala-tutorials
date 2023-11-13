package com.baeldung.scala.traits

trait Composition extends Orchestration with Mixing {
  var composer: String

  def compose(): String

  var studio: String

  def getStudio(): String = s"Composed at studio $studio"
}
