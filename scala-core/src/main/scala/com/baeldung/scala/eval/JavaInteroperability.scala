package com.baeldung.scala.eval

import com.baeldung.scala.eval.JavaInteroperability.AbstractClasses.Toyota
import com.baeldung.scala.eval.JavaInteroperability.Traits.F16

object JavaInteroperability extends App {

    object AbstractClasses {

        // Interoperable with Java
        abstract class Car(name: String) {
            def drive = s"I am driving a $name"
        }

        class Toyota extends Car("Toyota")

    }

    object Traits {

        // Not interoperable with Java
        trait Bike {
            def drive(name: String) = s"I am driving a $name"
        }

        class F16 extends Bike

    }

    val toyota = new Toyota
    val f16 = new F16
    assert(toyota.drive equals "I am driving a Toyota")
    assert(f16.drive("F16") equals "I am driving a F16")
}
