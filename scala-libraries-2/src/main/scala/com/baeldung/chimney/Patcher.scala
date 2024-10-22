package com.baeldung.chimney

import io.scalaland.chimney.dsl.*

object ChimneyPatcher extends App:

  case class Book(
    name: Title,
    authors: List[Author],
    isbn: ISBN
  )

  case class Title(name: String) extends AnyVal
  case class Author(name: String, surname: String)

  type ISBN = Option[String]

  case class UpdateISBN(isbn: ISBN)

  val book = Book(
    name = Title("Synergetics"),
    authors = List(Author("Buckminster", "Fuller")),
    isbn = None
  )

  val isbnUpdateForm = UpdateISBN(
    isbn = Some("978-0206532048")
  )

  val hardcover: Book = book.patchUsing(isbnUpdateForm)

  // Standard Library Alternative

  val softcover: Book =
    book.copy(
      authors =
        List(Author("Buckminster", "Fuller"), Author("Edmund", "Applewhite")),
      isbn = Some("978-0020653202")
    )
