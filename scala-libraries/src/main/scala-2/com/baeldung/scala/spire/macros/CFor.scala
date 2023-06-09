package com.baeldung.scala.spire.macros

import spire.implicits.cfor

object CFor extends App {

  cfor(9)(_>=0, _-1) { i =>
    println(i)
  }
}
