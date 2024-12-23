package models

import models.Measure.UnitMeasurement
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints.minLength
import play.api.libs.json.{Json, Writes}

import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.{Date, UUID}

case class SimpleForm(i: Int, active: Boolean, msg: String)

object SimpleForm {

  implicit val simpleFormWrites: Writes[SimpleForm] = Json.writes[SimpleForm]
  val form: Form[SimpleForm] = Form(
    mapping(
      "i" -> number,
      "active" -> boolean,
      "msg" -> text
    )(SimpleForm.apply)(SimpleForm.unapply)
  )

  def unapply(simpleForm: SimpleForm): Option[(Int, Boolean, String)] = {
    Some((simpleForm.i, simpleForm.active, simpleForm.msg))
  }

}

case class MultipleFieldsForm(
  i: Int,
  pwd: String,
  active: Boolean,
  msg: String,
  date: Date,
  uuid: UUID,
  favMovie: String,
  favDrink: String
)

object MultipleFieldsForm {

  implicit val multipleFieldsFormWrites: Writes[MultipleFieldsForm] =
    Json.writes[MultipleFieldsForm]
  val form: Form[MultipleFieldsForm] = Form(
    mapping(
      "i" -> number,
      "pwd" -> text,
      "active" -> boolean,
      "msg" -> text,
      "date" -> date,
      "uuid" -> uuid,
      "favMovie" -> text,
      "favDrink" -> text
    )(MultipleFieldsForm.apply)(MultipleFieldsForm.unapply)
  )

  def unapply(
    multipleFieldsForm: MultipleFieldsForm
  ): Option[(Int, String, Boolean, String, Date, UUID, String, String)] = {
    Some(
      (
        multipleFieldsForm.i,
        multipleFieldsForm.pwd,
        multipleFieldsForm.active,
        multipleFieldsForm.msg,
        multipleFieldsForm.date,
        multipleFieldsForm.uuid,
        multipleFieldsForm.favMovie,
        multipleFieldsForm.favDrink
      )
    )
  }

  object Movies {
    val list = List(
      "pulpFiction" -> "Pulp Fiction",
      "inception" -> "Inception",
      "theMatrix" -> "The Matrix",
      "titanic" -> "Titanic"
    )
  }

  object Drinks {
    val list = List(
      "vodka" -> "Vodka",
      "tequila" -> "Tequila",
      "whisky" -> "Whisky",
      "wine" -> "Wine",
      "beer" -> "Beer"
    )
  }

}

case class ComplexFormCustomField(
  i: Int,
  active: Boolean,
  msg: String,
  measurement: UnitMeasurement
)
object ComplexFormCustomField {

  implicit val complexFormWrites: Writes[ComplexFormCustomField] =
    Json.writes[ComplexFormCustomField]
  val form: Form[ComplexFormCustomField] = Form(
    mapping(
      "i" -> number,
      "active" -> boolean,
      "msg" -> text,
      "measurement" -> Measure.unitMeasurementMapping
    )(ComplexFormCustomField.apply)(ComplexFormCustomField.unapply)
  )

  def unapply(
    complexForm: ComplexFormCustomField
  ): Option[(Int, Boolean, String, UnitMeasurement)] = {
    Some(
      (
        complexForm.i,
        complexForm.active,
        complexForm.msg,
        complexForm.measurement
      )
    )
  }
}

case class InputFormWithConstraints(
  i: Int,
  msg: String,
  msgOpt: Option[String],
  email: String,
  birthday: Date
)
object InputFormWithConstraints {

  implicit val inputFormWrites: Writes[InputFormWithConstraints] =
    Json.writes[InputFormWithConstraints]
  val form: Form[InputFormWithConstraints] = Form(
    mapping(
      "i" -> number(min = 10, max = 20),
      "msg" -> text(minLength = 3, maxLength = 12),
      "msgOpt" -> optional(text),
      "email" -> email,
      "birthday" -> date
    )(InputFormWithConstraints.apply)(InputFormWithConstraints.unapply)
  )

  def unapply(
    inputForm: InputFormWithConstraints
  ): Option[(Int, String, Option[String], String, Date)] = {
    Some(
      (
        inputForm.i,
        inputForm.msg,
        inputForm.msgOpt,
        inputForm.email,
        inputForm.birthday
      )
    )
  }
}

case class InputFormWithCustomConstraints(email: String, birthday: Date)
object InputFormWithCustomConstraints {

  implicit val inputFormWrites: Writes[InputFormWithCustomConstraints] =
    Json.writes[InputFormWithCustomConstraints]
  val form: Form[InputFormWithCustomConstraints] = Form(
    mapping(
      "email" -> email.verifying(minLength(15)),
      "birthday" -> date.verifying(
        "Not 18 years old",
        d => d.toInstant.isBefore(Instant.now().minus(18, ChronoUnit.YEARS))
      )
    )(InputFormWithCustomConstraints.apply)(
      InputFormWithCustomConstraints.unapply
    )
  )

  def unapply(
    inputForm: InputFormWithCustomConstraints
  ): Option[(String, Date)] = {
    Some((inputForm.email, inputForm.birthday))
  }
}
