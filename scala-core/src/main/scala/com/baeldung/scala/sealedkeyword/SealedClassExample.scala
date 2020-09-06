package com.baeldung.scala.sealedkeyword

sealed abstract class MultipleChoice

case class OptionA() extends MultipleChoice
case class OptionB() extends MultipleChoice
case class OptionC() extends MultipleChoice

object MultipleChoiceExam extends App {

  def selectOption(option: MultipleChoice): String = option match {
    case optionA: OptionA => "Option-A Selected"
    case optionB: OptionB => "Option-B Selected"
  }
  /*
  Warning:(11, 54) match may not be exhaustive.
  It would fail on the following input: OptionC()
    def selectOption(option: MultipleChoice): String = option match {
   */

  println(selectOption(OptionA()))
  println(selectOption(OptionB()))

}
