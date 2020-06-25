import cats.Applicative

trait Monad[F[_]] extends Applicative[F[_]] {
  def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]

  def tailRecM[A, B](a: A)(f: A => F[Either[A, B]]): F[B]
}