package com.baeldung.scala.classT

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ClassTUnitTest extends AnyFlatSpec with Matchers {
  def createInstance[T](clazz: Class[T], args: Array[AnyRef]): Option[T] = {
    try {
      // Find the appropriate constructor based on the args types
      val constructor = clazz.getConstructors
        .find { c =>
          c.getParameterTypes.length == args.length &&
          (c.getParameterTypes zip args.map(_.getClass)).forall {
            case (paramType, argType) => paramType.isAssignableFrom(argType)
          }
        }
        .getOrElse(
          throw new NoSuchMethodException("Suitable constructor not found")
        )

      // Instantiate the class with arguments
      Some(constructor.newInstance(args: _*).asInstanceOf[T])
    } catch {
      case e: Exception =>
        println(s"Error creating instance of ${clazz.getName}: ${e.getMessage}")
        None
    }
  }

  "createInstance with classOf[T]" should "successfully create an instance of Person" in {
    val personClass: Class[Person] = classOf[Person]
    val personInstance = createInstance(personClass, Array("John Doe": AnyRef))

    personInstance should not be empty
    personInstance.get.toString should include("Person with name: John Doe")
  }

  "createInstance with .getClass" should "successfully create another instance of Person" in {
    val dummyPerson = new Person("Dummy")
    val personClass: Class[_ <: Person] = dummyPerson.getClass
    val personInstance = createInstance(personClass, Array("Jane Doe": AnyRef))

    personInstance should not be empty
    personInstance.get.toString should include("Person with name: Jane Doe")
  }

  "createInstance with .getClass" should "use the type of the variable to bound the type" in {
    val dummyPerson: Object = new Person("Dummy")
    val personClass: Class[_ <: AnyRef] = dummyPerson.getClass
    val personInstance = createInstance(personClass, Array("Jane Doe": AnyRef))

    personInstance should not be empty
    personInstance.get.toString should include("Person with name: Jane Doe")
  }

}
