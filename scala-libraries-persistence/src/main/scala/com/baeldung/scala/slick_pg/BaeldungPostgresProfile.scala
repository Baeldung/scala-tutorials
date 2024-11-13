package com.baeldung.scala.slick_pg

import com.github.tminglei.slickpg.*
import org.json4s.{JValue, JsonMethods}
import slick.jdbc.JdbcType

trait BaeldungPostgresProfile
  extends ExPostgresProfile
  with PgDate2Support
  with TimestamptzPostgresProfile
  with PgArraySupport
  with PgJson4sSupport {

  override protected def computeCapabilities: Set[slick.basic.Capability] =
    super.computeCapabilities + slick.jdbc.JdbcCapabilities.insertOrUpdate

  override val api = BaeldungApi

  override val pgjson = "jsonb"
  type DOCType = org.json4s.native.Document
  override val jsonMethods =
    org.json4s.native.JsonMethods.asInstanceOf[JsonMethods[DOCType]]

  object BaeldungApi
    extends ExtPostgresAPI
    with Date2DateTimeImplicitsDuration
    with ArrayImplicits
    with JsonImplicits {

    implicit val doubleListTypeMapper: JdbcType[List[Double]] =
      new SimpleArrayJdbcType[Double]("DOUBLE PRECISION").to(_.toList)
  }
}

object BaeldungPostgresProfile extends BaeldungPostgresProfile
