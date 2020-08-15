package scala.com.baeldung.scala.typetag

import com.baeldung.scala.typtag.ClassTagExample.makeArrayFrom
import org.scalatest.WordSpec

class ClassTagExampleTest extends WordSpec {
  "makeArrayFrom should create array of integer from arbitrary integer numbers" in {
    assert(makeArrayFrom(1, 2, 3) sameElements Array(1, 2, 3))
  }

  "makeArrayFrom should create array of strings from arbitrary string values" in {
    assert(makeArrayFrom("a", "b", "c") sameElements Array("a", "b", "c"))
  }
}
