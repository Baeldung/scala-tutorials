package com.baeldung.scala.slick

import java.time.LocalDate

/**
 * Created by yadu on 02/08/20
 */

case class Player(id: Long, name: String, country: String, dob:Option[LocalDate])