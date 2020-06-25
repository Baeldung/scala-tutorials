sealed abstract class Validated[+E, +A]

object Validated {

  final case class Valid[+A](a: A) extends Validated[Nothing, A]

  final case class Invalid[+E](e: E) extends Validated[E, Nothing]

}