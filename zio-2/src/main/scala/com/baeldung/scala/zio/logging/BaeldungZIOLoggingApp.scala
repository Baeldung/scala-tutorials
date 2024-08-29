package com.baeldung.scala.zio.logging

import zio.*
import zio.metrics.connectors.prometheus.PrometheusPublisher

object BaeldungZIOLoggingApp extends ZIOAppDefault {

  val app: ZIO[PrometheusPublisher, Any, Unit] = for {
    - <- ZIO.logTrace("This is a trace message")
    _ <- ZIO.logDebug("This is a debug message")
    _ <- ZIO.logInfo("This is an info message")
    _ <- ZIO.logWarning("This is a warning message")
    _ <- ZIO.logError("This is an error message")
    _ <- ZIO.sleep(500.millis)
    metricValues <- ZIO.serviceWithZIO[PrometheusPublisher](_.get)
    _ <- Console.printLine(metricValues)
  } yield ()

  override def run: ZIO[ZIOAppArgs & Scope, Any, Any] =
    app.provideLayer(
      BaeldungLogger.slf4jLogger >>> BaeldungMetricsLogger.metricsLogger
    )
}
