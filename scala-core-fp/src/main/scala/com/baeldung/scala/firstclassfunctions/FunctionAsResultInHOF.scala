package com.baeldung.scala.firstclassfunctions

object FunctionAsResultInHOF
{
    def performAddition(x: Int, y: Int): Int = x + y

    def performSubtraction(x: Int, y: Int): Int = x - y

    def performMultiplication(x: Int, y: Int): Int = x * y

    def performArithmeticOperation(num1: Int, num2: Int, operation: String): Int =
    {
        operation match
        {
            case "addition" => performAddition(num1, num2)
            case "subtraction" => performSubtraction(num1, num2)
            case "multiplication" => performMultiplication(num1, num2)
            case _ => -1
        }
    }
}
