package com.baeldung.scala.vectorbenefits

object VectorBasics extends App{

  val vec:Vector[Int] = Vector(1, 2, 3)

  val vecAppended:Vector[Int] = vec :+ 4
  val vecPrepended:Vector[Int] = 0 +: vec

  println(vec, vecPrepended, vecAppended)
}
