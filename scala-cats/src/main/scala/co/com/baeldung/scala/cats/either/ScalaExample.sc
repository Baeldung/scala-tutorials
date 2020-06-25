val eitherRight: Either[String, Int] = Right(1)
// Either[String, Int] = Right(1)

eitherRight.right.map(_ + 42)
// Either[String, Int] = Right(43)

val eitherLeft: Either[String, Int] = Left("BOOM error!")
// Either[String, Int] = Left("BOOM error!")

eitherLeft.left.map(_ + 1)
// Either[String, Int] = Left(BOOM error!)