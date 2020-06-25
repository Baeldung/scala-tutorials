import cats._
import cats.implicits._

implicit val optionApply: Apply[Option] = new Apply[Option] {
  def ap[A, B](f: Option[A => B])(fa: Option[A]): Option[B] =
    fa.flatMap(a => f.map(ff => ff(a)))

  def map[A, B](fa: Option[A])(f: A => B): Option[B] = fa map f
}

implicit val listApply: Apply[List] = new Apply[List] {
  def ap[A, B](f: List[A => B])(fa: List[A]): List[B] =
    fa.flatMap(a => f.map(ff => ff(a)))

  def map[A, B](fa: List[A])(f: A => B): List[B] = fa.map(f)
}

val listOpt = Apply[List] compose Apply[Option]
val times3 = (x: Int) => x * 3

listOpt.ap(List(Some(times3)))(List(Some(1), None, Some(3)))
//List(Some(3), None, Some(9))