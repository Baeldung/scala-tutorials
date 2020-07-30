// Simply function that can return a list of ints or a list of strings
def returnDifferentLists(f: Boolean) = {
  if (f) {
    List(1, 2, 3)
  } else {
    List("a", "b", "c")
  }
}

// Let's test it with false so that it returns a list of strings and see what happens...
returnDifferentLists(false) match {
  case l: List[Int] =>
    println("I'm a list of integers!")
  case l: List[String] =>
    println("I'm a list of strings!")
  case _ =>
    println("Huh?")
}
