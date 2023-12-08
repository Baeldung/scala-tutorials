package com.baeldung.scala.magnetpattern

import scala.language.implicitConversions

object MagnetPattern extends App {

  /*
  def combineElements(stringList:List[String]):String= stringList.mkString
  def combineElements(charList: List[Char]): String = charList.mkString

  Note: The above two lines of code will produce the below error from compiler
  double definition:
  def combineElements(stringList: List[String]): String at line 5 and
  def combineElements(charList: List[Char]): String at line 6
  have same type after erasure: (stringList: List)String
   */

  sealed trait CombineMagnet {
    type Result
    def apply(): Result
  }

  def combineElements(magnet: CombineMagnet): magnet.Result = magnet()

  implicit def intCombineMagnet(intList: List[Int]): CombineMagnet {type Result = Int} = new CombineMagnet {
    override type Result = Int
    override def apply(): Result = intList.reduce((i, c) => i + c)
  }
  implicit def strCombineMagnet(stringList: List[String]): CombineMagnet {type Result = String} = new CombineMagnet {
    override type Result = String
    override def apply(): Result = stringList.reduce((s, c) => s.concat(c))
  }

  println(combineElements(List(1, 2, 3, 4)))
  println(combineElements(List("a", "b", "c")))
}
