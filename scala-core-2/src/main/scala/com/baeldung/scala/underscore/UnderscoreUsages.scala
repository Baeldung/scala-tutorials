package com.baeldung.scala.underscore

object UnderscoreUsages {

  def getLength(x: List[List[_]]): Int = x.length

  def itemTransaction(price: Double): String = {
    price match {
      case 130 => "Buy"
      case 150 => "Sell"
      case _ => "Need approval"
    }
  }

  def multiplier(a: Int, b: Int): Int = a * b

  def sum(args: Int*): Int = {
    args.reduce(_ + _)
  }

  def sum(x: Int, y: Int): Int = x + y

  def bar(x: Int, y: Int)(z: String, a: String)(b: Float, c: Float): Int = x

  class Product {
    private var a = 0

    def price = a

    def price_=(i: Int): Unit = {
      require(i > 10)
      a = i
    }
  }

  def list_++(list: List[_]): List[_] = List.concat(list, list)

  trait ObjectContainer[T[_]] { // higher kinded type parameter
    def checkIfEmpty(collection: T[_]): Boolean
  }

  object SeqContainer extends ObjectContainer[Seq] {
    override def checkIfEmpty(collection: Seq[_]): Boolean = !collection.nonEmpty
  }

}
