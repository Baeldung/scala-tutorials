package com.baeldung.scala.matchtypes

type FirstComponentOf[T] = T match
  case String      => Option[Char]
  case Int         => Int
  case Iterable[t] => Option[t]
  case Any         => T

def firstComponentOf[U](elem: U): FirstComponentOf[U] = elem match
  case s: String => if (s.nonEmpty) Some(s.charAt(0)) else Option.empty[Char]
  case i: Int    => i.abs.toString.charAt(0).asDigit
  case it: Iterable[_] => it.headOption
  case a: Any          => a

val aNumber: FirstComponentOf[Int] = 2
val aChar: FirstComponentOf[String] = Some('b')
val anItem: FirstComponentOf[Seq[Float]] = Some(3.2f)

type Node[T] = T match
  case Iterable[t] => Node[t]
  case Array[t]    => Node[t]
  case AnyVal      => T