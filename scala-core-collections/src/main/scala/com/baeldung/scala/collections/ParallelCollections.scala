package com.baeldung.scala.collections

import scala.collection.parallel.immutable.{ParSeq, ParVector}

object ParallelCollections {
  val parallelList: ParSeq[Int] = (0 to 100).toList.par

  val parallelVector: ParVector[Int] = ParVector.range(0, 1000)

  val otherParallelVector: ParVector[Int] = ParVector.tabulate(1000)(x=>x)

  val parallelMap: ParSeq[Int] = parallelList.map(_*2)

  val parallelFold: Int = parallelList.fold(0)(_ + _)

  val parallelFilter: ParSeq[Int] = parallelList.filter(_ % 2 != 0)
}
