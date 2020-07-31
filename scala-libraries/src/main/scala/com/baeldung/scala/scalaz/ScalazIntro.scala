package com.baeldung.scala.scalaz
object ScalazIntro {
  //subtype
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

  //parametric
  def pairWiseReverseInt(xs: List[Int]): List[Int] =
    xs.grouped(2).flatMap(_.reverse).toList
  def pairWiseReverseString(xs: List[String]): List[String] =
    xs.grouped(2).flatMap(_.reverse).toList
  def pairWiseReverseDouble(xs: List[Double]): List[Double] =
    xs.grouped(2).flatMap(_.reverse).toList
  def pairWiseReverse[A](xs: List[A]): List[A] =
    xs.grouped(2).flatMap(_.reverse).toList

  //ad-hoc
  case class StudentId(id: Int)
  case class StaffId(id: Int)
  case class Score(s: Double)
  object StudentId {
    implicit val ord: Ordering[StudentId] = (x, y) => x.id.compareTo(y.id)
  }

  trait Printer[A] {
    def getString(a: A): String
  }

  object Printer {
    implicit val studentPrinter: Printer[StudentId] = new Printer[StudentId] {
      def getString(a: StudentId): String = s"StudentId: ${a.id}"
    }

    implicit val staffPrinter: Printer[StaffId] = new Printer[StaffId] {
      def getString(a: StaffId): String = s"StaffId: ${a.id}"
    }

    implicit val scorePrinter: Printer[Score] = new Printer[Score] {
      def getString(a: Score): String = s"Score: ${a.s}%"
    }
  }
  def show[A](a: A)(implicit printer: Printer[A]): String = printer.getString(a)

  case class Complex(re: Double, im: Double) {
    def +(op: Complex): Complex = Complex(re + op.re, im + op.im)
    def -(op: Complex): Complex = Complex(re - op.re, im - op.im)
    override def toString: String = s"$re + ${im}i"
  }

  case class IntroText(text: String) {
    val tokens = text.split(" ")
    def name: String = tokens(3)
    def level: String = tokens(12).toInt match {
      case 0 | 1 | 2 | 3 => "Junior"
      case 4 | 5 | 6     => "MidLevel"
      case _             => "Senior"
    }
    def language: String = tokens(9)
  }

  implicit def stringToIntroText(str: String): IntroText = IntroText(str)

  trait Doubler[F[_], A] {
    def makeDouble(xs: F[A]): F[A]
  }
  object Doubler {
    implicit object intListDoubler extends Doubler[List, Int] {
      def makeDouble(xs: List[Int]): List[Int] = xs.map(_ * 2)
    }

    implicit object stringListDoubler extends Doubler[List, String] {
      def makeDouble(xs: List[String]): List[String] = xs.map(s => s concat s)
    }

    implicit object intOptionDoubler extends Doubler[Option, Int] {
      def makeDouble(xs: Option[Int]): Option[Int] = xs.map(_ * 2)
    }
  }

  def doubleIt[F[_], A](xs: F[A])(implicit doubler: Doubler[F, A]): F[A] = {
    doubler.makeDouble(xs)
  }
}
