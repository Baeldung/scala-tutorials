import cats._
import cats.implicits._

Foldable[List].foldLeft(List(1, 2, 3), 0)(_ + _)
// Int = 6
Foldable[List].foldLeft(List("a", "b", "c"), "")(_ + _)
// String = abc