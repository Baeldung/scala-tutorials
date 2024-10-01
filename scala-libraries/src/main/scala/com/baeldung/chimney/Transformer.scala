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

  // UserDTO

  case class UserDTO(
    name: String, // 1. primitive
    addresses: Seq[AddressDTO], // 2. Seq collection
    recovery: Option[RecoveryMethodDTO] // 3. Option type
  )

  case class AddressDTO(street: String, city: String)

  sealed trait RecoveryMethodDTO

  object RecoveryMethodDTO:
    case class Phone(value: PhoneDTO) extends RecoveryMethodDTO
    case class Email(value: EmailDTO) extends RecoveryMethodDTO

  case class PhoneDTO(number: String)
  case class EmailDTO(email: String)

  // User Domain Model

  case class User(
    name: Username,
    addresses: List[Address],
    recovery: RecoveryMethod
  )

  case class Username(name: String) extends AnyVal
  case class Address(street: String, city: String)

  enum RecoveryMethod:
    case Phone(number: String)
    case Email(email: String)

  // we can do a transformation:

  User(
    Username("John"),
    List(Address("Paper St", "Somewhere")),
    RecoveryMethod.Email("john@example.com")
  ).transformInto[UserDTO]

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
