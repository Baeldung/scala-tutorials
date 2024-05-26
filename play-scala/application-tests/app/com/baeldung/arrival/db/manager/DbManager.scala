package com.baeldung.arrival.db.manager

import slick.basic.DatabaseConfig
import slick.dbio.DBIO

import scala.concurrent.Future

trait DbManager {

  def dbConfig: DatabaseConfig[?]

  def execute[T](dbio: DBIO[T]): Future[T] = dbConfig.db.run(dbio)

}
