object ImplicitParameter extends App {
  case class Color(value: String)
  case class DrawingDevice(value: String)

  def write(text: String)(implicit color: Color, by: DrawingDevice) =
    s"""Writing "$text" in ${color.value} color by ${by.value}."""

  def writeByMixColors(
      text: String
  )(implicit color: Color, color2: Color, by: DrawingDevice = null) =
    s"""Writing "$text" in ${color.value} and ${color2.value} colors by ${by.value}."""

  implicit val red: Color = Color("red")
//  implicit val green: Color = Color("green") // Enable this will be resulted in failed compilation.
  implicit val pen: DrawingDevice = DrawingDevice("pen")

  assert(
    write("A good day") ==
      """Writing "A good day" in red color by pen."""
  )
  assert(
    write("Drink a cup of coffee") ==
      """Writing "Drink a cup of coffee" in red color by pen."""
  )
  assert(
    write("Write some code") ==
      """Writing "Write some code" in red color by pen."""
  )

  assert(
    write("A good day")(red, pen) ==
      """Writing "A good day" in red color by pen."""
  )
  assert(
    write("Drink a cup of coffee")(red, pen) ==
      """Writing "Drink a cup of coffee" in red color by pen."""
  )
  assert(
    write("Write some code")(red, pen) ==
      """Writing "Write some code" in red color by pen."""
  )
}
