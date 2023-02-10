package com.baeldung.scala3.quitesyntax

import java.util.{Random as _, *}
import scala.collection.*
import scala.collection.mutable.Map as MMap // Scala 2

object Example {

  def luckyNumberScala3(): Int =
    println("Returning lucky number...")

    7

  def luckyNumberScala2(): Int = {
    println("Returning lucky number...")

    7
  }

  7 match // Scala 3
    case 7 => "Lucky number"
    case _ => "Non lucky number"

  7 match { // Scala 2
    case 7 => "Lucky number"
    case _ => "Non lucky number"
  }

  for i <- 0 until 1 do println(i) // Scala 3
  for (i <- 0 until 1) println(i) // Scala 2

  if 5 % 2 == 0 then println("even") // Scala 3
  else println("odd")
  if (5 % 2 == 0) println("even") // Scala 2
  else println("odd")

  for
    i <- 0 until 5 // Scala 3
    if i % 2 == 1
    iWasOdd = 2 * i
  yield iWasOdd

  for {
    i <- (0 until 5) // Scala 2
    if (i % 2 == 1)
    iWasOdd = 2 * i
  } yield iWasOdd

}

class Example {
  val positiveIntScala3: Int => Int =
    case x if x > 0 => x

  val positiveIntScala2: Int => Int = {
    case x if x > 0 => x
  }

}

case class AClassScala3():
  def doSomething(): Unit = ???

object AClassScala3:
  def apply(): AClassScala3 = ???

trait ATraitScala3:
  def doSomethingElsee(): Unit

case class AClassScala2() {
  def doSomething(): Unit = ???
}

object AClassScala2 {
  def apply(): AClassScala2 = ???
}

trait ATraitScala2 {
  def doSomethingElsee(): Unit
}
