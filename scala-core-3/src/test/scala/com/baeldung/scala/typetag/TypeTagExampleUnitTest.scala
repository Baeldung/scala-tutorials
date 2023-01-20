package scala.com.baeldung.scala.typetag
import com.baeldung.scala.typtag.TypeTagExample.checkType
import org.scalatest.wordspec.AnyWordSpec

class TypeTagExampleUnitTest extends AnyWordSpec {
  val intList: List[Int] = List(1, 2, 3)
  val strList: List[String] = List("foo", "bar")

  "checkType should determine the type of intList" in {
    assert(checkType(intList) == "List of Ints")
  }

  "checkType should determine the type of strList" in {
    assert(checkType(strList) == "List of Strings")
  }
}
