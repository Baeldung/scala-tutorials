package com.baeldung.scala.traits

trait SoundProduction {
  this: RecordLabel =>
  var engineer: String

  def produce(): String

  var studio: String

  def getStudio(): String = s"Produced at studio $studio"
}
