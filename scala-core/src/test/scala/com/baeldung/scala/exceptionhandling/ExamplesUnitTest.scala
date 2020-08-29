package com.baeldung.scala.exceptionhandling

import org.scalatest._
import org.scalatest.Assertions._
import scala.util.{Success, Failure}

class ExamplesUnitTest extends FlatSpec with Matchers {

  "tryCatch" should "handle NegativeNumberException" in {
    noException should be thrownBy Examples.tryCatch(-1, -2)
    assert(Examples.tryCatch(-20, -30) == -2)
  }

  it should "handle IntOverflowException" in {
    noException should be thrownBy Examples.tryCatch(Int.MaxValue, 1)
    assert(Examples.tryCatch(Int.MaxValue, 1) == -1)
  }

  it should "return the correct sum" in {
    noException should be thrownBy Examples.tryCatch(2, 1)
    assert(Examples.tryCatch(2, 3) == 5)
  }

  "trySuccessFailure" should "handle NegativeNumberException" in {
    import CalculatorExceptions._
    val result = Examples.trySuccessFailure(-1, -2)
    result match {
      case Failure(e) => assert(e.isInstanceOf[NegativeNumberException])
      case Success(_) => fail("Should fail!")
    }
  }

  it should "handle IntOverflowException" in {
    import CalculatorExceptions._
    val result = Examples.trySuccessFailure(Int.MaxValue, 1)
    result match {
      case Failure(e) => assert(e.isInstanceOf[IntOverflowException])
      case Success(_) => fail("Should fail!")
    }
  }

  it should "return the correct sum" in {
    import CalculatorExceptions._
    val result = Examples.trySuccessFailure(3, 2)
    result match {
      case Failure(e)      => fail("Should succed!")
      case Success(result) => assert(result == 5)
    }
  }

  "catchObjects" should "handle NegativeNumberException" in {
    import CalculatorExceptions._
    val result = Examples.catchObjects(-1, -2)
    result match {
      case Failure(e) => assert(e.isInstanceOf[NegativeNumberException])
      case Success(_) => fail("Should fail!")
    }
  }

  it should "handle IntOverflowException" in {
    import CalculatorExceptions._
    val result = Examples.catchObjects(Int.MaxValue, 1)
    result match {
      case Failure(e) => assert(e.isInstanceOf[IntOverflowException])
      case Success(_) => fail("Should fail!")
    }
  }

  it should "return the correct sum" in {
    import CalculatorExceptions._
    val result = Examples.catchObjects(3, 2)
    result match {
      case Failure(e)      => fail("Should succed!")
      case Success(result) => assert(result == 5)
    }
  }

  "customCatchObjects" should "handle NegativeNumberException" in {
    import CalculatorExceptions._
    val result = Examples.customCatchObjects(-1, -2)
    result match {
      case Failure(e) => assert(e.isInstanceOf[NegativeNumberException])
      case Success(_) => fail("Should fail!")
    }
  }

  it should "handle IntOverflowException" in {
    import CalculatorExceptions._
    assertThrows[IntOverflowException] {
      Examples.customCatchObjects(Int.MaxValue, 1)
    }
  }

  it should "return the correct sum" in {
    import CalculatorExceptions._
    val result = Examples.customCatchObjects(3, 2)
    result match {
      case Failure(e)      => fail("Should succed!")
      case Success(result) => assert(result == 5)
    }
  }

  "customCatchObjects composed with trySuccessFailure" should "return the correct sum" in {
    import CalculatorExceptions._
    val result = Examples.customCatchObjects(3, 2) flatMap (Examples
      .trySuccessFailure(_, 3))
    result match {
      case Failure(e)      => fail("Should succed!")
      case Success(result) => assert(result == 8)
    }
  }

  it should "print an error" in {
    import CalculatorExceptions._
    val result = Examples.customCatchObjects(-1, -2) flatMap (Examples
      .trySuccessFailure(_, 3))
    result match {
      case Failure(e)      => assert(e.isInstanceOf[NegativeNumberException])
      case Success(result) => fail("Should fail!")
    }
  }

  it should "ignore specified exceptions" in {
    Examples.ignoringAndSum(-1, -2)
  }
}
