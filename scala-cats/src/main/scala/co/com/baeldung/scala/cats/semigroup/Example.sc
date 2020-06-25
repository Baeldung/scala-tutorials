import cats._

case class Money(dollars: Int, cents: Int)

implicit val moneyAdditionSemigroup: Semigroup[Money] = new Semigroup[Money] {
  def combine(x: Money, y: Money): Money = {
    Money(x.dollars + y.dollars + ((x.cents + y.cents) / 100), (x.cents + y.cents) % 100)
  }
}

val x = Money(10, 40)
val y = Money(4, 95)
val z = Money(8, 99)

Semigroup[Money].combine(x, y)
// Money(15, 35)

Semigroup[Money].combine(x, Semigroup[Money].combine(y, z))
// Money(29, 34)

Semigroup[Money].combine(Semigroup[Money].combine(x, y), z)
// Money(29, 34)