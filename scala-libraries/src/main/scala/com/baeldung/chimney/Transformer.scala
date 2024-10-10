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
    doi: Option[String] // 3. Option type
  )

  case class AuthorDTO(name: String, surname: String)

  // Book Domain Model

  case class Book(
    name: Title,
    authors: List[Author],
    doi: DOI
  )

  case class Title(name: String) extends AnyVal
  case class Author(name: String, surname: String)

  type DOI = Option[String]

  // we can do a transformation:

  Book(
    name = Title("The Universal One"),
    authors = List(Author("Walter", "Russell")),
    doi = None
  ).transformInto[BookDTO]

  // Standard Library alternatives

  // Selectable

  class Record(elems: (String, Any)*) extends Selectable:
    private val fields = elems.toMap
    def selectDynamic(name: String): Any = fields(name)

  type Person = Record {
    val name: String
    val age: Int
  }

  val person = Record(
    "name" -> "Emma",
    "age" -> 42
  ).asInstanceOf[Person]

  // Tuple Generics

  case class Employee(name: String, number: Int, manager: Boolean)

  val bob: Employee = Employee("Bob", 42, false)

  val bobTuple: (String, Int, Boolean) = Tuple.fromProductTyped(bob)

  val bobAgain: Employee =
    summon[deriving.Mirror.Of[Employee]].fromProduct(bobTuple)
