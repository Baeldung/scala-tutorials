package com.baeldung.scala.transparent

object NonTransparent {
  trait Marker
  trait Genre {
    def name: String
  }
  object Horror extends Genre, Marker {
    override val name: String = "Horror"
  }
  object Comedy extends Genre, Marker {
    override val name: String = "Comedy"
  }
  val isScary = true
  val genre /*: Genre & Marker*/ = if (isScary) Horror else Comedy
}

object Transparent {
  transparent trait Marker
  trait Genre {
    def name: String
  }
  object Horror extends Genre, Marker {
    override val name: String = "Horror"
  }
  object Comedy extends Genre, Marker {
    override val name: String = "Comedy"
  }
  val isScary = true
  val genre /*: Genre*/ = if (isScary) Horror else Comedy
}
