package com.baeldung.scala.conditional

object ScalaConditionalExpressions {

  /**
   * Max return the maximum of two integers.
   *
   * Example of if expression. See how if expression returns a value.
   */
  def max(a: Int, b: Int): Int = {
    if (a > b) {
      a
    } else {
      b
    }
  }

  /**
   * Get maximum of three integers.
   *
   * Example of nested if/else blocks
   */
  def max(a: Int, b: Int, c: Int): Int = {
    if (a > b) {
      if (a > c) {
        a
      } else {
        c
      }
    } else {
      if (b > c) {
        b
      } else {
        c
      }
    }
  }
}
