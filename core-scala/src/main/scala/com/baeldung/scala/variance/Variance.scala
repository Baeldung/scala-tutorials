package com.baeldung.scala.variance

object Variance {

  sealed trait Assert
  class GenericAssert extends Assert
  class NumericAssert extends GenericAssert
  class IntAssert extends NumericAssert

  // Covariance
  sealed trait Test[+A] {
    def asserts: List[A]
    def execute = println("Executing a test")
  }
  case class GenericTest(asserts: List[GenericAssert]) extends Test[GenericAssert]
  case class NumericTest(asserts: List[NumericAssert]) extends Test[NumericAssert]
  case class IntTest(asserts: List[IntAssert]) extends Test[IntAssert]

  val genericAssert = new GenericAssert
  val numericAssert = new NumericAssert
  val intAssert = new IntAssert

  val test: GenericTest = GenericTest(List(intAssert))
  val intTest: IntTest = IntTest(List(intAssert))

  val executor: Test[GenericAssert] => Unit = test => test.execute

  executor(test)
  executor(intTest)

  sealed trait TestFramework
  class JUnit extends TestFramework {
    def testWithJUnit(): Unit = println("Testing with JUnit framework")
  }
  class JUnit4 extends JUnit {
    def testWithJUnit4(): Unit = println("Testing with JUnit4 framework")
  }
  class JUnit4WithMockito extends JUnit4 {
    def testWithJUnit4WithMockito(): Unit = println("Testing with JUnit4, using Mockito")
  }

  abstract class TestsExecutor[T](framework: T) {
    def execute(func: T => Unit): Unit = {
      println("Executing tests")
      func(framework)
    }
  }

  def executeTestWithJUnit[T](executor: (T => Unit), frmwk: T): Unit = {
    println("Executing tests")
    executor(frmwk)
  }

  val junit: JUnit = new JUnit()
  val junit4: JUnit4 = new JUnit4()
  val junit4WithMockito: JUnit4WithMockito = new JUnit4WithMockito()

  val testWithJUnit4: JUnit4 => Unit = _.testWithJUnit4()
  val testWithJUnit: JUnit => Unit = _.testWithJUnit()
  val testWithJUnitAndMockito: JUnit4WithMockito => Unit = _.testWithJUnit4WithMockito()

  class JUnit4Executor(framework: JUnit4) extends TestsExecutor(framework)
  val junit4Executor = new JUnit4Executor(junit4)
  junit4Executor.execute(testWithJUnit4)
  junit4Executor.execute(testWithJUnit)
  junit4Executor.execute(testWithJUnitAndMockito)

  executeTestWithJUnit(testWithJUnit4, junit4)
  executeTestWithJUnit(testWithJUnit, junit4)
  // executeTestWithJUnit(testWithJUnitAndMockito, junit4)
}
