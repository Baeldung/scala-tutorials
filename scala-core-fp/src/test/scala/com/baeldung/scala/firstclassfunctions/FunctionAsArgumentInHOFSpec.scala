package com.baeldung.scala.firstclassfunctions

import org.scalatest.matchers.should.Matchers
import org.scalatest.flatspec.AnyFlatSpec

class FunctionAsArgumentInHOFSpec extends AnyFlatSpec with Matchers
{
    "HigherOrderFunction" should "take a function as argument to calculate square" in
    {
        val squareCalculated = FunctionAsArgumentInHOF.calcAnything(2, FunctionAsArgumentInHOF.calcSquare)
        assert(squareCalculated == 4)
    }

    "HigherOrderFunction" should "take a function as argument to calculate cube" in
    {
      val cubeCalculated = FunctionAsArgumentInHOF.calcAnything(3, FunctionAsArgumentInHOF.calcCube)
      assert(cubeCalculated == 27)
    }
}
