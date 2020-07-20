package com.baeldung.scala.traits

import com.baeldung.scala.traits.ObjectInstanceTrait.{Process, Logger}
import org.scalatest.FunSuite

class ObjectInstanceTraitTest extends FunSuite {

  test("Object Instance Trait Test") {
    val vanillaProcess = new Process
    assert(vanillaProcess.isInstanceOf[Logger] === false)

    val loggedProcess = new Process with Logger
    assert(loggedProcess.isInstanceOf[Logger] === true)
  }

}
