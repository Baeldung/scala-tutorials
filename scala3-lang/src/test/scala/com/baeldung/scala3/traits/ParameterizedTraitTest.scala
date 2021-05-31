package com.baeldung.scala3.traits

import com.baeldung.scala3.traits.ParameterizedTrait.{Foo, Base}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.time.LocalDate

class ParameterizedTraitTest extends AnyWordSpec with Matchers {
  "Traits can have parameters" in {
      val foo = new Foo    
      assert(foo.msg == "Foo")
    }
}
