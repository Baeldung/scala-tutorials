package com.baeldung.scala

import org.junit.Assert.assertEquals
import org.junit.{Before, Test}

import scala.util.Random

class FunctionsAndMethodsUnitTest {
  @Test
  def givenAnonymousFunction_whenPassItToAMethodCall_thenAnonymousFunctionUsage (): Unit = {
    val result1 = FunctionsAndMethods.anonymousFunctionUsage((number: Int) => number + 1)
    val result2 = FunctionsAndMethods.anonymousFunctionUsageWithApply((number: Int) => number + 1)

    assertEquals(result1, result2)
  }

  @Test
  def givenFunction_whenCheckingItsType_thenItIsAFunctionN(): Unit = {
    val functionWithoutParameters = () => Random.nextInt()
    val functionWithOneParameter = (number: Int) => number + 1
    val functionWithTwoParameters = (x: Int, y: Int) => (x + 1, y + 1)

    assert(functionWithoutParameters.isInstanceOf[Function0[Int]])
    assert(functionWithOneParameter.isInstanceOf[Function1[Int, Int]])
    assert(functionWithTwoParameters.isInstanceOf[Function2[Int, Int, (Int, Int)]])
  }

  @Test
  def givenByValueFunction_whenCallIt_thenValuesAreEquals(): Unit = {
    val (firstAccess, secondAccess) = FunctionsAndMethods.byValue(Random.nextInt)
    assert(firstAccess == secondAccess)
  }

  @Test
  def givenByNameFunction_whenCallIt_thenValuesAreDifferent(): Unit = {
    val (firstAccess, secondAccess) = FunctionsAndMethods.byName(Random.nextInt)
    assert(firstAccess != secondAccess)
  }

  @Test
  def givenExtensionMethod_whenImportInContext_thenWeCanUseIt(): Unit = {
    import FunctionsAndMethods._
    assertEquals(true, 10.isOdd)
    assertEquals(false, 11.isOdd)
  }
}