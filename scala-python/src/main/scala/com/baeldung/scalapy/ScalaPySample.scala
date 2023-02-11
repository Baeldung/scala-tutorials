package com.baeldung.scalapy

import me.shadaj.scalapy.py
import me.shadaj.scalapy.py.{SeqConverters, local}

object ScalaPySample extends App {
    local {
      val listLengthPython = py.Dynamic.global.len(List(1, 2, 3).toPythonProxy)
      val valueInScala = listLengthPython.as[Int]
      println("Length of python list is: " + valueInScala)

      //dict operation
      val dictPython = py.Dynamic.global.dict(Map("India" -> "New Delhi", "Germany" -> "Berlin"))

      val cap = dictPython.get("India")
      println("Capital of India is: " + cap)
    }
}