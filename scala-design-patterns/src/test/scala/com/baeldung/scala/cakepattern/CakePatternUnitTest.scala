package com.baeldung.scala.cakepattern

import com.baeldung.scala.cakepattern.CakePattern.Test
import org.mockito.Mockito.when
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatestplus.mockito.MockitoSugar

trait TestRegistry
  extends CakePattern.TestExecutorComponent
  with CakePattern.TestEnvironmentComponent
  with MockitoSugar {
  override val env: TestEnvironment = mock[TestEnvironment]
  override val testExecutor: TestExecutor = new TestExecutor
}

class CakePatternUnitTest extends AnyFlatSpec with TestRegistry {

  "A TestExecutor" should "execute tests using a given environment" in {
    when(env.readEnvironmentProperties).thenReturn(Map("ENV" -> "true"))
    val test = Test(
      "test-1",
      { environment =>
        environment.getOrElse("ENV", "false").toBoolean
      }
    )
    assertResult(testExecutor.execute(List(test)))(true)
  }
}
