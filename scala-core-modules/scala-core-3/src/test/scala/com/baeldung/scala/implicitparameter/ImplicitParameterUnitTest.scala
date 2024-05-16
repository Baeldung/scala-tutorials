package com.baeldung.scala.implicitparameter

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ImplicitParameterUnitTest extends AnyWordSpec with Matchers {

  import ImplicitParameter._
  "Implicit Parameter" should {
    implicit val red: Color = Color("red")
    //  implicit val green: Color = Color("green") // Enable this will be resulted in failed compilation.
    implicit val pen: DrawingDevice = DrawingDevice("pen")

    "able to pass in implicitly" in {
      assert(
        write("A good day") ==
          """Writing "A good day" in red color by pen."""
      )
      assert(
        write("Drink a cup of coffee") ==
          """Writing "Drink a cup of coffee" in red color by pen."""
      )
      assert(
        write("Write some code") ==
          """Writing "Write some code" in red color by pen."""
      )
    }

    "able to pass in explicitly" in {
      assert(
        write("A good day")(red, pen) ==
          """Writing "A good day" in red color by pen."""
      )
      assert(
        write("Drink a cup of coffee")(red, pen) ==
          """Writing "Drink a cup of coffee" in red color by pen."""
      )
      assert(
        write("Write some code")(red, pen) ==
          """Writing "Write some code" in red color by pen."""
      )
    }
  }
}
