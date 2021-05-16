package com.baeldung.scala.vectorbenefits
import scala.util.Random

object VectorRandomAccess extends App{

  def randomAccessSeq(seq:Seq[Int], it:Int): (Double) ={
    val begin = System.currentTimeMillis
    for (j <- 0 until it) {
      val idx = Random.nextInt(it)
      val elem = seq(idx)
    }
    val elapsedTime = System.currentTimeMillis - begin
    return (elapsedTime)
  }

  val numElements = 10000
  val vec:Vector[Int] = (1 to numElements).toVector
  val lst:List[Int] = (1 to numElements).toList

  val randomAccessTimeRatio = randomAccessSeq(lst, numElements)/randomAccessSeq(vec, numElements)

  println("Random access test with %s elements, Vector is ~ %s times faster than List".format(numElements, randomAccessTimeRatio))

}