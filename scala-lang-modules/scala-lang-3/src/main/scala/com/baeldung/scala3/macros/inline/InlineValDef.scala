package com.baeldung.scala3.macros.inline
object InlineValDef {
  inline val debugLogEnabled = true
  inline def logTime[T](fn: => T): T = {
    if (debugLogEnabled) {
      val startTime = System.currentTimeMillis
      val execRes = fn
      val endTime = System.currentTimeMillis
      println(s"Took ${endTime - startTime} millis")
      execRes
    } else fn
  }

  def myLongRunningMethod = {
    println("This is a long running method")
  }

  @main def inlineValDefMainMethod: Unit = logTime { myLongRunningMethod }
}
