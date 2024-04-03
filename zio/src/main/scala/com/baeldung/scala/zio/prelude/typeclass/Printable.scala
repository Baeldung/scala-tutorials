package com.baeldung.scala.zio.prelude.typeclass

/**
 * Type class that instructs implementing types to be able to be formatted as strings.
 *
 * @tparam A type that needs to implement the trait
 */
trait Printable[A] {
  /**
   * Any type A that implements this trait must be able to present itself as a string.
   *
   * @param value instance of type [[A]] that implements the type class
   * @return string representation of the parameter `value`
   */
  def format(value: A): String
}

/**
 * Provides implementations of the [[Printable]] type class for types [[String]] and [[Int]].
 */
object Printable {

  /**
   * Implementation of the [[Printable]] type class for [[String]].
   */
  implicit val stringPrintable: Printable[String] = (value: String) => value

  /**
   * Implementation of the [[Printable]] type class for [[Int]].
   */
  implicit val intPrintable: Printable[Int] = (value: Int) => value.toString
}
