val querySomeServiceThatThrows: Int => String = ???

val anotherQueryThatThrows: String => String = ???

val magicOperation = querySomeServiceThatThrows.andThen(anotherQueryThatThrows)

val right: Either[String, Int] = Right(1)
// Either[String, Int] = Right(1)