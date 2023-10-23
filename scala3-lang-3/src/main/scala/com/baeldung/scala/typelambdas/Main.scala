type MyTry = [X] =>> Either[Throwable, X]

type MyTuple = [X] =>> [Y] =>> (X, Y)

@main def main(): Unit = {
  val myTryInt: MyTry[Int] = Right(10)
  val myTryString: MyTry[String] = Right("Baeldung")
  val myTryLeft: MyTry[Int] = Left(Exception("Boom"))

  println(myTryInt)
  println(myTryString)
  println(myTryLeft)
}
