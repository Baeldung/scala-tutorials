package com.baeldung.scala.fixedsizelist

class FixedSizeList[A](val maxSize: Int) extends Iterable[A] {
  private val internalList = scala.collection.mutable.Queue[A]()

  def add(element: A): Unit = {
    internalList.enqueue(element)
    if (internalList.size > maxSize) {
      internalList.dequeue()
    }
  }

  def get(index: Int): Option[A] = {
    if (index >= 0 && index < internalList.size) Some(internalList(index))
    else None
  }

  override def size: Int = internalList.size

  override def isEmpty: Boolean = internalList.isEmpty

  override def iterator: Iterator[A] = internalList.iterator
}

object FixedSizeList {
  def apply[A](maxSize: Int): FixedSizeList[A] = new FixedSizeList[A](maxSize)
}
