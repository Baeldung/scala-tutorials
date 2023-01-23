package com.baeldung.scala3.intersectiontypes
import scala.reflect.Selectable.reflectiveSelectable

object DuckTyping {

  class Scissors() {
    def cut(): Unit = { println("Cutting with Scissors") }
    val canCutPaper: Boolean = true
  }

  class Knife() {
    def cut(): Unit = { println("Cutting with Knife") }
    val canCutPaper: Boolean = true
  }

  class Chainsaw() {
    def cut(): Unit = { println("Cutting with Chainsaw") }
  }

  type ToolToCutPaper = {
    val canCutPaper: Boolean
    def cut(): Unit
  }

  def cutPaper(pc: ToolToCutPaper) =
    pc.cut()

}
