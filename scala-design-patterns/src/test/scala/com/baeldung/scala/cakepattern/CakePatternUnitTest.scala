package com.baeldung.scala.cakepattern

import com.baeldung.scala.cakepattern.CakePattern.Test
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

  "A TestExecutor" should "execute tests using a given environment" in {
    (env.readEnvironmentProperties _).expects().returning(Map("ENV" -> "true"))
    val test = Test(
      "test-1",
      { environment =>
        environment.getOrElse("ENV", "false").toBoolean
      }
    )
    assertResult(testExecutor.execute(List(test)))(true)
  }
}
