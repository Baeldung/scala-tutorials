package com.baeldung.scala.forcomprehension

object ForComprehension {

  val results = List(TestResult("test 1", 10, 10), TestResult("test 2", 2, 6))
  val executionTimeList = List(("test 1", 100), ("test 2", 230))
  val listOfPassedAssertsInSucceededTests: List[Int] =
    for {
      result <- results
      if result.succeeded
    } yield result.successfulAsserts
  val passedAssertsInSucceededTests: Int = listOfPassedAssertsInSucceededTests.sum

  val numberOfAssertsWithExecutionTime: List[(String, Int, Int)] =
    for {
      result <- results
      (id, time) <- executionTimeList
      if result.id == id
    } yield (id, result.totalAsserts, time)

  val hugeNumberOfAssertsForATest: Int = 10
  val resultsWithAHugeAmountOfAsserts: List[TestResult] =
    for {
      result <- results
      if result.totalAsserts >= hugeNumberOfAssertsForATest
    } yield result

  case class TestResult(id: String, successfulAsserts: Int, totalAsserts: Int) {
    def succeeded: Boolean = successfulAsserts == totalAsserts
  }

  val result: Result[Int] = Result(42)
  for {
    res <- result
  } println(s"The result is $res")

  val magic: Int = 42
  for {
    res <- result
  } yield res * magic

  val anotherResult: Result[Int] = Result(10)
  result
    .flatMap(res =>
      anotherResult
        .map(another => res + another)
    )

  for {
    res <- result
    if res == 10
  } yield res

  case class Result[A](result: A) {
    def foreach(f: A => Unit): Unit = f(result)
    def map[B](f: A => B): Result[B] = Result(f(result))
    def flatMap[B](f: A => Result[B]): Result[B] = f(result)
    def withFilter(f: A => Boolean): Result[_] = if (f(result)) this else EmptyResult
  }

  object EmptyResult extends Result[Null](null)
}
