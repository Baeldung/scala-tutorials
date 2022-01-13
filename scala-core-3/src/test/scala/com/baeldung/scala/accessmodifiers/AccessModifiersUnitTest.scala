package com.baeldung.scala.accessmodifiers

import org.scalatest.{Matchers, WordSpec}

class AccessModifiersUnitTest extends WordSpec with Matchers {

  val rectangle = new Rectangle(3, 4, "gray", 1)

  "instance of Rectangle can access the members of it's inner object" should {
    "access public members" in {
      rectangle.innerCircle.printCodeInner
    }
  }

}
