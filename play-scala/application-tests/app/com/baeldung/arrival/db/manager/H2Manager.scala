package com.baeldung.arrival.db.manager

import slick.basic.DatabaseConfig
import slick.jdbc.H2Profile

import javax.inject.Inject

class H2Manager @Inject() (val dbConfig: DatabaseConfig[H2Profile])
  extends DbManager
