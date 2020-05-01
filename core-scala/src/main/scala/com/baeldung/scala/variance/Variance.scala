package com.baeldung.scala.variance

object Variance {

  class Person(val name: String)
  class Employee(name: String, val salary: Int) extends Person(name)
  class Manager(name: String, salary: Int, val manages: List[Employee]) extends Employee(name, salary)

  class Assert[-T](expr: T => Boolean) {
    def assert(target: T): Boolean = expr(target)
  }

  val alice = new Person("Alice")
  val bob = new Employee("Bob", 50000)
  val kris = new Manager("Kris", 100000, List(bob))

  val personAssert = new Assert[Person](p => p.name == "Alice")
  val employeeAssert = new Assert[Employee](e => e.name == "Bob" && e.salary < 70000)
  val managerAssert = new Assert[Manager](m => m.manages.nonEmpty)

  def testEmployee(assert: Assert[Employee], employee: Employee): Boolean =
    assert.assert(employee)

  testEmployee(employeeAssert, bob)
  // testEmployee(personAssert, bob)
  // testEmployee(managerAssert, bob)

  trait Test[T] {
    def asserts: List[Assert[T]]
    def execute(target: T): Boolean =
      asserts
        .map(a => a.assert(target))
        .reduce(_ && _)
  }

  class TestEmployee(val asserts: List[Assert[Employee]]) extends Test[Employee]

  val tester = new TestEmployee(List(personAssert, employeeAssert))
  tester.execute(bob)
/*
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
  */
}
