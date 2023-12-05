package com.baeldung.scala.randomfixedsizesample

import scala.annotation.tailrec
import scala.util.Random

object RandomFixedSizeSample extends App {

  // 1. Using recursion and Random.nextInt
  def getRandomSampleRec[T](list: List[T], size: Int): List[T] = {
    @tailrec
    def rec(xs: List[T], acc: List[T]): List[T] = {

      if (acc.size == size) {
        acc
      } else {

        val index = Random.nextInt(xs.size)
        val (left, right) = xs.splitAt(index)
        val (xsUpd, next) = if (right.nonEmpty) {
          (left ::: right.tail, right.head)
        } else {
          (left.dropRight(1), left.tail.last)
        }
        rec(xsUpd, next :: acc)
      }
    }

    if (size == 0) {
      List.empty[T]
    } else if (size > list.size) {
      list
    } else {
      rec(list, List.empty[T])
    }

  }

  // 2. Using zip with random numbers, sort and take
  def getRandomSampleZip[T](list: List[T], size: Int): List[T] =
    list
      .map(elem => (Random.nextInt(), elem))
      .sortBy(_._1)
      .map(_._2)
      .take(size)

  // 3. Using shuffle and take
  def getRandomSampleShuffle[T](list: List[T], size: Int): List[T] =
    Random.shuffle(list).take(size)

}
