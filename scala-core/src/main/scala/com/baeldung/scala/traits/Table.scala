package com.baeldung.scala.traits

abstract class Table[A, B](defaultValue: B) {
  def get(key: A): Option[B]
  def set(key: A, value: B)
  def apply(key: A) = get(key) match {
    case Some(value) => value
    case None        => defaultValue
  }
}

/**
  * concrete implementation of the Table class.
  */
class ListTable[A, B](defaultValue: B) extends Table[A, B](defaultValue) {
  private var elems: List[(A, B)] = Nil
  def get(key: A) = elems.find(_._1.==(key)).map(_._2)
  def set(key: A, value: B) = { elems = (key, value) :: elems }
}

/**
  *  trait that prevents concurrent access to the get and set operations of its parent class
  */
trait SynchronizedTable[A, B] extends Table[A, B] {
  abstract override def get(key: A): Option[B] =
    synchronized { super.get(key) }
  abstract override def set(key: A, value: B) =
    synchronized { super.set(key, value) }
}

/**
  * thread safe concrete implementation of the Table class.
  */
class SafeListTable[A, B]
    extends ListTable[String, Int](0)
    with SynchronizedTable[String, Int] {}
