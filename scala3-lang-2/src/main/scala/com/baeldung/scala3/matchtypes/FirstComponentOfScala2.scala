package com.baeldung.scala3.matchtypes

sealed trait FirstComponentOfScala2[-T] {
  type Result
  def firstComponentOf(elem: T): Result
}

object FirstComponentOfScala2 {
  implicit val firstComponentOfString: FirstComponentOfScala2[String] =
    new FirstComponentOfScala2[String] {
      override type Result = Option[Char]

      override def firstComponentOf(elem: String): Option[Char] =
        if (elem.nonEmpty) Some(elem.charAt(0)) else Option.empty[Char]
    }

  implicit val firstComponentOfInt: FirstComponentOfScala2[Int] =
    new FirstComponentOfScala2[Int] {
      override type Result = Int

      override def firstComponentOf(elem: Int): Int =
        elem.abs.toString.charAt(0).asDigit
    }

  implicit def firstComponentOfIterable[U]
    : FirstComponentOfScala2[Iterable[U]] =
    new FirstComponentOfScala2[Iterable[U]] {
      override type Result = Option[U]

      override def firstComponentOf(elem: Iterable[U]): Option[U] =
        elem.headOption
    }

  implicit val firstComponentOfAny: FirstComponentOfScala2[Any] =
    new FirstComponentOfScala2[Any] {
      override type Result = Any

      override def firstComponentOf(elem: Any): Any = elem
    }

  def firstComponentOf[T](elem: T)(implicit
    T: FirstComponentOfScala2[T]
  ): T.Result = T.firstComponentOf(elem)
}
