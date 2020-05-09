package mutability

object ImmutableCollections {
  def main(args: Array[String]): Unit = {
    val pets = Seq("Cat", "Dog")
    println(pets)

    val myPets = pets :+ "Hamster"
    val notPets = pets ++ List("Girrafe", "Elephant")
    val yourPets = pets.updated(0, "Mice")
    println(pets)
    println(myPets)
    println(notPets)
    println(yourPets)
  }
}
