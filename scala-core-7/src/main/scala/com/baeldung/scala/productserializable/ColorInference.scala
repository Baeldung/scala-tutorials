package com.baeldung.scala.productserializable

trait Color
object Color {
  case object Red extends Color
  case object Green extends Color
}

trait ColorV2 extends Product with Serializable
object ColorV2 {
  case object Red extends ColorV2
  case object Green extends ColorV2
}

object Inference {
  def isError: Boolean = true
  val consoleColor: Product with Serializable with Color =
    if (isError) Color.Red else Color.Green

  val consoleColorV2: Color = if (isError) Color.Red else Color.Green

  val consoleColorV3 = if (isError) ColorV2.Red else ColorV2.Green

  def showColor(data: Data[Color]) = {
    println("The color is " + data.color)
  }

  val data = Data("This is coloured text", consoleColor)
  // Note: The below line will show compilation error due to type inference issue
  // showColor(data)
}

case class Data[C <: Color](value: String, color: C)
