package com.baeldung.scala

package object packageimport {
  val year = "2020"

  trait Motor {
    val dieselMessage: String = "I am not environment friendly"
    val noDieselMessage: String = "I am environment friendly"
  }

}
