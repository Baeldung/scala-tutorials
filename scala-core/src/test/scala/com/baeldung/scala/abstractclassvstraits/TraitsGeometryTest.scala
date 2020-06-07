package com.baeldung.scala.abstractclassvstraits

import org.scalatest.{Matchers, WordSpec}

/**
  * Test for the Example of Trait composition
  * @author Oscar Forero
  */

class TraitsGeometryTest extends WordSpec with Matchers {
  import TraitsGeometry._

  "Points" should {
    val p = new Point(1, 3)  
    
    "have an origin" in {
        p.origin.x shouldBe 1
        p.origin.y shouldBe 3
    }

    "be drawable" in {
        p.draw() shouldBe "Point at (1, 3)"
    }
  }

  "Squares" should {
      val s = new Square(x=5, y=10, side=3)
      "have an origing" in {
        s.origin.x shouldBe 5
        s.origin.y shouldBe 10
      }

      "be drawable" in {
          s.draw() shouldBe "Square at (5, 10) with 9 area"
      }
  }
}
