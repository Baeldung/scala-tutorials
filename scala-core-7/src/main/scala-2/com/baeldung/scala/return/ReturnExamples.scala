package com.baeldung.scala.`return`

object ReturnExamples extends App {

  /** Referential transparency
    */

  private def isPositiveNumber1(number: Int): Boolean = {
    val isTrue = true
    val isFalse = false

    if (number > 0) isTrue else isFalse
  }

  private def isPositiveNumber2(number: Int): Boolean = {
    if (number > 0) true else false
  }

//  /**
//   * Referential transparency BAD
//   * */
//
//  private def isPositiveNumber1(number : Int ) : Boolean = {
//    val isTrue = return true
//    val isFalse = return false
//
//    if(number > 0) isTrue else isFalse
//  }
//
//  private def isPositiveNumber2(number : Int ) : Boolean = {
//    if(number > 0) return true else return false
//  }

  /** Inlining example
    */
  def multiplier(a: Int, b: Int): Int = return a * b

  val randomNumbers = List(1, 2, 3, 4)

  def multiple(numbers: List[Int]): Int = {
    numbers.foldLeft(1)(multiplier)
  }

  println(multiple(randomNumbers)) // 24

  /** Inlining example BAD
    */

//  val randomNumbers = List(1,2,3,4)
//
//
//  def multiple(numbers : List[Int]) : Int = {
//    numbers.foldLeft(1){(a,b) => return a * b}
//  }
//
//  println(multiple(randomNumbers)) // 1

  /** IndexOf with recursion
    */

  def indexOf(string: String, char: Char): Int = {
    def runner(stringList: List[Char], index: Int): Int = {
      stringList match {
        case Nil                 => -1
        case h :: _ if h == char => index + 1
        case _ :: t              => runner(t, index + 1)
      }
    }
    if (string.isEmpty) -1 else runner(string.toList, -1)
  }

}
