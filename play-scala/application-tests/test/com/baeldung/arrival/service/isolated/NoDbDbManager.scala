package com.baeldung.arrival.service.isolated

import com.baeldung.arrival.db.manager.DbManager
import slick.basic.DatabaseConfig
import slick.dbio.{DBIO, SuccessAction}

import scala.concurrent.Future

class NoDbDbManager extends DbManager {
  override def dbConfig: DatabaseConfig[_] = ???

  override def execute[T](dbio: DBIO[T]): Future[T] = Future.successful(dbio.asInstanceOf[SuccessAction[T]].value)
}
