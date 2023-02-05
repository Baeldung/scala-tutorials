package com.baeldung.scala.filtermap

import scala.collection.mutable

object FilterMap {
  type Rank = Int
  val xmen: Map[Rank, Option[String]] = Map(
    1 -> Some("ProfessorX"),
    2 -> Some("Wolverine"),
    3 -> None,
    4 -> Some("Night Crawler"),
    5 -> None,
  )

  val xmenMutable: mutable.Map[Rank, Option[String]] = mutable.Map(
    1 -> Some("ProfessorX"),
    2 -> Some("Wolverine"),
    3 -> None,
    4 -> Some("Night Crawler"),
    5 -> None,
  )

  // filter
  val filterMap = xmen.filter(_._2 != None)

  // filterNot
  val filterNotMap = xmen.filterNot(_._2 == None)

  // collect
  val collectMap = xmen.collect { case (k, Some(v)) => (k, Some(v)) }

  // for comprehension
  val forMap = for {
    (k, v) <- xmen if v != None
  } yield (k, v)

  // withFilter
  val withFilterMap = xmen.withFilter(_._2 != None)

  // fiterKeys
  val filterKeysMap = xmen.view.filterKeys(xmen(_) != None)
  val filterKeysSet = xmen.view.filterKeys(Set(1, 2, 4))

  // filterInPlace mutable and immutable
  xmenMutable.filterInPlace((_, v) => v != None)
}

object program extends App {
  import FilterMap._

  println(filterMap)
  /*
   * HashMap(1 -> Some(ProfessorX), 2 -> Some(Wolverine), 4 -> Some(Night Crawler))
   **/

  println(filterNotMap)
  /*
   * HashMap(1 -> Some(ProfessorX), 2 -> Some(Wolverine), 4 -> Some(Night Crawler))
   * */

  println(collectMap)
  /*
   * HashMap(1 -> Some(ProfessorX), 2 -> Some(Wolverine), 4 -> Some(Night Crawler))
   * */

  println(forMap)
  /*
   * HashMap(1 -> Some(ProfessorX), 2 -> Some(Wolverine), 4 -> Some(Night Crawler))
   * */

  println(withFilterMap.map(x => x))
  /*
   * HashMap(1 -> Some(ProfessorX), 2 -> Some(Wolverine), 4 -> Some(Night Crawler))
   * */

  println(filterKeysSet.toMap)
  /*
   *Map(1 -> Some(ProfessorX), 2 -> Some(Wolverine), 4 -> Some(Night Crawler))
   * */

  println(filterKeysMap.toMap)
  /*
   *Map(1 -> Some(ProfessorX), 2 -> Some(Wolverine), 4 -> Some(Night Crawler))
   * */

  println(xmenMutable)
  /*
   * HashMap(1 -> Some(ProfessorX), 2 -> Some(Wolverine), 4 -> Some(Night Crawler))
   * */


}
