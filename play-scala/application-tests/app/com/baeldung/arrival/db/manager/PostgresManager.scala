package com.baeldung.arrival.db.manager

import slick.basic.DatabaseConfig
import slick.jdbc.PostgresProfile

import javax.inject.Inject

class PostgresManager @Inject() (val dbConfig: DatabaseConfig[PostgresProfile])
  extends DbManager
