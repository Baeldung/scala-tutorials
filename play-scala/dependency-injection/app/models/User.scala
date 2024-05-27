package models

import play.api.libs.json.Json

case class User(id: Long, name: String)

object User {
  implicit val writes: play.api.libs.json.OWrites[models.User] =
    Json.writes[User]
}
