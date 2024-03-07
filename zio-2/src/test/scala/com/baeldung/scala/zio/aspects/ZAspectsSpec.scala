package com.baeldung.scala.zio.aspects

import com.baeldung.scala.zio.aspects.ZAspectsSpec.test
import zio.*
import zio.test.*
import zio.test.TestAspect.{aroundWith, ignore, nonFlaky, timeout}

object ZAspectsSpec extends ZIOSpecDefault {
  override def spec = suite("ZAspectSpec")(
    test("Tautology test") {
      assertTrue(true)
    },
    test("Tautology test") {
      assertTrue(true)
    } @@ ignore,
    test("Run effects around a test") {
      for {
        plt <- System.env("PLATFORM")
      } yield assertTrue(plt.contains("Baeldung"))
    } @@ aroundWith {
      val platform = "Baeldung"
      TestSystem.putEnv("PLATFORM", platform) *> ZIO.succeed(platform)
    }(envVar => ZIO.debug(s"Platform: $envVar")),
    test("Non flaky") {
      assertTrue(true)
    } @@ nonFlaky(10)
    /* Commented out as otherwise the test suite will fail
    test("timeouts") {
      for {
        _ <- ZIO.succeed("Baeldung").forever
      } yield assertTrue(true)
    } @@ timeout(1.second)*/
  )
}
