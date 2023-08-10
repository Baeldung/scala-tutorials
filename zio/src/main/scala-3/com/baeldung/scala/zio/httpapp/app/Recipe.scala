package com.baeldung.scala.zio.httpapp.app

import java.util.UUID
import zio.json._

case class Recipe(id: Long, name: String, ingredients: List[String])

object Recipe:
  given JsonEncoder[Recipe] = DeriveJsonEncoder.gen[Recipe]
  given JsonDecoder[Recipe] = DeriveJsonDecoder.gen[Recipe]
