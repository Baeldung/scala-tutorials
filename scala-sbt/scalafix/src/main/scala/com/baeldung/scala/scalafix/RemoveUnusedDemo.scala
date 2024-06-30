package com.baeldung.scala.scalafix

import scala.List

object RemoveUnusedDemo:
  val myNumber = 10

  def greeting(name: String): String = {
    val newName = s"$name $myNumber"
    s"Hello, $name!"
  }

/* Rewritten:
object RemoveUnusedDemo:
  val myNumber = 10

  def greeting(name: String): String = {
    s"$name $myNumber"
    s"Hello, $name!"
  }
 */
