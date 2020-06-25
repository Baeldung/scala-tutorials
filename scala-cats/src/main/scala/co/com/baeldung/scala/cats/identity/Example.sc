import cats.Functor
import cats.Id

val one: Int = 1
Functor[Id].map(one)(_ + 1)
// cats.Id[Int] = 2