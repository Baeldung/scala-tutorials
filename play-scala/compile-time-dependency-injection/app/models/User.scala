package models

import play.api.libs.json.Json

case class User(id: Long, name: String)

object User {
  implicit val writes = Json.writes[User]
}
