package com.baeldung.scala.scaladoc

/** Factory for [[com.baeldung.scala.scaladoc.TasmanianDevil]] instances.
  *
  * Extends [[com.baeldung.scala.scaladoc.Carnivore]].
  */
object TasmanianDevil extends Carnivore {
  /** Creates a tasmanian devil with a given length.
    *
    * @param length the length of tasmanian devil in cm.
    */
  def apply(length: Int) = {}
  def movement: String = "Walk"
}
