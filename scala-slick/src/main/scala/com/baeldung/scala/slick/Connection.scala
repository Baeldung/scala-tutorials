package com.baeldung.scala.slick

import slick.jdbc.H2Profile.api._

object Connection {

  val db = Database.forConfig("h2mem")

}
