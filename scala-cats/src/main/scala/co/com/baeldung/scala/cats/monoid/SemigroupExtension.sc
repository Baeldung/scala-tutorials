import cats.Semigroup

trait Monoid[A] extends Semigroup[A] {
  def empty: A
}