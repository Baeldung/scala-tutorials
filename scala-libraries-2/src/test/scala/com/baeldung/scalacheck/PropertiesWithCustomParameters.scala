package com.baeldung.scalacheck

import org.scalacheck.Prop.forAll
import org.scalacheck.Properties
import org.scalacheck.Test.{Parameters, TestCallback}

object PropertiesWithCustomParameters extends Properties("CustomParameters") {

  override def overrideParameters(p: Parameters): Parameters = {
    p
      .withMinSuccessfulTests(50)
      .withTestCallback(new TestCallback {
        override def onPropEval(name: String, threadIdx: Int, succeeded: Int, discarded: Int): Unit = {
          println(s"Evaluating prop with name: $name")
        }
      })
  }

  property("length") = forAll { (str: String) =>
    str.length >= 0
  }
}
