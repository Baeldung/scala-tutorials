package com.baeldung.scala.accessmodifiers

import java.util.UUID.randomUUID

abstract class Figure {
  private[this] val code = randomUUID.toString //accessible in the scope of the object-only
  def printCode: Unit = println(s"$code") //public access

  protected[accessmodifiers] val color: String //accessible in the scope of the package
  protected[this] val lineWidth: Int //accessible for instances of this class and its subclasses instances

  protected val topPoint: Double //accessible in the scope of the class and subclasses
  protected val rightMostPoint: Double

  def area: Double

  /*def compareCodes(that: Figure) = {
    this.code == that.code //that.code is not accessible due to object only access scope
  }*/

  //def compareLineWidth(other: Figure) = this.lineWidth == other.lineWidth //other.lineWidth
  //is not accessible due to object only access scope
}

class Rectangle(widthParam: Int, heightParam: Int, colorParam: String, lineWidthParam: Int) extends Figure {
  val width: Int = widthParam
  val height: Int = heightParam
  val color = colorParam
  val lineWidth = lineWidthParam
  override val topPoint = height
  override val rightMostPoint = width

  override val area = width * height
  private val isSquare = width == height //private access in the class scope

  def info: Unit = println(s"the rectangle is $area square units in area and $color in color")

  //override def compareLineWidth(other: Figure) = this.lineWidth == other.lineWidth //other.lineWidth is not accessible
  //due to object-only access scope

  val innerCircle = new InnerFigure(new Circle(scala.math.min(width, height)/2, "green", 1))
  // innerCircle.codeInner //private field of the nested object is not accessible

  class InnerFigure(fig: Figure) {
    private val codeInner = randomUUID.toString
    val isInsideSquare = isSquare //private field of the outer object is accessible
    def printCodeInner = print(codeInner)
  }

}

class Circle(val radius: Int, override val color: String, override val lineWidth: Int) extends Figure {
  override val topPoint = radius * 2
  override val rightMostPoint = radius * 2
  override val area = scala.math.Pi * radius * radius
}

class Composition(fig1: Figure, fig2: Figure) {
  val color = mixColors(fig1.color, fig2.color)

  private def mixColors(color1: String, color2: String) = if (color1 != color2) s"$color1-$color2" else color1
  //val topPoint = scala.math.max(fig1.topPoint, fig2.topPoint) //symbol topPoint is inaccessible from this place
}
