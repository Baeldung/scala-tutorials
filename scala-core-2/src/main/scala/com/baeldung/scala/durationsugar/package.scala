package com.baeldung.scala

import scala.concurrent.duration.{FiniteDuration, MILLISECONDS, MINUTES, SECONDS}

package object durationsugar {

  implicit class DurationSugar(time: Long) {
    def milliseconds: FiniteDuration = new FiniteDuration(time, MILLISECONDS)

    def seconds: FiniteDuration = new FiniteDuration(time, SECONDS)

    def minutes: FiniteDuration = new FiniteDuration(time, MINUTES)
  }

  implicit class DurationOps(duration: FiniteDuration) {
    def ++(other: FiniteDuration): FiniteDuration =
      (duration.unit, other.unit) match {
        case (a, b) if a == b =>
          new FiniteDuration(duration.length + other.length, duration.unit)
        case (MILLISECONDS, _) =>
          new FiniteDuration(duration.length + other.toMillis, MILLISECONDS)
        case (_, MILLISECONDS) =>
          new FiniteDuration(duration.toMillis + other.length, MILLISECONDS)
        case (SECONDS, _) =>
          new FiniteDuration(duration.length + other.toSeconds, SECONDS)
        case (_, SECONDS) =>
          new FiniteDuration(duration.toSeconds + other.length, SECONDS)
      }
  }

}
