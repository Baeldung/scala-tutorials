case class Coordinates(x: Int, y: Int) {
  def moveX(offset: Int): Coordinates = Coordinates(x + offset, y)
  def moveY(offset: Int): Coordinates = Coordinates(x, y + offset)
}

val point = Coordinates(10, 15)
println(point.x)

val movedPoint = point.moveX(7)
println(movedPoint.x)
