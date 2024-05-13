package com.baeldung.refined

import eu.timepit.refined._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.numeric._
import eu.timepit.refined.string._
import eu.timepit.refined.collection._
import eu.timepit.refined.char._
import eu.timepit.refined.boolean._
import eu.timepit.refined.api.Validate

object RefinedIntro {
  // val oddNumber: Refined[Int, Odd] = 8
  // val oddNumber: Int Refined Odd = 8
  val age: Int Refined Less[35] = 30
  val ageInterval: Int Refined Interval.Closed[30, 35] = 35
  val age2: Int Refined GreaterEqual[35] = 35
  val ageInput: Int = 36
  val ageCheck = refineV[GreaterEqual[35]](ageInput)

  val myDigit: Char Refined Digit = '8'
  val myLetter: Char Refined Letter = 'H'

  val myName: String Refined StartsWith["S"] = "Sandra"
  val myName2: String Refined EndsWith["t"] = "Herbert"
  // val myName3: String Refined StartsWith["s"] = "Sandra"
  // val myAddr: String Refined IPv6 = "127.0.0.1"

  type myRegex = MatchesRegex["""[A-Za-z0-9]+"""]
  val accessCode: String Refined myRegex = "DC13h"

  type myIntRegex = myRegex And ValidInt
  val accessCode2: String Refined myIntRegex = "97426"
  // val accessCode3: String Refined myIntRegex = "9742B"

  val fruits = List("Banana", "Orange", "Lemon", "Guava")
  val contains = refineV[Contains["Berry"]](fruits)
  val forall = refineV[Forall[Trimmed]](fruits)
  val last = refineV[Last[Uuid]](fruits)
  val size = refineV[Size[Less[5]]](fruits)

  case class Person(name: String, height: Double)
  case class Tall()
  case class Average()
  case class Short()

  implicit val tallValidate: Validate.Plain[Person, Tall] =
    Validate.fromPredicate(
      p => p.height >= 6.0,
      p => s"(${p.name} is tall)",
      Tall()
    )

  implicit val averageValidate: Validate.Plain[Person, Average] =
    Validate.fromPredicate(
      p => p.height >= 5.0 && p.height < 6.0,
      p => s"(${p.name} is average)",
      Average()
    )

  implicit val shortValidate: Validate.Plain[Person, Short] =
    Validate.fromPredicate(
      p => p.height < 5.0,
      p => s"(${p.name} is short)",
      Short()
    )

  val tall = refineV[Tall](Person("Herbert", 5.5))
  val average = refineV[Average](Person("Herbert", 5.5))
}

object Hello extends App {
  import RefinedIntro._
  println(refineV[Short](Person("Herbert", 4.0)))
}
