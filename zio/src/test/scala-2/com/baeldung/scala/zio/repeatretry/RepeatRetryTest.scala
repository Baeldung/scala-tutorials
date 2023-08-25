package com.baeldung.scala.zio.repeatretry

import zio._
import zio.test._

object RepeatRetryTest extends ZIOSpecDefault {
  override def spec = suite("CreateZIOEffects")(
    test("execute retry and end with default handler") {
      val failingZIO = ZIO.fail(new Exception("Fail!"))
      val retriedZIO: ZIO[Any, Nothing, String] = failingZIO.retryOrElse(
        Schedule.recurs(3),
        (_, _: Any) => ZIO.succeed("Default Value")
      )
      assertZIO(retriedZIO)(Assertion.equalTo("Default Value"))
    },
    test("execute retry and fail with exception") {
      val failingZIO = ZIO.fail("Fail!")
      val retriedZIO = failingZIO.retryN(3)

      for {
        exitValue <- retriedZIO.exit
      } yield assertTrue(exitValue == Exit.fail("Fail!"))
    },
    test("execute repeat and exit successfully") {
      val simpleZio = ZIO.succeed("Hello ZIO!")
      assertZIO(simpleZio.repeatN(3))(Assertion.equalTo("Hello ZIO!"))
    }
  )

}
