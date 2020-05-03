package com.baeldung.scala.variance

object Variance {

  class Person(val name: String)
  class Employee(name: String, val salary: Int) extends Person(name)
  class Manager(name: String, salary: Int, val manages: List[Employee]) extends Employee(name, salary)

  sealed trait Test
  class UnitTest extends Test
  class IntegrationTest extends UnitTest
  class FunctionalTest extends IntegrationTest
  class End2EndTest extends FunctionalTest

  class TestsSuite[+T](tests: List[T])

  val suite: TestsSuite[Test] = new TestsSuite[UnitTest](List(new UnitTest))

  class Assert[-T](expr: T => Boolean) {
    def assert(target: T): Boolean = expr(target)
  }

  val bob = new Employee("Bob", 50000)

  val personAssert = new Assert[Person](p => p.name == "Alice")
  val employeeAssert = new Assert[Employee](e => e.name == "Bob" && e.salary < 70000)
  val managerAssert = new Assert[Manager](m => m.manages.nonEmpty)

  trait Asserts[T] {
    def asserts: List[Assert[T]]
    def execute(target: T): Boolean =
      asserts
        .map(a => a.assert(target))
        .reduce(_ && _)
  }

  class AssertsEmployee(val asserts: List[Assert[Employee]]) extends Asserts[Employee]

  val tester = new AssertsEmployee(List(personAssert, employeeAssert))
  tester.execute(bob)
}
