package com.baeldung.scala.zio.logging

import zio.{Chunk, LogLevel, ZIO}
import zio.test.Assertion.*
import zio.test.*
import zio.*

object BaeldungZIOLoggingUnitTest extends ZIOSpecDefault {

  override def spec: Spec[TestEnvironment, Any] =
    suite("BaeldungZIOLoggingSpec")(
      test("log trace level") {
        for {
          - <- ZIO.logTrace("This is a trace message")
          _ <- ZIO.logDebug("This is a debug message")
          _ <- ZIO.logInfo("This is an info message")
          _ <- ZIO.logWarning("This is a warning message")
          _ <- ZIO.logError("This is an error message")
          loggerOutput <- ZTestLogger.logOutput
        } yield assertTrue(loggerOutput.size == 5) &&
          assert(loggerOutput.map(_.logLevel))(
            equalTo(
              Chunk(
                LogLevel.Trace,
                LogLevel.Debug,
                LogLevel.Info,
                LogLevel.Warning,
                LogLevel.Error
              )
            )
          ) &&
          assert(loggerOutput.map(_.message()))(
            equalTo(
              Chunk(
                "This is a trace message",
                "This is a debug message",
                "This is an info message",
                "This is a warning message",
                "This is an error message"
              )
            )
          )
      }
    ).provideLayer(Runtime.removeDefaultLoggers >>> ZTestLogger.default)
}
