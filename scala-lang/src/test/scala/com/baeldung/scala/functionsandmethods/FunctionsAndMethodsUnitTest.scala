package com.baeldung.scala.functionsandmethods

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

  @Test
  def givenLine45_whenUseItInAPlot_thenCorrectResults(): Unit = {
    val a45DegreeLine = FunctionsAndMethods.line(1,0)
    val results = FunctionsAndMethods.plot(a45DegreeLine)
    val expected = List(-10.0, -9.0, -8.0, -7.0, -6.0, -5.0, -4.0, -3.0, -2.0, -1.0,
                        0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)
    assertEquals(expected, results)
  }

  @Test
  def givenNestedMethod_whenUseIt_thenCorrectResults(): Unit = {
    val factorialResult = FunctionsAndMethods.factorial(10)
    val expected = 3628800;
    assertEquals(expected, factorialResult)
  }

  @Test
  def givenParameterizedMethod_whenUseIt_thenCorrectResults(): Unit = {
    val strings = Seq("a", "b", "c")
    val first = FunctionsAndMethods.pop(strings)
    assertEquals("a", first)

    val ints = Seq(10, 3, 11, 22, 10)
    val second = FunctionsAndMethods.pop(ints)
    assertEquals(10, second)


  }
}