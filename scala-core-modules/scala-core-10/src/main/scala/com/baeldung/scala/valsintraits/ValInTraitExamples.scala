package com.baeldung.scala.valsintraits

import java.time.LocalDateTime

object ValInTraitExamples {
  trait CurrentTimePrinter {
    val currentTime: LocalDateTime
    def printCurrentTime(): Unit
  }
  object TimePrinterImpl extends CurrentTimePrinter {
    override def currentTime: LocalDateTime = LocalDateTime.now()
    override def printCurrentTime(): Unit = println(currentTime)
  }

  trait CurrentTimePrinterWithDef {
    def currentTime: LocalDateTime
    def printCurrentTime(): Unit
  }
  object TimePrinterWithDefImpl extends CurrentTimePrinterWithDef {
    override var currentTime: LocalDateTime = LocalDateTime.now()
    override def printCurrentTime(): Unit = println(currentTime)
  }

  trait TraitA {
    val multiplier: Int
    val result: Int = multiplier * 7
  }
  trait TraitB {
    val multiplier: Int = 10
  }
  trait TraitAWithDef {
    val multiplier: Int
    def result: Int = multiplier * 7
  }

  object AB extends TraitA with TraitB
  object BA extends TraitB with TraitA
  object ABWithDef extends TraitAWithDef with TraitB
}
