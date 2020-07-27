package com.baeldung.scala.bounds

object implicitconversion {
  abstract class Order[T](val me: T) {
    def less(other: T): Boolean
  }

  implicit val intToOrder: Int => Order[Int] = x => new Order[Int](x) {
    override def less(other: Int): Boolean = me < other
  }

  def maximum[A](a: A, b: A)(implicit toOrder: A => Order[A]): A = {
    if(toOrder(a).less(b)) b else a
  }

  val a = 5
  val b = 9

  println(s"The maximum($a, $b) is ${maximum(a, b)}")
}
