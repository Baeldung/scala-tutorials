package com.baeldung.scala.variance

import com.baeldung.scala.variance.Variance._
import org.scalatest.flatspec.AnyFlatSpec

class VarianceUnitTest extends AnyFlatSpec {

  "A TestSuite" should "contain both unit and integration tests" in {
    val expected = List(new UnitTest, new IntegrationTest)
    val suite: TestsSuite[Test] = new TestsSuite[UnitTest](expected)

    assertResult(expected) { suite.tests }
  }

  "Asserts on Employee" should "allow to test conditions on Employees and Persons" in {
    val bob = new Employee("Bob", 50000)
    val personAssert = new Assert[Person](p => p.name == "Alice")
    val employeeAssert = new Assert[Employee](e => e.name == "Bob" && e.salary < 70000)

    val tester = new AssertsEmployee(List(personAssert, employeeAssert))

    assert(!tester.execute(bob))
  }

  it should "not allow to test a condition on a Manager" in {
    val managerAssert = new Assert[Manager](m => m.manages.nonEmpty)

    assertDoesNotCompile("new AssertsEmployee(List(managerAssert))")
  }
}
