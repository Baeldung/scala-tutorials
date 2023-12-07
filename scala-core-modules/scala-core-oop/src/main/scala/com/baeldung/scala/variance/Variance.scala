package com.baeldung.scala.variance

object Variance {

  class Person(val name: String)
  class Employee(name: String, val salary: Int) extends Person(name)
  class Manager(name: String, salary: Int, val manages: List[Employee])
    extends Employee(name, salary)

  sealed trait Test
  class UnitTest extends Test
  class IntegrationTest extends UnitTest
  class FunctionalTest extends IntegrationTest

  class TestsSuite[+T](val tests: List[T])

  class Assert[-T](expr: T => Boolean) {
    def assert(target: T): Boolean = expr(target)
  }

  trait Asserts[T] {
    def asserts: List[Assert[T]]
    def execute(target: T): Boolean =
      asserts
        .map(a => a.assert(target))
        .reduce(_ && _)
  }

  class AssertsEmployee(val asserts: List[Assert[Employee]])
    extends Asserts[Employee]
}
