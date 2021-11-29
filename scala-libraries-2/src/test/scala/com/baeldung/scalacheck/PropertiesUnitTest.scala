package com.baeldung.scalacheck

import com.baeldung.scalacheck.model.Simple
import org.scalacheck.Prop.forAll
import org.scalacheck.Properties

object PropertiesUnitTest extends Properties("Simple") {
  property("length") = forAll { (str: String) =>
    Simple(str).length >= 0
  }
}
