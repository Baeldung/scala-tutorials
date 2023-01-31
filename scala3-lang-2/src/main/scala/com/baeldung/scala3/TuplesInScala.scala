package com.baeldung.tuples
object TupleTest {
  @main
  def tupleMain = {

    val tuple = (1, "Baeldung", false)
    assert(tuple(0) == 1)
    assert(tuple.head == 1)
    assert(tuple.tail == ("Baeldung", false))
    assert(tuple.last == false)
    assert(tuple.take(2) == (1, "Baeldung"))
    assert(tuple.toList == List(1, "Baeldung", false))
    assert(
      tuple ++ ("Hello", "World") == (1, "Baeldung", false, "Hello", "World")
    )

    val baeldung = User(1, "baeldung", true)
    val userTuple = Tuple.fromProductTyped(baeldung)
    assert(userTuple == (1, "baeldung", true))
  }
}

final case class User(id: Long, name: String, active: Boolean)
