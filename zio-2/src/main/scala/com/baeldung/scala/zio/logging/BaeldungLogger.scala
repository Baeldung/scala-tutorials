package com.baeldung.scala.zio.logging

import zio.*
import zio.logging.backend.SLF4J
import zio.logging.{
  LogColor,
  LogFilter,
  LogFormat,
  LoggerNameExtractor,
  console,
  fileJson
}
import zio.logging.LogFormat.*

import java.nio.file.Path
import java.time.format.DateTimeFormatter

object BaeldungLogger {

  private val filter =
    LogFilter.logLevelByName(
      LogLevel.Trace,
      ("com.baeldung.scala.zio.logging", LogLevel.Info)
    )

  private val logFormat: LogFormat = timestamp(
    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssAZ")
  ).highlight(_ => LogColor.BLUE)
    |-| bracketStart |-| LogFormat.loggerName(
      LoggerNameExtractor.trace
    ) |-| text(":") |-| traceLine |-| bracketEnd |-|
    fiberId |-| level.highlight |-| label("message", quoted(line)).highlight

  val consoleLogger: ULayer[Unit] =
    Runtime.removeDefaultLoggers >>> console(logFormat, filter)

  val fileLogger: ULayer[Unit] = Runtime.removeDefaultLoggers >>> fileJson(
    Path.of("baeldung-zio-logging.log"),
    logFormat,
    LogLevel.Debug
  )

  val slf4jLogger: ULayer[Unit] = Runtime.removeDefaultLoggers >>> SLF4J.slf4j

//  val slf4jBridgeLogger: ULayer[Unit] = Runtime.removeDefaultLoggers >>> Slf4jBridge.initialize

  val combinedLogger: ULayer[Unit] = consoleLogger ++ fileLogger

}
