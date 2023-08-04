import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TestIfCompiles extends AnyFlatSpec with Matchers {

  "val x: Int = 258" shouldNot compile
  "val x: Int = 2.0" shouldNot typeCheck
  "val x: Int = 2" should compile

}

