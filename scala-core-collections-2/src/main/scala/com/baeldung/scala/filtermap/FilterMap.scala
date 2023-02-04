package com.baeldung.scala.filtermap

object FilterMap {
  type Rank = Int
  val xmen: Map[Rank, Option[String]] = Map(
    1 -> Some("ProfessorX"),
    2 -> Some("Wolverine"),
    3 -> None,
    4 -> Some("Night Crawler"),
    5 -> None,
  )

  val xmen2: Map[Rank, Option[String]] = Map(
    (1, Some("ProfessorX")),
    (2, Some("Wolverine")),
    (3, None),
    (4, Some("Night Crawler")),
    (5, None),
  )
  /** Compare xmen and xmen2
    */

  val compare = xmen.toString().equals(xmen2.toString())

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
}

object program extends App {
  import FilterMap._

  println(compare)
  /** true
    */

  println(filterMap)
  /*
   * HashMap(1 -> Some(ProfessorX), 2 -> Some(Wolverine), 4 -> Some(Night Crawler)
   **/

  println(filterNotMap)
  /*
   * HashMap(1 -> Some(ProfessorX), 2 -> Some(Wolverine), 4 -> Some(Night Crawler)
   * */

  println(collectMap)
  /*
   * HashMap(1 -> Some(ProfessorX), 2 -> Some(Wolverine), 4 -> Some(Night Crawler)
   * */

  println(forMap)
  /*
   * HashMap(1 -> Some(ProfessorX), 2 -> Some(Wolverine), 4 -> Some(Night Crawler)
   * */

}
