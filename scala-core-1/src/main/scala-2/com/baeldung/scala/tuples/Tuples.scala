package com.baeldung.scala.tuples

object Tuples {

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

  def partition[A](xs: List[A])(predicate: A => Boolean): (List[A], List[A]) = {
    xs.foldRight((List.empty[A], List.empty[A])) { case (a, (lefts, rights)) =>
      if (predicate(a)) (a :: lefts, rights) else (lefts, a :: rights)
    }
  }

  val (evens, odds) = partition(List(1, 3, 4, 5, 2))(_ % 2 == 0)
  //  evens: List[Int] = List(4, 2)
  //  odds: List[Int] = List(1, 3, 5)

  val data = Map(
    "Joe" -> 34,
    "Mike" -> 16,
    "Kelly" -> 21
  )

  case class User(name: String, isAdult: Boolean)

  val createUser: (String, Int) => User = (name, age) => User(name, age >= 18)
  val users = data.map(createUser.tupled)
  //  users: Iterable[User] = List(User(Joe,true), User(Mike,false), User(Kelly,true))

}
