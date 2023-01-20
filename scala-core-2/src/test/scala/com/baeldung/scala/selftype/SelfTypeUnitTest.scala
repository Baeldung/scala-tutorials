package com.baeldung.scala.selftype

import org.scalatest.FlatSpec

class SelfTypeUnitTest extends FlatSpec {

  "TestExecutor class" should "be extended creating a new type" in {
    assertCompiles(
      """
        |import SelfType._
        |class JUnit5TestExecutor extends TestExecutor with WindowsTestEnvironment {}
        |""".stripMargin
    )
  }

  it should "be instantiated directly using mixing a TestEnvironment" in {
    assertCompiles(
      """
        |import SelfType._
        |val windowsGeneralExecutor: TestExecutor = new TestExecutor with WindowsTestEnvironment
        |""".stripMargin
    )
  }
}
