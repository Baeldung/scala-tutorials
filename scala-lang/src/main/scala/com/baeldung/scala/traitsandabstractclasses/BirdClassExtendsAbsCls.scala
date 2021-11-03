package com.baeldung.scala.traitsandabstractclasses

import com.baeldung.scala.traitsandabstractclasses.AnimalTrait

class Bird extends AnimalTrait {
  def selfPresentation: Unit = println("Chirp, I'm a bird!")
}
