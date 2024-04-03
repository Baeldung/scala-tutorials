package com.baeldung.scala.zio.prelude.mapreduce

import zio.prelude._

object MapReduce {
  implicit val intIdentity: Identity[Int] = Identity.make(0, (a, b) => a + b)

  implicit val intAssociative: Associative[Int] =
    new Associative[Int] {
      def combine(left: => Int, right: => Int): Int =
        left + right
    }

  def mapReduce[A, B](as: List[A])(f: A => B)(implicit identity: Identity[B]): B =
    as.map(f).toNonEmptyList.map(_.reduce1).getOrElse(identity.identity)

  def mapReduce2[F[+_] : ForEach, A, B: Identity](as: F[A])(f: A => B): B = as.foldMap(f)

  private val wordsPerLine: String => Int = _.split(" ").length

  def wordCount(lines: List[String]): Int = lines.map(wordsPerLine).sum

  def wordCountAbstract(as: List[String]): Int = mapReduce(as)(wordsPerLine)

  def wordCountVeryAbstract(as: List[String]): Int = mapReduce2(as)(wordsPerLine)

}
