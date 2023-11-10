package com.baeldung.scala.traits

class Author(name: String) extends Writer(name) {
  def write(): String = s"$name is writing a book"
}
