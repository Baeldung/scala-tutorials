package com.baeldung.scala.kafka.intro.common

import java.util.Date

case class Article(
  id: String,
  title: String,
  content: String,
  created: Date,
  author: Author
)

case class Author(id: Int, name: String)
