package com.baeldung.scala.abstractclassestrait

abstract class Publication(val title: String)

object WarAndPeace extends Publication("War and Peace")

class ProgrammingInScala(ed: Int)
  extends Publication(s"Programming in Scala. Edition: $ed")
