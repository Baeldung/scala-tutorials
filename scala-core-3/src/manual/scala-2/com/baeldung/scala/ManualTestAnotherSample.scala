package com.baeldung.scala

import org.scalatest.wordspec.AnyWordSpec

class ManualTestAnotherSample extends AnyWordSpec {

  "Shared SBT Config Manual test" should {
    "run only with custom test configuration/profile using the shared settings" in {
      println("++ Another Manual test started executing.....")
      fail
    }
  }

}
