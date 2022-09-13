package com.baeldung.scala3.matchtypes

object MatchTypes {
  type FirstComponentOf[T] = T match {
    case String => Option[Char]
    case Int => Int
    case Iterable[t] => Option[t]
  }

  val aNumber: FirstComponentOf[Int] = 2
  val aChar: FirstComponentOf[String] = Some('b')
  val anItem: FirstComponentOf[Seq[Float]] = Some(3.2f)
}
