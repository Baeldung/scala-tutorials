package com.baeldung.scala.typtag

import scala.reflect.ClassTag

object ClassTagExample {
  def makeArrayFrom[T: ClassTag](elems: T*): Array[T] = Array[T](elems: _*)
}
