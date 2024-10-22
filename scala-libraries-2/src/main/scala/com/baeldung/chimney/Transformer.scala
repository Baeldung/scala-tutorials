package com.baeldung.chimney

import io.scalaland.chimney.*, dsl.*, partial.*

object ChimneyTransformers extends App:

  class MyType(val a: Int)

  class MyOtherType(val b: String):
    override def toString: String = s"MyOtherType($b)"

  val transformer: Transformer[MyType, MyOtherType] = (src: MyType) =>
    new MyOtherType(src.a.toString)

  transformer.transform(new MyType(10)) // new MyOtherType("10")

  implicit val transformerAsImplicit: Transformer[MyType, MyOtherType] =
    transformer

  (new MyType(10)).transformInto[MyOtherType]

  // Transitive Given Instances

  trait Serial[T]:
    def serial(v: T): String

  given Serial[MyOtherType] with
    def serial(v: MyOtherType): String = v.toString

  given [F, T](using Serial[T], Transformer[F, T]): Serial[F] with
    def serial(v: F): String =
      summon[Serial[T]].serial:
        summon[Transformer[F, T]].transform(v)

  // Automatic Case Class Transformation

  // BookDTO

  case class BookDTO(
    name: String, // 1. primitive
    authors: Seq[AuthorDTO], // 2. Seq collection
    isbn: Option[String] // 3. Option type
  )

  case class AuthorDTO(name: String, surname: String)

  // Book Domain Model

  case class Book(
    name: Title,
    authors: List[Author],
    isbn: ISBN
  )

  case class Title(name: String) extends AnyVal
  case class Author(name: String, surname: String)

  type ISBN = Option[String]

  // we can do a transformation:

  val book = Book(
    name = Title("The Universal One"),
    authors = List(Author("Walter", "Russell")),
    isbn = None
  )

  val bookDTO: BookDTO = book.transformInto[BookDTO]

  // Standard Library alternatives

  // Selectable

  class Record(elems: (String, Any)*) extends Selectable:
    private val fields = elems.toMap
    def selectDynamic(name: String): Any = fields(name)

  type BookRecord = Record {
    val name: Title
    val authors: List[Author]
    val isbn: ISBN
  }

  val naturesOpenSecret: BookRecord = Record(
    "name" -> Title("Nature's Open Secret"),
    "authors" -> List(Author("Rudolph", "Steiner")),
    "isbn" -> Some("978-0880103930")
  ).asInstanceOf[BookRecord]

  // Tuple Generics

  val bookTuple: (Title, List[Author], ISBN) = Tuple.fromProductTyped(book)

  val bookAgain: Book =
    summon[deriving.Mirror.Of[Book]].fromProduct(bookTuple)
