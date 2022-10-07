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
    val consoleColor: Product with Serializable with Color = if(isError) Color.Red else Color.Green

    val consoleColorV2: Color = if(isError) Color.Red else Color.Green

    val consoleColorV3 = if(isError) ColorV2.Red else ColorV2.Green
}