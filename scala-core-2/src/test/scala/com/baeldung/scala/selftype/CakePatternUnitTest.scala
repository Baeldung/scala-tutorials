package com.baeldung.scala.selftype

import org.scalamock.scalatest.MockFactory
import org.scalatest.FlatSpec

trait TestRegistry
  extends CakePattern.TestExecutorComponent
    with CakePattern.TestEnvironmentComponent
    with MockFactory {
  override val env: TestEnvironment = mock[TestEnvironment]
  override val testExecutor: TestExecutor = new TestExecutor
}

class CakePatternUnitTest extends FlatSpec with TestRegistry {
  
  (env.readEnvironmentProperties _).expects(_).returning(Map("ENV" -> "true"))
  "A TestExecutor" should "execute tests using a given environment" in {
    // assertResult(expected) { suite.tests }
  }
}
