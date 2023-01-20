package com.baeldung.scala.sealedkeyword

object SelaedExtendedDifferentFile extends App {

  /*
  case class OptionD() extends MultipleChoice

  Throws exception:
  Error:(5, 32) illegal inheritance from sealed class MultipleChoice
  case class OptionD() extends MultipleChoice
   */

  case class OptionY() extends OptionX

  def selectOption(option: MultipleChoice): String = option match {
    case _: OptionY => "Option-Y Selected"
    case _: OptionX => "Option-X Selected"
  }

  println(selectOption(OptionY()))
  // prints : Option-Y Selected

}
