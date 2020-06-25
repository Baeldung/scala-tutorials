import cats.Functor
import cats.implicits._

case class Container[A](x: A, y: A)

object FunctorExample extends App {
  implicit val functorForContainer: Functor[Container] = new Functor[Container] {
    def map[A, B](fa: Container[A])(f: A => B): Container[B] =
      Container(f(fa.x), f(fa.y))
  }

  val container = Container(10, 20)
  val result = container.map(value => value * 3)
  // Container(30, 60)
}