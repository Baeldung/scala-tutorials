package com.baeldung.scala.twoscomplement

import scala.annotation.tailrec

object TwosComplement {
  def convertWithFold(bin: String): String = {
    addOneWithFold(onesComplement(bin))
  }

  def convertUsingRecursion(bin: String): String = {
    addOneWithRec(onesComplement(bin))
  }

  private def onesComplement(bin: String): String = {
    bin.map {
      case '1' => '0'
      case '0' => '1'
    }
  }

  private def addOneWithFold(bin: String): String = {
    bin.reverse
      .foldLeft((true, ""))((added, bit) => {
        val (needsAdding, acc) = added
        if (needsAdding) {
          if (bit == '0') (false, acc + '1') else (true, acc + '0')
        } else {
          (false, acc + bit)
        }
      })
      ._2
      .reverse
  }

  private def addOneWithRec(bin: String): String = {
    @tailrec
    def addOne(needsAdding: Boolean, acc: String)(binString: String): String = {
      if (needsAdding) {
        binString match
          case "" => acc
          case _ => {
            val (bit, tail) = binString.splitAt(1)
            if (bit == "0") addOne(false, acc + '1')(tail.mkString)
            else addOne(true, acc + '0')(tail.mkString)
          }
      } else {
        acc + binString
      }
    }

    addOne(true, "")(bin.reverse).reverse
  }
}
