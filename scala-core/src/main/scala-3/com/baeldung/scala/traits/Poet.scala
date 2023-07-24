package com.baeldung.scala.traits

class Poet(name: String) extends Writer(name) {
  def write(): String = s"$name is composing poetry"
}
