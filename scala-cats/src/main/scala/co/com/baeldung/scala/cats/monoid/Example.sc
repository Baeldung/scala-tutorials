import cats.Monoid

case class Money(dollars: Int, cents: Int)

implicit val moneyMonoid: Monoid[Money] = new Monoid[Money] {
  override def combine(x: Money, y: Money): Money =
    Money(x.dollars + y.dollars + ((x.cents + y.cents) / 100), (x.cents + y.cents) % 100)

  override def empty: Money = Money(0, 0)
}

def combineAll[A: Monoid](list: List[A]): A =
  list.foldLeft(Monoid[A].empty)(Monoid[A].combine)

combineAll(List(Money(10, 40), Money(4, 95), Money(8, 99)))
// Money(29, 34)
