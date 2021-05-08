package com.baeldung.scala.vectorbenefits

object VectorHeadTailAccess extends App{

  def headTailAccessSeq(seq:Seq[Int], f: (Seq[Int]) => Int): (Int, Double) ={
    val begin = System.currentTimeMillis
    val headOrTail = f(seq)
    println(headOrTail)
    val elapsedTime = System.currentTimeMillis - begin
    return (headOrTail, elapsedTime)
  }

  val numElements = 1000000
  val vec:Vector[Int] = (1 to numElements).toVector
  val lst:List[Int] = (1 to numElements).toList

  println(" Vector of %s elements took %s for head access".format(numElements, headTailAccessSeq(vec, (seq) => seq.head)._2 ))
  println(" List of %s elements took %s for head access".format(numElements, headTailAccessSeq(lst, (seq) => seq.head)._2 ))

  println(" Vector of %s elements took %s for tail access".format(numElements, headTailAccessSeq(vec, (seq) => seq.last)._2 ))
  println(" List of %s elements took %s for tail access".format(numElements, headTailAccessSeq(lst, (seq) => seq.last)._2 ))
}
