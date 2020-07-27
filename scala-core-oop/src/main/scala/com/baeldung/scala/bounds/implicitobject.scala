package com.baeldung.scala.bounds

object implicitobject {
  abstract class Order[T] {
    def less(me: T, other: T): Boolean
  }

  implicit val intOrder: Order[Int] = new Order[Int] {
    override def less(me: Int, other: Int): Boolean = me < other
  }

  def maximum[A](a: A, b: A)(implicit ord: Order[A]): A = {
    if(ord.less(a, b)) b else a
  }

  val a = 5
  val b = 9

  println(s"The maximum($a, $b) is ${maximum(a, b)}")
}
