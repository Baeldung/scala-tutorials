package com.baeldung.arrival.modules

import com.baeldung.arrival.db.manager.{DbManager, H2Manager}
import com.google.inject.{AbstractModule, Provides}
import play.api.db.slick.DatabaseConfigProvider
import play.db.NamedDatabase
import slick.basic.DatabaseConfig
import slick.jdbc.{H2Profile, JdbcProfile}

class H2Module extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[DbManager]).to(classOf[H2Manager])
  }

  @Provides
  def dbProfile(@NamedDatabase("h2") dbConfigProvider: DatabaseConfigProvider): JdbcProfile = {
    dbConfigProvider.get[H2Profile].profile
  }

  @Provides
  def dbConfig(@NamedDatabase("h2") dbConfigProvider: DatabaseConfigProvider): DatabaseConfig[H2Profile] = {
    dbConfigProvider.get[H2Profile]
  }

}
