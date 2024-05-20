package com.baeldung.scala.abstractclassvstrait

abstract class Interpreter[Code, Output] {
  def interpret(code: Code): Output
}

object Interpreter {
  def interpret[Code, Output](
    interpreter: Interpreter[Code, Output],
    code: Code
  ): Output =
    interpreter.interpret(code)
}

class Adder extends Interpreter[List[Int], Int] {
  def interpret(code: List[Int]): Int = code.sum
}

class Multiplier extends Interpreter[List[Int], Int] {
  def interpret(code: List[Int]): Int = code.product
}

trait CalculatorTerm
trait Operator

case class Literal(number: Int) extends CalculatorTerm

case class Multiply(term1: CalculatorTerm, term2: CalculatorTerm)
  extends CalculatorTerm
  with Operator

case class Add(term1: CalculatorTerm, term2: CalculatorTerm)
  extends CalculatorTerm
  with Operator

class Calculator extends Interpreter[CalculatorTerm, Int] {
  def interpret(code: CalculatorTerm): Int = code match {
    case Literal(number)        => number
    case Multiply(term1, term2) => interpret(term1) * interpret(term2)
    case Add(term1, term2)      => interpret(term1) + interpret(term2)
  }
}
