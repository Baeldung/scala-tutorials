package com.baeldung.scala.accessmodifiers

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class AccessModifiersUnitTest extends AnyWordSpec with Matchers {

  val rectangle = new Rectangle(3, 4, "gray", 1)

  "instance of Rectangle can access the members of it's inner object" should {
    "access public members" in {
      rectangle.innerCircle.printCodeInner
    }
  }

}
