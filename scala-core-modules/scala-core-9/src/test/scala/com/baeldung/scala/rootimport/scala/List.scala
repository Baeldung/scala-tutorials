package com.baeldung.scala.rootimport.scala

class List(val num: Int) {
  override def toString: String = s"MyList[${num}]"
}

object List {
  def apply(num: Int) = new List(num)
}
