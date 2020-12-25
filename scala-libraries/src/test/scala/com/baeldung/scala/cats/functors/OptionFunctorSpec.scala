package com.baeldung.scala.cats.functors

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class OptionFunctorSpec extends AnyFlatSpec with Matchers {
  "OptionFunctor" should "transform option" in {
    val option: Option[Int] = Some(10)
    val transformedOption = Some("10")
    assert(OptionFunctor.transformOption(option) == transformedOption)
  }
}
