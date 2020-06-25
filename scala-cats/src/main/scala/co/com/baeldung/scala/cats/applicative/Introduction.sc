import cats.Apply

trait Applicative[F[_]] extends Apply[F[_]] {
  def pure[A](x: A): F[A]
}