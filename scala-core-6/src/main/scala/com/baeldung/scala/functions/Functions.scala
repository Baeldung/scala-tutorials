package com.baeldung.scala.functions

import java.util.Date
import java.util.concurrent.Executors
import scala.concurrent.{ExecutionContext, Future}

object Functions {

  val dummyFunctionval: Int => Int = {
    println(new Date())
    num => num
  }

  def dummyFunctiondef(number: Int): Int = {
    println(new Date())
    number
  }

  dummyFunctionval(10) // prints out the date and then the result :10
  dummyFunctionval(10) // doesn't print out date and just returns 10

  dummyFunctiondef(10) // prints out the date and then the result :10
  dummyFunctiondef(10) // prints out the date and then the result :10

  /** vals as specialized instances of FunctionN vs def */
  val getNameLengthVal: String => Int = name => name.length
  val multiplyByTwoVal: Int => Int = num => num * 2

  getNameLengthVal.andThen(multiplyByTwoVal) // compiles

  def getNameLengthDef(name: String): Int = name.length

  def multiplyByTwoDef(number: Int): Int = number * 2

  // getNameLengthDef.andThen(multiplyByTwoDef) //doesn't compile

  /** Method to Function value */
  val getNameLengthDefFnValue = getNameLengthDef _
  val multiplyByTwoDefFnValue = multiplyByTwoDef _

  getNameLengthDefFnValue.andThen(multiplyByTwoDefFnValue) // compiles

  def createUrl(protocol: String, domain: String): String = {
    s"$protocol$domain"
  }

  /** repetitive non-DRY code */
  //  val baeldung = createUrl("https://","www.baeldung.com")
  //  val facebook = createUrl("https://","www.facebook.com")
  //  val twitter = createUrl("https://","www.twitter.com")
  //  val google = createUrl("https://","www.google.com")

  val withHttpsProtocol: String => String = createUrl("https://", _: String)
  val withHttpProtocol: String => String = createUrl("http://", _: String)
  val withFtpProtocol: String => String = createUrl("ftp://", _: String)

  val baeldung = withHttpsProtocol("www.baeldung.com")
  val facebook = withHttpsProtocol("www.facebook.com")
  val twitter = withHttpsProtocol("www.twitter.com")
  val google = withHttpsProtocol("www.google.com")

  def htmlPrinter(
    tag: String,
    value: String,
    attributes: Map[String, String]
  ): String = {
    s"<$tag${attributes.map { case (k, v) => s"""$k="$v"""" }.mkString(" ", " ", "")}>$value</$tag>"
  }

  htmlPrinter("div", "Big Animal", Map("size" -> "34", "show" -> "true"))

  /** repetitive non-DRY code */
  val div1 = htmlPrinter("div", "1", Map("size" -> "34"))
  val div2 = htmlPrinter("div", "2", Map("size" -> "34"))
  val div3 = htmlPrinter("div", "3", Map("size" -> "34"))
  val div4 = htmlPrinter("div", "4", Map("size" -> "34"))

  val withDiv: (String, Map[String, String]) => String =
    htmlPrinter("div", _: String, _: Map[String, String])

  val withDivAndAttr2: String => String =
    withDiv(_: String, Map("size" -> "34"))
  val withDivAndAttr: String => String =
    htmlPrinter("div", _: String, Map("size" -> "34"))

  val withAttr: (String, String) => String =
    htmlPrinter(_: String, _: String, Map("size" -> "34"))

  assert(
    withDivAndAttr("1")
      .contentEquals(htmlPrinter("div", "1", Map("size" -> "34")))
  )

  /** Function Currying */
  def dummyFunction(a: Int, b: Int)(c: Int, d: Int)(e: Int, f: Int): Int = {
    a + b + c + d + e + f
  }

  val first: (Int, Int) => (Int, Int) => Int = dummyFunction(1, 2)
  val second: (Int, Int) => Int = first(3, 4)
  val third: Int = second(5, 6)

  val executionContext: ExecutionContext =
    ExecutionContext.fromExecutorService(Executors.newCachedThreadPool())
  //  val future1 = Future({
  //    Thread.sleep(1000)
  //    2
  //  }, executionContext)
  val future2 = Future {
    Thread.sleep(1000)
    2
  }(executionContext)

  /** Uncurried Function with poor type Inference */
  //  def withListItems[T](list: List[T], f: T => Unit): Unit = {
  //    list match {
  //      case Nil => ()
  //      case h :: t =>
  //        f(h)
  //        withListItems(t, f)
  //    }
  //  }
  //
  //  withListItems(List(1, 2, 3, 4), number => println(number + 2)) // does not compile

  def withListItems[T](list: List[T])(f: T => Unit): Unit = {
    list match {
      case Nil => ()
      case h :: t =>
        f(h)
        withListItems(t)(f)
    }
  }

  withListItems(List(1, 2, 3, 4))(number => println(number + 2)) // compiles

  /** Verbose way of defining Partial functions */
  //  val isWorkingAge: PartialFunction[Int, String] = new PartialFunction[Int, String] {
  //    override def isDefinedAt(x: Int): Boolean = if (x > 18 && x < 60) true else false
  //
  //    override def apply(v1: Int): String = {
  //      if (isDefinedAt(v1)) {
  //        s"You are $v1 years old and can still work"
  //      } else {
  //        s"You are $v1 years old and cannot work"
  //      }
  //    }
  //  }

  /** Less verbose way of defining partial functions */
  val isWorkingAge: PartialFunction[Int, String] = {
    case age if age >= 18 && age <= 60 =>
      s"You are $age years old within working age"
  }

  val isYoung: PartialFunction[Int, String] = {
    case age if age < 18 =>
      s"You are less than 18, and not within the working age"
  }

  val isOld: PartialFunction[Int, String] = {
    case age if age > 60 =>
      s"You are greater than 60 and not within the working age"
  }

  val verdict = isWorkingAge orElse isYoung orElse isOld

  verdict(12) // You are less than 18, and not within the working age
  verdict(22) // You are 22 years old within working age
  verdict(70) // You are greater than 60 and not within the working age

}
