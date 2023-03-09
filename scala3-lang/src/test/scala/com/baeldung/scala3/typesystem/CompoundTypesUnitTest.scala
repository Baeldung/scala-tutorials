package com.baeldung.scala3.typesystem

import com.baeldung.scala3.typesystem.CompoundTypes.Intersection.*
import com.baeldung.scala3.typesystem.CompoundTypes.Union.*
import com.baeldung.scala3.typesystem.types.*
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.time.LocalDate

class CompoundTypesUnitTest extends AnyWordSpec with Matchers {

  "parse function which reutrns union type" should {
    "parse 123 to the integer type" in {
      parse("123") shouldBe 123
    }

    "return a string indicating that the input is not a number" in {
      parse("123Foo") shouldBe "Not a number anyways"
    }

  }

  "shutdown function which require intersection of Show and Closable types" should {
    "shoutdown a res object which is intersection of these two data types" in {
      shutdown(res) shouldBe ()
    }

  }

}
