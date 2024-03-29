package com.baeldung.scala3.implicits

import com.baeldung.scala3.implicits.ProvidingContextualEnvironment.*
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.time.LocalDate
import scala.concurrent.duration.*
import scala.concurrent.{Await, ExecutionContext}
import scala.language.implicitConversions
class ProvidingContextualEnvironmentUnitTest extends AnyWordSpec with Matchers {

  "calling square function requiring execution context" should {
    "run without problem by providing the execution context using given keyword" in {
      given ExecutionContext = ExecutionContext.global

      Await.result(square(4), 1.seconds) shouldBe 16
    }
  }
}
