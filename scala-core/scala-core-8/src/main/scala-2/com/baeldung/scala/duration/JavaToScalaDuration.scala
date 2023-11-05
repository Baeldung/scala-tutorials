package com.baeldung.scala.duration

import com.typesafe.config.ConfigFactory

import java.util.concurrent.TimeUnit
import scala.concurrent.duration.Duration

object JavaToScalaDuration {

  def asFiniteDuration(javaDuration: java.time.Duration) =
    scala.concurrent.duration.Duration.fromNanos(javaDuration.toNanos)

  def asFiniteDurationFromConf() = {
    Duration.fromNanos(
      ConfigFactory
        .load()
        .getDuration("timeout.value", TimeUnit.NANOSECONDS)
    )
  }
}
