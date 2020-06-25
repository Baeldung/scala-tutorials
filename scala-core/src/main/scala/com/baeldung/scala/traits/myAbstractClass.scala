package com.baeldung.scala.traits

abstract class myAbstractClass(className: String) {
  def myAbstractMethod():Unit
  def myImplementedMethod():Unit=print("Inside de defined method on my abstract class")
}
