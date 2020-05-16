package com.baeldung.scala.classvsobject

object MixinExample {

  sealed class Data()

  case class ProcessedData(strings: List[String], integers: List[Int]) extends Data

  trait ToIntHelper {
    def strToInt(s: String): Int = s.toInt
  }

  abstract class Transform {
    def transform(raw_data: String): Data;
  }

  class TransformToStrAndInt extends Transform with ToIntHelper {
    override def transform(raw_data: String): ProcessedData = {
      val words = raw_data.split(" +")
      var strings: List[String] = Nil
      var integers: List[Int] = Nil
      words.foreach(s => {
        try {
          integers = strToInt(s) :: integers
        } catch {
          case _ : Throwable => strings = s :: strings
        }
      })
      ProcessedData(strings, integers)
    }
  }
}
