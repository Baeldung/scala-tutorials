package com.baeldung.scala.collections

import scala.collection.parallel.immutable.{ParSeq, ParVector}
import scala.collection.parallel.CollectionConverters._
object ParallelCollections {
  lazy val parallelList: ParSeq[Int] = (0 to 100).toList.par

  lazy val parallelVector: ParVector[Int] = ParVector.range(0, 1000)

  lazy val otherParallelVector: ParVector[Int] = ParVector.tabulate(1000)(x=>x)

  lazy val parallelMap: ParSeq[Int] = parallelList.map(_*2)

  lazy val parallelFold: Int = parallelList.fold(0)(_ + _)

  lazy val parallelFilter: ParSeq[Int] = parallelList.filter(_ % 2 != 0)
}
