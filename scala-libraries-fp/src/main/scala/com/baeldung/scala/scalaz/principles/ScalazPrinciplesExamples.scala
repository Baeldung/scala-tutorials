package com.baeldung.scala.scalaz.principles

object ScalazPrinciplesExamples {
  trait Doubler[F[_], A] {
    def makeDouble(xs: F[A]): F[A]
  }
  object Doubler {
    implicit object intListDoubler extends Doubler[List, Int] {
      def makeDouble(xs: List[Int]): List[Int] = xs.map(_ * 2)
    }

    implicit object stringListDoubler extends Doubler[List, String] {
      def makeDouble(xs: List[String]): List[String] = xs.map(s => s `concat` s)
    }

    implicit object intOptionDoubler extends Doubler[Option, Int] {
      def makeDouble(xs: Option[Int]): Option[Int] = xs.map(_ * 2)
    }
  }

  def doubleIt[F[_], A](xs: F[A])(implicit doubler: Doubler[F, A]): F[A] = {
    doubler.makeDouble(xs)
  }

  case class IntWrapper(id: Int)

  implicit val ord: Ordering[IntWrapper] = (x, y) => x.id.compareTo(y.id)
}
