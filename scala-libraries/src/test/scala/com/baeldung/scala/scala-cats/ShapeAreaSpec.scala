import AreaInstances._
import org.scalatest._
import org.scalatest.matchers.should.Matchers

class ShapeAreaSpec extends FlatSpec with Matchers{
  val rectangle: Rectangle = Rectangle(2, 3)
  val circle: Circle = Circle(2)

  "ShapeAreaSpec" should "check for candidate type class instance for type Rectangle" in {
    val areaOfRectangle = ShapeArea.areaOf(rectangle)
    val expectedAreaOfRectangle = 6.0
    assert(areaOfRectangle == expectedAreaOfRectangle)
  }

  it should "check for candidate type class instance for type Circle" in {
    val areaOfCircle = ShapeArea.areaOf(circle)
    val expectedAreaOfCircle = 12.566370614359172
    assert(areaOfCircle == expectedAreaOfCircle)
  }
}
