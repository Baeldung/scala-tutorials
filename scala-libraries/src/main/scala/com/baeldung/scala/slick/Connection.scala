package com.baeldung.scala.slick

import slick.jdbc.H2Profile.api._
/**
 * Created by yadu on 02/08/20
 */


object Connection {

  val db = Database.forConfig("h2mem")

}
