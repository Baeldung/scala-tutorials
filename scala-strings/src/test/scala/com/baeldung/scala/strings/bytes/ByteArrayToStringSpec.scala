package com.baeldung.scala.strings.bytes

import com.baeldung.scala.strings.bytes.ByteArrayToString._
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ByteArrayToStringSpec extends AnyWordSpec with Matchers {
  "byte array" should {
    val helloInUtf16 = Array[Byte](104, 101, 108, 108, 111)
    val helloInUtf16Le = Array[Byte](104, 0, 101, 0, 108, 0, 108, 0, 111, 0)
    val hello = "hello"

    "return correct String for usingNewString" in {
      usingNewString(helloInUtf16) MustBe hello
    }

    "return correct String for usingToChar" in {
      usingToChar(helloInUtf16) MustBe hello
    }

    "return correct String for usingDifferentCharSet" in {
      usingDifferentCharSet(helloInUtf16Le) MustBe hello
    }
  }
}
