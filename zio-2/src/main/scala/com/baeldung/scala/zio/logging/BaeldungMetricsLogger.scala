package com.baeldung.scala.zio.logging

import zio.*
import zio.logging.*
import zio.metrics.connectors.MetricsConfig
import zio.metrics.connectors.prometheus.{
  PrometheusPublisher,
  prometheusLayer,
  publisherLayer
}

import java.io.IOException

object BaeldungMetricsLogger {

  private val prometheusConnector
    : ZLayer[Unit, IOException, PrometheusPublisher] = (ZLayer.succeed(
    MetricsConfig(10.millis)
  ) ++ publisherLayer) >+> prometheusLayer
  val metricsLogger: ZLayer[Unit, IOException, PrometheusPublisher] =
    logMetrics ++ logMetricsWith(
      "custom_log_counter",
      "log_level"
    ) ++ prometheusConnector

}
