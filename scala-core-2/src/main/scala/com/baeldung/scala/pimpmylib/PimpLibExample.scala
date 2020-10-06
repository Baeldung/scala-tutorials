package com.baeldung.scala.pimpmylib

object PimpLibExample {
  case class IntroText(text: String) {
    val tokens = text.split(" ")
    def name: String = tokens(3)
    def level: String = tokens(12).toInt match {
      case 0 | 1 | 2 | 3 => "Junior"
      case 4 | 5 | 6     => "MidLevel"
      case _             => "Senior"
    }
    def language: String = tokens(9)
  }

  implicit def stringToIntroText(str: String): IntroText = IntroText(str)
}
