package com.baeldung.scala.traitabstractclass.params

abstract class AbstractClassParams(param: Int) {
  def sum(x: Int, y: Int): Int = x + y + param
}
