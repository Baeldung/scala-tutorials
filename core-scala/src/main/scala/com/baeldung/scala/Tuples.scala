package com.baeldung.scala

object Tuples {

  def partition[A](xs: List[A])(predicate: A => Boolean): (List[A], List[A]) = {
    xs.foldRight((List.empty[A], List.empty[A])) {
      case (a, (lefts, rights)) =>
        if (predicate(a)) (a :: lefts, rights) else (lefts, a :: rights)
    }
  }

  val (evens, odds) = partition(List(1, 3, 4, 5, 2))(_ % 2 == 0)
  //  evens: List[Int] = List(4, 2)
  //  odds: List[Int] = List(1, 3, 5)

  val tuple: (String, Int) = ("Joe", 34)
  //  tuple: (String, Int) = (Joe,34)
  val stillTuple = "Joe" -> 34
  //  stillTuple: (String, Int) = (Joe,34)
  val tuple3: (String, Int, Boolean) = ("Joe", 34, true)
  //  tuple3: (String, Int, Boolean) = (Joe,34,true)
  val tuple4: (String, Int, Boolean, Char) = ("Joe", 34, true, 'A')
  //  tuple4: (String, Int, Boolean, Char) = (Joe,34,true,A)

  val name = tuple._1
  //  name: String = Joe
  val age = tuple._2
  //  age: Int = 34

  val (userName, userAge) = tuple
  //  userName: String = Joe
  //  userAge: Int = 34
  val (_, myAge) = tuple
  //  myAge: Int = 34

  tuple.productArity
  // 2
  tuple.productElement(1)
  // 34
  tuple.productPrefix
  // Tuple2
  tuple.productIterator.foreach(println)
  //  Joe
  //  34
  (1, 2).swap
  // (2, 1)

  val args = (5, 10)
  val sum: (Int, Int) => Int = (x, y) => x + y
  val tupledSum: ((Int, Int)) => Int = sum.tupled
  tupledSum(args)
  //  15
}
