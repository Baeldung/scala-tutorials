package models

import play.api.data.format.{Formats, Formatter}
import play.api.data.{FormError, Forms, Mapping}
import play.api.libs.json.{Json, Writes}

object Measure {

  def unitMeasurementMapping: Mapping[UnitMeasurement] =
    Forms.of[UnitMeasurement]

  case class UnitMeasurement(quantity: Int, unit: String)

  implicit def binder: Formatter[UnitMeasurement] =
    new Formatter[UnitMeasurement] {
      override def bind(
        key: String,
        data: Map[String, String]
      ): Either[Seq[FormError], UnitMeasurement] = Formats.parsing(
        d => UnitMeasurement.fromString(d),
        "The format is (\\d*)(\\s)(\\D*)- example: \"1 pound\"",
        Nil
      )(key, data)

      override def unbind(
        key: String,
        value: UnitMeasurement
      ): Map[String, String] = Map(key -> s"${value.quantity} ${value.unit}")
    }

  object UnitMeasurement {

    implicit val unitMeasurementFormWrites: Writes[UnitMeasurement] =
      Json.writes[UnitMeasurement]

    private val pattern = "(\\d*)(\\s)(\\D*)".r

    def fromString(str: String): UnitMeasurement = {
      val matches = pattern.findAllIn(str)
      if (matches.hasNext) {
        val List(number, space, quantity) = matches.subgroups
        UnitMeasurement(number.toInt, quantity)
      } else {
        throw new RuntimeException(s"Incorrect data: $str")
      }
    }
  }

}
