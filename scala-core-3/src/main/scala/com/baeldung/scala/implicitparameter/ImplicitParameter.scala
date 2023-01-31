package com.baeldung.scala.implicitparameter

object ImplicitParameter extends App {
  case class Color(value: String)
  case class DrawingDevice(value: String)

  def write(text: String)(implicit color: Color, by: DrawingDevice) =
    s"""Writing "$text" in ${color.value} color by ${by.value}."""

  def writeByMixColors(
    text: String
  )(implicit color: Color, color2: Color, by: DrawingDevice = null) =
    s"""Writing "$text" in ${color.value} and ${color2.value} colors by ${by.value}."""
}
