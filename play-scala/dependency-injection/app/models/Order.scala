package models

import play.api.libs.json.Json

object Order {
  implicit val writes: play.api.libs.json.OWrites[models.Order] =
    Json.writes[Order]
}

case class Order(id: Long, userId: Long, date: Long, isEnterprise: Boolean)
