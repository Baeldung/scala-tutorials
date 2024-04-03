package com.baeldung.scala.typetag

import com.baeldung.scala.typtag.WeakTypeTagExample.Foo
import org.scalatest.wordspec.AnyWordSpec

class WeakTypeTagExampleUnitTest extends AnyWordSpec {
  "The compiler should produce implicit WeakTypeTag when calling barType for abstract Bar type" in {
    assert(new Foo {
      override type Bar = Int
    }.barType.toString == "Foo.this.Bar")
  }
}
