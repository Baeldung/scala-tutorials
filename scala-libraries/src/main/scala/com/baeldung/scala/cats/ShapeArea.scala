object ShapeArea {
  def areaOf[A](a: A)(implicit shape: Area[A]): Double = shape.area(a)
}
