package com.baeldung.scala.withfilter

import java.util.concurrent.atomic.AtomicInteger
import org.scalatest.{Matchers, WordSpec}

import scala.collection.WithFilter

class WithFilterVsFilterUnitTest extends WordSpec with Matchers {

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

  def isMidOrSenior(implicit counter: AtomicInteger): Programmer => Boolean =
    programmer => {
      counter.incrementAndGet()
      println("verify level " + programmer)
      List(Level.Mid, Level.Senior).contains(programmer.level)
    }

  def knowsMoreThan1Language(
      implicit counter: AtomicInteger): Programmer => Boolean =
    programmer => {
      counter.incrementAndGet()
      println("verify number of known languages " + programmer)
      programmer.knownLanguages.size > 1
    }

  val getName: Programmer => String =
    programmer => {
      println("get name " + programmer)
      programmer.name
    }

  "Filter" should {
    "filter programmers" in {
      implicit val counter: AtomicInteger = new AtomicInteger(0)

      val desiredProgrammers: List[Programmer] = programmers
        .filter(isMidOrSenior)
        .filter(knowsMoreThan1Language)

      counter.get() shouldBe 5

      desiredProgrammers.map(getName) shouldBe List("John")
      counter.get() shouldBe 5
    }
  }

  "WithFilter" should {

    "filter programmers" in {
      implicit val counter: AtomicInteger = new AtomicInteger(0)

      val desiredProgrammers: WithFilter[Programmer, List] =
        programmers
          .withFilter(isMidOrSenior)
          .withFilter(knowsMoreThan1Language)

      counter.get() shouldBe 0

      desiredProgrammers.map(getName) shouldBe List("John")
      counter.get() shouldBe 5
    }

  }

}
