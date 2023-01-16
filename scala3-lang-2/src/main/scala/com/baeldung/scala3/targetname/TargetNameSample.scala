package com.baeldung.scala3.targetname
import scala.annotation.targetName

class Operator {
    @targetName("star")
    def * = "Scala Star Method"
}

class TypeErasure {
    def sum(nums: List[Int]): Int = nums.sum
    @targetName("totalLen") 
    def sum(str: List[String]): Int = str.map(_.size).sum
}

trait Super {
    def myMethod(): String = "Super"
}

class Child extends Super {

    //This doesn't compile due to conflict.
    //@targetName("myMethod")
    //def targetMethod(): String = "From Child class"
}


class Calculator {
    @targetName("plus")
    def +(a: Int, b:Int): Int = a + b

    def plus(a:String, b:String): String = a + b

    @targetName("weirdType")
    def `type` = println("This is a weird type!") 

    lazy val calcType: String = "Simple"

    @targetName("InnerCalculator")
    object InnerCalc {
        def print() = println("from inner class")
    }

    def f(x: => String): Int = x.length
    @targetName("fstr")
    def f(x: => Int): Int = x + 1

    def sum(ints: List[Int]):Int = ints.sum
    @targetName("sumStr")
    def sum(strs: List[String]):Int = strs.map(_.length()).sum
}
