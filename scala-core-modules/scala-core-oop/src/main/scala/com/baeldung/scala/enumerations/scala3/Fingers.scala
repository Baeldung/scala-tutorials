package com.baeldung.scala.enumerations.scala3

enum Fingers(val height: Double) {
  case Thumb extends Fingers(1)
  case Index extends Fingers(4)
  case Middle extends Fingers(4.1)
  case Ring extends Fingers(3.2)
  case Little extends Fingers(0.5)
  def heightInCms(): Double = height * 2.54
}
