package com.baeldung.scala3.transparent

object NonTransparent {
  trait Marker
  trait Genre
  object Horror extends Genre, Marker
  object Comedy extends Genre, Marker

  val isScary = true
  val genre: Genre & Marker = if (isScary) Horror else Comedy
}

object Transparent {
  transparent trait Marker
  trait Genre
  object Horror extends Genre, Marker
  object Comedy extends Genre, Marker

  val isScary = true
  val genre: Genre = if (isScary) Horror else Comedy
}

object Test extends App {
    val as = "Not an Inline value"
    println(as)

}