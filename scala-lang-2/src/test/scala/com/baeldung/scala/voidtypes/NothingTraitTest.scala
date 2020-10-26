package com.baeldung.scala.voidtypes

import org.scalatest.funsuite.AnyFunSuite

class NothingTraitTest extends AnyFunSuite{
  test("Nothing return function test"){

    val exceptionThrown = intercept[Exception]{
      val e:Exception = new Exception("An error occured");
      NothingTrait.logException(e);
    }
    assert(exceptionThrown.getMessage == "My New Exception")
  }
}
