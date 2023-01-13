package com.baeldung.scala.scalapy.helloworld

import me.shadaj.scalapy.py
import me.shadaj.scalapy.py.SeqConverters

class ScalaPyDemo {
  val listLengthPython = py.Dynamic.global.len(List(1, 2, 3).toPythonProxy)
  println(listLengthPython)
  // listLengthPython: py.Dynamic = 3
}
