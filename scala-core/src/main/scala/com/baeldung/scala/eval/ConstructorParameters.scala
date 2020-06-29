package com.baeldung.scala.eval

import com.baeldung.scala.eval.ConstructorParameters.AbstractClasses.Toyota

object ConstructorParameters extends App {

    object AbstractClasses {

        abstract class Car(name: String) {
            override def toString: String = s"I am a $name"
        }

        class Toyota extends Car("Toyota") // works!

    }

    object Traits {
        //    trait Car(name:String) // doesn't compile
    }

    val toyota = new Toyota
    assert(toyota.toString equals "I am a Toyota")

}
