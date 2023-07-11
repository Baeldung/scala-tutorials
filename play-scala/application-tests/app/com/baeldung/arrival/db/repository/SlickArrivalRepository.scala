package com.baeldung.arrival.db.repository

import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.ExecutionContext

class SlickArrivalRepository @Inject()(val dbProfile: JdbcProfile)(implicit ec: ExecutionContext) extends ArrivalRepository {


  import dbProfile.api._

  private class ArrivalTable(tag: Tag) extends Table[Arrival](tag, "arrival") {

    def id = column[Long]("id", O.PrimaryKey)

    def origin = column[String]("origin")

    def destination = column[String]("destination")

    def plane = column[String]("plane")

    def * = (id, origin, destination, plane) <> ((Arrival.apply _).tupled, Arrival.unapply)
  }

  /**
   * The starting point for all queries on the people table.
   */
  private val arrivals = TableQuery[ArrivalTable]

  def getArrivals: DBIO[Seq[Arrival]] = arrivals.result

}