package com.baeldung.scala3.macros.inline
object InlineDef {
  inline def answer(name: String): Unit = println(s"Elementary, my dear $name")
  
  @main def defMain() = answer("Watson")
}
