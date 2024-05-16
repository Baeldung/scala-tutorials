package com.baeldung.reflection

import org.junit.Test

import scala.reflect.runtime.{universe => ru}

class ScalaReflectionUnitTest {
  @Test
  def instantiate_a_class_at_runtime_v1: Unit = {
    val mirror: ru.Mirror = ru.runtimeMirror(getClass.getClassLoader)
    val classSymbol: ru.ClassSymbol =
      mirror.staticClass("com.baeldung.reflection.Person")
    val consMethodSymbol = classSymbol.primaryConstructor.asMethod
    val classMirror = mirror.reflectClass(classSymbol)
    val consMethodMirror = classMirror.reflectConstructor(consMethodSymbol)
    val result = consMethodMirror.apply("John", 20)

    assert(result == Person("John", 20))
  }

  @Test
  def instantiate_a_class_at_runtime_v2: Unit = {
    val mirror: ru.Mirror = ru.runtimeMirror(getClass.getClassLoader)
    val classSymbol: ru.ClassSymbol = ru.typeOf[Person].typeSymbol.asClass
    val consMethodSymbol = classSymbol.primaryConstructor.asMethod
    val classMirror = mirror.reflectClass(classSymbol)
    val consMethodMirror = classMirror.reflectConstructor(consMethodSymbol)
    val result = consMethodMirror.apply("John", 20)

    assert(result == Person("John", 20))
  }

  @Test
  def invoke_a_method_at_runtime = {
    val mirror: ru.Mirror = ru.runtimeMirror(getClass.getClassLoader)
    val classSymbol: ru.ClassSymbol = ru.typeOf[Person].typeSymbol.asClass
    val methodSymbol =
      classSymbol.info.decl(ru.TermName("prettyPrint")).asMethod
    val person = Person("John", 20)
    val instanceMirror = mirror.reflect(person)
    val method = instanceMirror.reflectMethod(methodSymbol)

    assert(
      method.apply() ==
        """
          |Person {
          |  name: "John",
          |  age: 20
          |}
          |""".stripMargin
    )
  }

  @Test
  def accessing_private_fields = {
    val mirror: ru.Mirror = ru.runtimeMirror(getClass.getClassLoader)
    val classSymbol: ru.ClassSymbol = ru.typeOf[Person].typeSymbol.asClass
    val passwordTermSymbol =
      classSymbol.info
        .decl(ru.TermName("password"))
        .asTerm
    val person = Person("John", 20)
    val instanceMirror = mirror.reflect(person)
    val passwordFiledMirror = instanceMirror.reflectField(passwordTermSymbol)

    assert(passwordFiledMirror.get == "123")
    passwordFiledMirror.set("321")
    assert(passwordFiledMirror.get == "321")
  }

  @Test
  def reification_test(): Unit = {
    import ru._
    assert(reify(5).tree equalsStructure Literal(Constant(5)))

    assert(
      ru.reify {
        val a = 5;
        val b = 2;
        a + b
      }.tree equalsStructure
        Block(
          List(
            ValDef(
              Modifiers(),
              TermName("a"),
              TypeTree(),
              Literal(Constant(5))
            ),
            ValDef(Modifiers(), TermName("b"), TypeTree(), Literal(Constant(2)))
          ),
          Apply(
            Select(Ident(TermName("a")), TermName("$plus")),
            List(Ident(TermName("b")))
          )
        )
    )
  }
}
