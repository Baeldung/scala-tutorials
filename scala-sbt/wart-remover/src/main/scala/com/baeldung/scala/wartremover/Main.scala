package com.baeldung.scala.wartremover
object Main extends App {
  val test = null

  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  val suppressed = null

  val baeldungViolation = "Baeldung"

  @SuppressWarnings(Array("com.baeldung.scala.customwarts.BaeldungWart"))
  val baeldungSuppressed = "Baeldung"
}
