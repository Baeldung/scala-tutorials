package com.baeldung.scala.traits

import java.util.Date

object PracticalAbstractClassTraitDifference {

  trait Restricted {
    def isAllowable(country: String): Boolean
  }

  abstract class Fruit(expiry: Option[Date]) {
    def isFresh = expiry.forall(_.before(new Date))
    def ship(country: String): Unit
  }

  class Apple(expiry: Date) extends Fruit(Some(expiry)) with Restricted {
    override def isAllowable(country: String) = country == "Wakanda"

    override def ship(country: String) =
      if (isAllowable(country) && isFresh) {
        println(s"Ship to $country before $expiry")
      } else {
        println(s"Apple is not allowed in $country")
      }
  }

  val apple = new Apple(new Date)

  class Foo

  val foo = new Foo with Restricted {
    override def isAllowable(c: String) = c.startsWith("foo")
  }
}
