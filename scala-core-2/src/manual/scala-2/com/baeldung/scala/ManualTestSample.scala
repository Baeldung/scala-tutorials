package com.baeldung.scala

import org.scalatest.wordspec.AnyWordSpec

class ManualTestSample extends AnyWordSpec {

  "Manual test" should {
    "run only with custom test configuration/profile" in {
      println("++ Manual test started executing.....")
      fail
    }
  }

}
