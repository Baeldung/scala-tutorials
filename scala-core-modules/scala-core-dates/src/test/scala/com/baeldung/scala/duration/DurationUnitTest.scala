package com.baeldung.scala.duration

import com.baeldung.scala.duration.JavaToScalaDuration.{
  asFiniteDuration,
  asFiniteDurationFromConf
}
import org.scalatest.funsuite.AnyFunSuite

import java.time.Duration
import java.time.temporal.ChronoUnit
import java.util.concurrent.TimeUnit
import scala.concurrent.duration.{FiniteDuration, HOURS}
import scala.jdk.DurationConverters.JavaDurationOps

class DurationUnitTest extends AnyFunSuite {

  test("conversion from javaDuration using constructor") {
    val javaDuration = Duration.of(1, ChronoUnit.HOURS)
    val expected = scala.concurrent.duration.Duration(1, HOURS)
    val actual = FiniteDuration.apply(javaDuration.toHours, TimeUnit.HOURS)
    assert(expected == actual)
  }

  test("conversion from javaDuration using fromNanos") {
    val javaDuration = Duration.of(1, ChronoUnit.HOURS)
    val actual = asFiniteDuration(javaDuration)
    val expected = scala.concurrent.duration.Duration(1, HOURS)
    assert(expected == actual)
  }

  test("conversion from javaDuration using DurationConverter") {
    val javaDuration = Duration.of(1, ChronoUnit.HOURS)
    val actual = javaDuration.toScala
    val expected = scala.concurrent.duration.Duration(1, HOURS)
    assert(expected == actual)
  }

  test("reading duration from config file using typesafe") {
    val actual = asFiniteDurationFromConf()
    val expected = scala.concurrent.duration.Duration(1, TimeUnit.HOURS)
    assert(expected == actual)
  }
}
