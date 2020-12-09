import AreaInstances._
import ShapeAreaSyntax._
import org.scalatest._
import org.scalatest.matchers.should.Matchers

class ShapeAreaSyntaxSpec extends FlatSpec with Matchers {
  "ShapeAreaSyntaxSpec" should "check for candidate type class instance for type Rectangle" in {
    val areaOfRectangle = Rectangle(2, 3).areaOf
    val expectedAreaOfRectangle = 6.0
    assert(areaOfRectangle == expectedAreaOfRectangle)
  }

  it should "check for candidate type class instance for type Circle" in {
    val areaOfCircle = Circle(2).areaOf
    val expectedAreaOfCircle = 12.566370614359172
    assert(areaOfCircle == expectedAreaOfCircle)
  }
}
