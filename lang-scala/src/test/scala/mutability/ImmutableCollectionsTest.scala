package mutability

import org.scalatest.FunSuite

class ImmutableCollectionsTest extends FunSuite {
  test("Immutable collections will create new instance if we add or update the elements") {
    val pets = Seq("Cat", "Dog")
    val myPets = pets :+ "Hamster"
    val notPets = pets ++ List("Giraffe", "Elephant")
    val yourPets = pets.updated(0, "Mice")

    assert(pets == Seq("Cat", "Dog"))
    assert(myPets == Seq("Cat", "Dog", "Hamster"))
    assert(notPets == Seq("Cat", "Dog", "Giraffe", "Elephant"))
    assert(yourPets == Seq("Mice", "Dog"))
  }
}
