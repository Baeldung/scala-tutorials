package demo

import cats.implicits._

object ShowImpl extends App {
  val showInt: Show[Int] = Show.apply[Int]
  val showString: Show[String] = Show.apply[String]

  val intAsString: String = showInt.show(123)
  val stringAsString: String = showString.show("abc")

  println(intAsString)
  println(stringAsString)
}
