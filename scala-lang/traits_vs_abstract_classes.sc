// example 1 - stackable traits

trait FirstTrait {
    def functionOne()
}

trait SecondTrait {
    def functionTwo()
}

trait ThirdTrait {
    def functionThree()
}

case class SomeClass() extends FirstTrait with SecondTrait {
    override def functionOne(): Unit = ???
    override def functionTwo(): Unit = ???
}

// example 2 - abstract classes are not stackable
// note that the code does not compile, so it is commented out

abstract class FirstAbstractClass {
    def functionOne()
}

abstract class SecondAbstractClass {
    def functionTwo()
}

/* class SomeOtherClass() extends FirstAbstractClass with SecondAbstractClass {
    override def functionOne(): Unit = ???
    override def functionTwo(): Unit = ???
}
*/

// example 3 - composable function parameters

def functionWithMultiTraitParameter(parameter: FirstTrait with ThirdTrait): Unit = ???

// this code does not compile:
// functionWithMultiTraitParameter(SomeClass())

val firstWithThird = new FirstTrait with ThirdTrait {
    override def functionOne(): Unit = ???
    override def functionThree(): Unit = ???
}

functionWithMultiTraitParameter(firstWithThird)
