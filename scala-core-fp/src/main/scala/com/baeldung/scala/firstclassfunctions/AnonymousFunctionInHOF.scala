package com.baeldung.scala.firstclassfunctions

object AnonymousFunctionInHOF
{
    def mapOperation(list: List[Int]): List[Int] =
    {
        list.map(number => number + 1)
    }

    def filterOperation(list: List[Int]): List[Int] =
    {
        list.filter(number => number % 5 == 0)
    }

    def foldOperation(list: List[Int]): Int =
    {
        list.foldLeft(0)((sum, number) => sum + number * number)
    }
}
