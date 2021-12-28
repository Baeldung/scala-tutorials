package com.baeldung.scala.break

import scala.util.control.Breaks
import scala.util.control.Breaks.{break, breakable, tryBreakable}

object BreakStatement {

  def breakSimple(): Unit = {
    breakable {
      for (i <- Range(0, 10)) yield {
        if (i > 3) {
          break()
        }
        println(s"$i * 2 = ${i * 2}")
      }
    }
  }

  def breakSimpleAlternative(): Unit = {
    val loop = new Breaks
    loop.breakable {
      for (i <- Range(0, 10)) yield {
        if (i > 3) {
          break()
        }
        println(i * 2)
      }
    }
  }

  def dualLoop(): Unit = {
    var i = 0
    breakable {
      while (i < 4) {
        var j = 1
        while (j < 3) {
          if (i == 3) {
            break()
          }
          println(s"i: $i, j: $j")
          j += 1
        }
        i += 1
      }
    }
  }

  def refinedDualLoop(): Unit = {
    var i = 0
    val outerLoop = new Breaks
    outerLoop.breakable {
      while (i < 4) {
        if (i == 3) {
          outerLoop.break()
        }
        var j = 2
        val innerLoop = new Breaks
        innerLoop.breakable {
          while (j < 6) {
            if (j == 5) {
              innerLoop.break()
            }
            println(s"i: $i, j: $j")
            j += 1
          }
        }
        i += 1
      }
    }
  }

  def tryLoop(): Unit = {
    tryBreakable[Unit] {
      for (i <- Range(0, 10)) yield {
        if (i > 3) {
          break()
        }
        println(s"$i * 2 = ${i * 2}")
      }
    } catchBreak {
      println("break called!")
    }
  }

  def main(args: Array[String]): Unit = {
    breakSimple()
    breakSimpleAlternative()
    dualLoop()
    refinedDualLoop()
    tryLoop()
  }
}
