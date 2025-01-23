package com.baeldung.scala.contextfunctions

val increment: Int ?=> Int = summon[Int] + 1

val repeatString: String ?=> Int ?=> String = summon[String].repeat(summon[Int])

opaque type WrappedAlso[T] = T

def it[T](using a: WrappedAlso[T]): T = a

extension [T](x: T)
  def also(f: WrappedAlso[T] ?=> Unit): T =
    f(using x)
    x

@main
def main(): Unit = {
  // Implicit parameter provided via the scope
  given Int = 1
  println(s"Result scope: $increment")

  // Implicit parameter provided explicitly to the call
  println(s"Result explicit: ${increment(using 5)}")

  println(s"Result repeatString: ${repeatString(using "Baeldung")(using 3)}")

  val numbers = List(1, 2, 3, 4, 5)
  numbers
    .also(println(s"The list before adding 6: $it"))
    .appended(6)
    .also(println(s"The list after adding 6: $it"))

  numbers
    .also(it appended 7)
    .also(println(s"The list after adding 7: $it"))
}
