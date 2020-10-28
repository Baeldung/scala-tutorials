object ShapeAreaSyntax {
  implicit class ShapeAreaOps[A](a: A) {
    def areaOf(implicit shape: Area[A]): Double = shape.area(a)
  }
}
