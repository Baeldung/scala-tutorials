package com.baeldung.scala.enumerations

// This is Scala 2 based enumeration. It still works in Scala 3 for compatibility.
// However, this might become unsupported in future release. In scala 3, use the keyword `enum`
object Fingers extends Enumeration {
  type Finger = Value

  protected case class FingerDetails(i: Int, name: String, height: Double)
    extends super.Val(i, name) {
    def heightInCms(): Double = height * 2.54
  }

  implicit def valueToFingerDetails(x: Value): FingerDetails =
    x.asInstanceOf[FingerDetails]

  val Thumb = FingerDetails(6, "Thumb Finger", 1)
  val Index = FingerDetails(2, "Pointing Finger", 4)
  val Middle = FingerDetails(3, "The Middle Finger", 4.1)
  val Ring = FingerDetails(4, "Finger With The Ring", 3.2)
  val Little = FingerDetails(5, "Shorty Finger", 0.5)
}
