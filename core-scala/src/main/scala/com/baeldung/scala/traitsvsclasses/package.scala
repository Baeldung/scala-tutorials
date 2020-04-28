package com.baeldung.scala

/**
  * Examples for the article "The Practical Difference between Abstract Classes and Traits in Scala"
  */

package object traitsvsclasses {

  trait FirstTrait
  trait SecondTrait
  abstract class FirstClass
  abstract class SecondClass

  // A valid class definition - two traits are mixed in
  class FirstImpl extends FirstTrait with SecondTrait
  // A valid class definition - a class mixed in with a trait
  class SecondImpl extends FirstTrait with SecondTrait
  // A valid class definition - a class mixed in with two traits
  class ThirdImpl extends FirstClass with FirstTrait with SecondTrait

  // Invalid class definition - we may not extend two classes at the same time
  //class FourthImpl extends FirstClass with SecondClass

  trait HasTitle {
    def title: String
  }

  class HasConstantTitle(override val title: String) extends HasTitle

  abstract class HasTitleEncloser(inner: HasTitle) extends HasTitle {
    protected def prefix: String
    protected def suffix: String
    final override def title: String = prefix + inner.title + suffix
  }


  sealed trait CardSuit
  case object Hearts extends CardSuit
  case object Clubs extends CardSuit
  case object Diamonds extends CardSuit
  case object Spades extends CardSuit

  sealed abstract class GameCharacter(val strength: Int, val intellect: Int, val stamina: Int)
  case object Barbarian extends GameCharacter(strength = 10, intellect = 1, stamina = 7)
  case object Sorcerer extends GameCharacter(strength = 4, intellect = 10, stamina = 4)
  case object Hunter extends GameCharacter(strength = 7, intellect = 5, stamina = 6)
}
