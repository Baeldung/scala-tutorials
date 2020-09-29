package com.baeldung.scala.withfilter

object WithFilterVsFilter extends App {
  sealed trait Level
  object Level {
    case object Junior extends Level
    case object Mid extends Level
    case object Senior extends Level
  }
  case class Programmer(name: String,
                        level: Level,
                        knownLanguages: List[String])

  val programmers: List[Programmer] = List(
    Programmer(name = "Kelly",
               level = Level.Mid,
               knownLanguages = List("JavaScript")),
    Programmer(name = "John",
               level = Level.Senior,
               knownLanguages = List("Java", "Scala", "Kotlin")),
    Programmer(name = "Dave",
               level = Level.Junior,
               knownLanguages = List("C", "C++"))
  )

  val getName: Programmer => String =
    programmer => {
      println("get name" + programmer)
      programmer.name
    }

  val isMidOrSenior: Programmer => Boolean =
    programmer => {
      println("verify level " + programmer)
      List(Level.Mid, Level.Senior).contains(programmer.level)
    }

  val knowMoreThan1Language: Programmer => Boolean =
    programmer => {
      println("verify number of known languages " + programmer)
      programmer.knownLanguages.size > 1
    }

  println("Filter")
  val filteredLanguages = programmers
    .filter(isMidOrSenior)
    .filter(knowMoreThan1Language)
    .map(getName)

  println("WithFilter")
  val withFilteredLanguages = programmers
    .withFilter(isMidOrSenior)
    .withFilter(knowMoreThan1Language)
    .map(getName)

}
