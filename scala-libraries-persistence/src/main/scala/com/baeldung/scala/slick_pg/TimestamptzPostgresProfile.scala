package com.baeldung.scala.slick_pg

import slick.jdbc.PostgresProfile

import java.sql.{PreparedStatement, ResultSet}
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatterBuilder
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField

trait TimestamptzPostgresProfile extends PostgresProfile {

  override val columnTypes: PostgresJdbcTypes = new PostgresJdbcTypes {

    override val offsetDateTimeType: OffsetDateTimeJdbcType =
      new OffsetDateTimeJdbcType {

        override def sqlType: Int = {
          java.sql.Types.TIMESTAMP_WITH_TIMEZONE
        }

        private val formatter = {
          new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            .optionalStart()
            .appendFraction(ChronoField.NANO_OF_SECOND, 0, 6, true)
            .optionalEnd()
            .optionalStart()
            .appendOffset("+HH:mm:ss", "+00")
            .optionalEnd()
            .toFormatter()
        }

        override def setValue(
          v: OffsetDateTime,
          p: PreparedStatement,
          idx: Int
        ): Unit = {
          p.setObject(idx, v)
        }
        override def getValue(r: ResultSet, idx: Int): OffsetDateTime = {
          r.getString(idx) match {
            case null         => null
            case date: String => OffsetDateTime.from(formatter.parse(date))
          }
        }
        override def updateValue(
          v: OffsetDateTime,
          r: ResultSet,
          idx: Int
        ): Unit = {
          r.updateObject(idx, v)
        }
      }
  }
}
