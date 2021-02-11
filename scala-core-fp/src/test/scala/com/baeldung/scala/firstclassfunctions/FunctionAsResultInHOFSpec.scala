package com.baeldung.scala.firstclassfunctions

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class FunctionAsResultInHOFSpec extends AnyFlatSpec with Matchers
{
    "HigherOrderFunction" should "return a function as result to perform addition" in
    {
        val additionResult = FunctionAsResultInHOF.performArithmeticOperation(2, 4, "addition")
        assert(additionResult == 6)
    }

    "HigherOrderFunction" should "return a function as result to perform subtraction" in
    {
        val subtractionResult = FunctionAsResultInHOF.performArithmeticOperation(10, 6, "subtraction")
        assert(subtractionResult == 4)
    }

    "HigherOrderFunction" should "return a function as result to perform multiplication" in
    {
        val multiplicationResult = FunctionAsResultInHOF.performArithmeticOperation(8, 5, "multiplication")
        assert(multiplicationResult == 40)
    }
}
