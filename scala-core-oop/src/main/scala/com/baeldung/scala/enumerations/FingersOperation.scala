package com.baeldung.scala.enumerations

import com.baeldung.scala.enumerations.Fingers._

class FingersOperation {

  def isShortest(finger: Finger) =
    Fingers.values.toList.sortBy(_.height).head == finger

  def twoLongest() =
    Fingers.values.toList.sortBy(_.heightInCms()).takeRight(2)
}
