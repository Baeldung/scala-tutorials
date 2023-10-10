package com.baeldung.scala.reverselists

import scala.annotation.tailrec

/** Contains several implementations of methods to reverse sequences
  * (SCALA-623).
  */
object ListReverser {

  /** Builds a sequence with the same elements as the argument, in reverse
    * order. It uses a simple recursion pattern, hence accumulating in the heap.
    * @param xs
    *   sequence to be reversed
    * @tparam T
    *   type of the elements of the sequence
    * @return
    *   sequence with the input elements in reverse order
    */
  def naiveRecursiveReverse[T](xs: Seq[T]): Seq[T] =
    if (xs.isEmpty) xs
    else naiveRecursiveReverse(xs.drop(1)) :+ xs.head

  /** Builds a sequence with the same elements as the argument, in reverse
    * order. It uses a tail-recursive pattern, so the heap is safe.
    *
    * @param xs
    *   sequence to be reversed
    * @tparam T
    *   type of the elements of the sequence
    * @return
    *   sequence with the input elements in reverse order
    */
  def tailRecursiveReverse[T](xs: Seq[T]): Seq[T] = {
    @tailrec
    def aux(acc: Seq[T], sequence: Seq[T]): Seq[T] =
      if (sequence.isEmpty) acc
      else aux(sequence.head +: acc, sequence.tail)

    aux(Seq.empty[T], xs)
  }

  /** Builds a sequence with the same elements as the argument, in reverse
    * order. It uses the fold operation, thus enforcing functional principles.
    *
    * @param xs
    *   sequence to be reversed
    * @tparam T
    *   type of the elements of the sequence
    * @return
    *   sequence with the input elements in reverse order
    */
  def foldBasedReverse[T](xs: Seq[T]): Seq[T] =
    xs.foldLeft(Seq.empty[T])((sequence, element) => element +: sequence)

}
