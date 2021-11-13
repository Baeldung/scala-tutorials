package com.baeldung.scala.traitsandabstractclasses

import com.baeldung.scala.traitsandabstractclasses.FlyTrait
import org.scalatest.FunSuite

class FlyTraitTest extends FunSuite {
  test("FlyTrait location definition returns expected output") {
    val fly = new FlyTrait {}
    val result = fly.location

    assert(result == "I'm at the Sky!")
  }
}