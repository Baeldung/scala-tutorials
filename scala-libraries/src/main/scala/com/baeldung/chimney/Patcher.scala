package com.baeldung.chimney

import io.scalaland.chimney.dsl.*

object ChimneyPatcher extends App:

  case class Book(
    name: Title,
    authors: List[Author],
    doi: DOI
  )

  case class Title(name: String) extends AnyVal
  case class Author(name: String, surname: String)

  type DOI = Option[String]

  case class BookUpdateForm(name: String, authors: List[Author])

  val book = Book(
    name = Title("Synergetics"),
    authors = List(Author("Buckminster", "Fuller")),
    doi = None
  )

  val updateForm = BookUpdateForm(
    name = "Godel, Escher, Bach",
    authors = List(Author("Douglas", "Hofstadter"))
  )

  val patchedValue: Book = book.patchUsing(updateForm)

  // Standard Library Alternative

  case class SimpleCaseClass(a: Int, b: Int)

  val simpleClass = SimpleCaseClass(0, 0)

  simpleClass.copy(b = 2) // SimpleCaseClass(0, 2)
