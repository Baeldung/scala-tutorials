package com.baeldung.arrival.modules

import com.baeldung.arrival.db.manager.{DbManager, PostgresManager}
import com.google.inject.{AbstractModule, Provides}
import play.api.db.slick.DatabaseConfigProvider
import play.db.NamedDatabase
import slick.basic.DatabaseConfig
import slick.jdbc.{JdbcProfile, PostgresProfile}

class PostgresModule extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[DbManager]).to(classOf[PostgresManager])
  }

  @Provides
  def dbProfile(@NamedDatabase("postgres") dbConfigProvider: DatabaseConfigProvider): JdbcProfile = {
    dbConfigProvider.get[PostgresProfile].profile
  }

  @Provides
  def dbConfig(@NamedDatabase("postgres") dbConfigProvider: DatabaseConfigProvider): DatabaseConfig[PostgresProfile] = {
    dbConfigProvider.get[PostgresProfile]
  }

}
