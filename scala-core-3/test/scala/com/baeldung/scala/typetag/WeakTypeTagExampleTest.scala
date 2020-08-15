package scala.com.baeldung.scala.typetag

import com.baeldung.scala.typtag.WeakTypeTagExample.Foo
import org.scalatest.WordSpec

class WeakTypeTagExampleTest extends WordSpec {
  "The compiler should produce implicit WeakTypeTag when calling barType for abstract Bar type" in {
    assert(new Foo {
      override type Bar = Int
    }.barType.toString == "Foo.this.Bar")
  }
}
