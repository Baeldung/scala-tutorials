package com.baeldung.scala.traits

trait Writer(val name: String) {
  def introduce = s"Hello, I'm $name"

  def write(): String
}
