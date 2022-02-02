package com.baeldung.scala3.implicits.extensions

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ExtensionsUnitTest extends AnyWordSpec with Matchers {

  "String Extensions" should {
    import StringExtensions._

    "convert string to snake case" in {
      val str = "helloWorldScala3"
      val expected = "hello_world_scala3"
      str.toSnakeCase shouldBe expected
    }

    "check if a string contains only numbers" in {
      val numStr = "123456"
      numStr.isNumber shouldBe true
    }

    "should return false for isNumber if the string contains non numeric" in {
      val numStr = "102e"
      numStr.isNumber shouldBe false
    }
  }

  "Generic Extensions" should {
    import GenericExtensions._

    "work for List[Int] for getSecond method" in {
      val intList = List(1, 2, 3, 4)
      intList.getSecond shouldBe Some(2)
    }

    "work for List[String] for getSecond method" in {
      val strList = List("first", "second", "third")
      strList.getSecond shouldBe Some("second")
    }

    "successfully able to add any 2 integers using add() method" in {
      val int1 = 10
      val int2 = 20
      int1.add(int2) shouldBe 30
    }

    "successfully able to add any 2 floats using add() method" in {
      val float1 = 4.2f
      val float2 = 2.0f
      float1.add(float2) shouldBe 6.2f
    }

    "successfully able to add any 2 integers using add2() method" in {
      val int1 = 1
      val int2 = 2
      int1.add2(int2) shouldBe 3
    }

    "successfully able to add any 2 floats using add2() method" in {
      val float1 = 1.0f
      val float2 = 0.1f
      float1.add2(float2) shouldBe 1.1f
    }

  }

}
