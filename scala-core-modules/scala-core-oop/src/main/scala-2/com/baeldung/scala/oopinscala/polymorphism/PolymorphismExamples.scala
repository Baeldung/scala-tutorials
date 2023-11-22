package com.baeldung.scala.oopinscala.polymorphism

object PolymorphismExamples {
  case class Complex(re: Double, im: Double) {
    def +(op: Complex): Complex = Complex(re + op.re, im + op.im)
    def -(op: Complex): Complex = Complex(re - op.re, im - op.im)
    override def toString: String = s"$re + ${im}i"
  }
  // subtype
  trait Shape {
    def getArea: Double
  }
  case class Square(side: Double) extends Shape {
    override def getArea: Double = side * side
  }
  case class Circle(radius: Double) extends Shape {
    override def getArea: Double = Math.PI * radius * radius
  }
  def printArea[T <: Shape](shape: T): Double = {
    BigDecimal(shape.getArea)
      .setScale(2, BigDecimal.RoundingMode.HALF_UP)
      .toDouble
  }

  // parametric
  def pairWiseReverseInt(xs: List[Int]): List[Int] =
    xs.grouped(2).flatMap(_.reverse).toList
  def pairWiseReverseString(xs: List[String]): List[String] =
    xs.grouped(2).flatMap(_.reverse).toList
  def pairWiseReverseDouble(xs: List[Double]): List[Double] =
    xs.grouped(2).flatMap(_.reverse).toList

  def pairWiseReverse[A](xs: List[A]): List[A] =
    xs.grouped(2).flatMap(_.reverse).toList

  // ad-hoc
  case class StudentId(id: Int)

  object StudentId {
    implicit val ord: Ordering[StudentId] = (x, y) => x.id.compareTo(y.id)
  }
}
