package com.baeldung.scala.abstractclassvstrait

// Usage of abstract class and trait
object AbstractClassVsTrait extends App {

    trait ValueTrait {
        var value: Int
    }

    trait AdditionTrait extends ValueTrait {
        def add(n: Int)
        def addOne() = value = value + 1
    }

    trait SubtractionTrait {
        def subtract(n: Int)
    }

    abstract class ValueHolder(var value: Int) extends ValueTrait {
        // printValue is not implemented here and must be implemented by the inheriting class
        def printValue()
        def setValue(v: Int): Unit = {
            value = v
        }

        def getValue(): Int = value
    }

    class Maths(v: Int) extends ValueHolder(v) with AdditionTrait with SubtractionTrait {
        override def subtract(n: Int) = {
            setValue(value - n)
        }

        override def add(n: Int) = {
            setValue(value + n)
        }

        override def printValue(): Unit = {
            println(s"Value is ${getValue}")
        }
    }

    val m: Maths = new Maths(5)
    m.addOne()
    m.printValue() // Displays 6
    m.add(10)
    m.printValue() // Displays 16
    m.subtract(5)
    m.printValue() // Displays 11
}
