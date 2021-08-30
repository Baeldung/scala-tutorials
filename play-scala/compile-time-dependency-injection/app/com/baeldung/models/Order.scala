package com.baeldung.models

import play.api.libs.json.Json

object Order {
  implicit val writes = Json.writes[Order]
}

case class Order(id: Long, userId: Long, date: Long, isEnterprise: Boolean)
