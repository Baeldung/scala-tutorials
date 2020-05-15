// example 1 - stackable traits

trait CanWalk {
    def walk()
}

trait CanTalk {
    def talk()
}

trait CanEat {
    def eat()
}

case class FastingHuman() extends CanWalk with CanTalk {
    override def walk(): Unit = ???

    override def talk(): Unit = ???
}

// example 2 - abstract classes are not stackable
// note that the code does not compile, so it is commented out

abstract class Walker {
    def walk()
}

abstract class Talker {
    def talk()
}

/* this code does not compile
class Human() extends Walker with Talker {
    override def walk(): Unit = ???

    override def talk(): Unit = ???
}
*/

// example 3 - composable function parameters

def eatingAndWalking(person: CanWalk with CanEat): Unit = ???

// this code does not compile
// eatingAndWalking(FastingHuman())

val eatingHuman = new CanWalk with CanEat {
    override def walk(): Unit = ???

    override def eat(): Unit = ???
}

eatingAndWalking(eatingHuman)
