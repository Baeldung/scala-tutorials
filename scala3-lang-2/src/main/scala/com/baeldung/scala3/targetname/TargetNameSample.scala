package com.baeldung.scala3.targetname
import scala.annotation.targetName

class Calculator {
    @targetName("plus")
    def +(a: Int, b:Int): Int = a + b

    def plus(a:String, b:String): String = a + b

    @targetName("weirdType")
    def `type` = println("This is a weird type!") 

    //@targetName("calculatorType")
    lazy val calcType: String = "Simple"

    @targetName("InnerCalculator")
    object InnerCalc {
        def print() = println("from inner class")
    }

    def f(x: => String): Int = x.length
    @targetName("fstr")
    def f(x: => Int): Int = x + 1
}

object Usage extends App {
    val calc = new Calculator
    val sum = calc +(10,20)
    calc.`type`
    println(calc.f(100))
    println(calc.f("100"))
}