val eitherRight: Either[String, Int] = Right(3)
// Either[String, Int] = Right(3)

eitherRight.map(_ * 4)
// Either[String, Int] = Right(12)

val eitherLeft: Either[String, Int] = Left("BOOM error!")
// Either[String, Int] = Left("BOOM error!")

eitherLeft.map(_ * 3)
// Either[String, Int] = Left(BOOM error!)