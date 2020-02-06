package com.baeldung.scala

/**
 * Sample code demonstrating the differences between traits and abstract classes.
 *
 * @author Brian Kelly
 *
 */

object TraitsAndAbstractClasses {
  trait MyTrait {
    // Abstract field
    val name: String
    
    // Concrete method
    def upperCaseName: String =
      name.toUpperCase
  }

  abstract class MyAbstractClass {
    // Abstract field
    val name: String
    
    // Concrete method
    def upperCaseName: String =
      name.toUpperCase
  }

  trait Visible {
    def height: Int
    def width: Int
  }

  trait Highlightable {
    def getHighlightedText(start: Int, end: Int): String
  }

  trait Clickable { 
    def onClick: Unit =
      println(s"${super.toString} has been clicked!")
  }

  class TextArea(text: String) extends Visible with Highlightable {
    def height = text.linesIterator.length
    def width = text.length
    
    def getHighlightedText(start: Int, end: Int) =
      text.substring(start, end)
    
    override def toString = text
  }

  abstract class FixedSizeElement(val height: Int, val width: Int) extends Visible

  class Margin(height: Int, width: Int) extends FixedSizeElement(height, width)

  class Button(height: Int, width: Int) extends FixedSizeElement(height, width) with Clickable
}