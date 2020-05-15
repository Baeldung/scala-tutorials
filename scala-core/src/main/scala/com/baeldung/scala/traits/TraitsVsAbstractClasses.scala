

// Example 2.1
trait FourLeggedAnimal {

  def speak: Unit
}

trait DomesticatedAnimal {

  val name: String
}

class Dog(_name: String) extends FourLeggedAnimal with DomesticatedAnimal  {

  def speak: Unit = println("Woof")

  val name = _name
}

// Example 2.2
trait RescueAnimal{

  val isHero = true
}

val newDog = new Dog("Boots") with RescueAnimal


// Example 2.3
abstract class DomesticatedAnimalRefactor(_name: String) {

  val name: String = _name
}

class DogRefactor(name: String) extends DomesticatedAnimalRefactor(name) with FourLeggedAnimal {

  def speak: Unit = println("Woof")
}

// Example 2.4
trait Math {

  def sum(a: Int, b: Int): Int = a + b
}

abstract class Math2 {

  def sum(a: Int, b: Int): Int = a + b
}

