package com.baeldung.scala.functor

import cats.Functor
import cats.instances.option._
import org.scalatest.flatspec.AnyFlatSpec

class FunctorUnitTest extends AnyFlatSpec {
  "Option map" should "apply the function in an imperative way" in {
    var patientIdOpt = Some("patient 0")
    if (patientIdOpt.isDefined) {
      val patientId = patientIdOpt.get
      patientIdOpt = Some(s"$patientId is cured")
    }

    assert(patientIdOpt.contains("patient 0 is cured"))
  }

  "Option map" should "apply the function in an idiomatic way" in {
    val temperatures = List(38, 39, 39, 37)
    val panadol: Int => Int = temperature => temperature - 10
    val lowTemperatures = temperatures.map(panadol)

    assert(lowTemperatures == List(28, 29, 29, 27))
  }

  "Option Functor map" should "apply the function to the internal value" in {
    val option = Some("Hello!")
    val result = Functor[Option].map(option)(_.toUpperCase)

    assert(result.contains("HELLO!"))
  }
}
