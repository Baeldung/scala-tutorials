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
    case optionY: OptionY => "Option-Y Selected"
    case optionX: OptionX => "Option-X Selected"
  }

  println(selectOption(OptionY()))
  // prints : Option-Y Selected

}
