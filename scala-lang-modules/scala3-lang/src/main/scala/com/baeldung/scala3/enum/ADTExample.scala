package com.baeldung.scala3.`enum`

object ADTExample {
  enum Option[+T]:
    case Some(x: T)
    case None
}
