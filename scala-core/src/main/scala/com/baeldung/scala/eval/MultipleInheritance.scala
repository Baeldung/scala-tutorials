package com.baeldung.scala.eval

object MultipleInheritance {

    object AbstractClasses {

        abstract class A

        abstract class B

        //class C extends A with B  // doesn't compile
    }

    object Traits {

        trait A

        trait B

        class C extends A with B // works!
    }

}

