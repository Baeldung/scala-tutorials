package com.baeldung.scala.fixedsizelist

class CircularBufferFixedSizeList[A](val maxSize: Int) extends Iterable[A] {
  private val buffer = Array.ofDim[Any](maxSize)
  private var start = 0
  private var end = 0

  def add(element: A): Unit = {
    buffer(end) = element
    end = (end + 1) % maxSize

    if (end == start) {
      start = (start + 1) % maxSize
    }
  }

  def get(index: Int): Option[A] = {
    if (index >= 0 && index < size) {
      Some(buffer((start + index) % maxSize).asInstanceOf[A])
    } else {
      None
    }
  }

  override def size: Int = {
    if (end >= start) end - start
    else maxSize - start + end
  }

  override def isEmpty: Boolean = start == end

  override def iterator: Iterator[A] = new Iterator[A] {
    private var pos = start

    override def hasNext: Boolean = pos != end

    override def next(): A = {
      val elem = buffer(pos).asInstanceOf[A]
      pos = (pos + 1) % maxSize
      elem
    }
  }
}

object CircularBufferFixedSizeList {
  def apply[A](maxSize: Int): CircularBufferFixedSizeList[A] =
    new CircularBufferFixedSizeList[A](maxSize)
}
