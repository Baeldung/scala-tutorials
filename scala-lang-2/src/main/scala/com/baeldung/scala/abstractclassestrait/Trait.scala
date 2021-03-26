package com.baeldung.scala.abstractclassestrait

trait OnlineCourse {
  val url: String
}

object FunctionalProgrammingInScala
  extends Publication("Functional Programming In Scala")
  with OnlineCourse {

  override val url: String = "..."
}
