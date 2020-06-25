import cats._
import cats.implicits._

Applicative[Option].pure(1)
// Option[Int] = Some(1)

(Applicative[List] compose Applicative[Option]).pure(1)
// List[Option[Int]] = List(Some(1))