package com.baeldung.scala

object TraitAbstractClassComparison {

  abstract class AbstClass(exampleParam: String) {

    def abstMethod: Unit

    def showParam: Unit = {
      println(exampleParam)
    }

  }

  trait B {

    def doSomething: Unit

  }

  trait A {

    def doSomething: Unit

  }

  trait Trait extends A with B {

    def doSomething: Unit

  }

  object Singleton extends Trait {

    def doSomething: Unit = {
      println("Do something!")
    }

  }

}
