package com.baeldung.scala.typtag

import scala.reflect.runtime.universe._

object TypeTagExample {
  def checkType[T: TypeTag](v: T): String = typeOf[T] match {
    case t if t =:= typeOf[List[String]] => "List of Strings"
    case t if t =:= typeOf[List[Int]] => "List of Ints"
  }
}

