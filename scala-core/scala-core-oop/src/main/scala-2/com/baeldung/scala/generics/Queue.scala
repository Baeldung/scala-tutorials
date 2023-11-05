package com.baeldung.scala.generics

class Queue[+T](private val leading: List[T], trailing: List[T]) {

  def enqueue[U >: T](x: U): Queue[U] = new Queue(leading, x :: trailing)
}
