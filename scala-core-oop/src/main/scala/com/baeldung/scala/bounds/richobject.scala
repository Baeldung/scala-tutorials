package com.baeldung.scala.bounds

object richobject {

  abstract class Order[T] {
    def less(me: T, other: T): Boolean

    implicit class RichInterface(val me: T) {
      def <(other: T): Boolean = less(me, other)
    }

  }

  implicit val intOrder: Order[Int] = new Order[Int] {
    override def less(me: Int, other: Int): Boolean = me < other
  }

  def maximum[A: Order](a: A, b: A): A = {
    val ord = implicitly[Order[A]]
    import ord._
    if (a < b) b else a
  }

  val a = 5
  val b = 9

  println(s"The maximum($a, $b) is ${maximum(a, b)}")
}
