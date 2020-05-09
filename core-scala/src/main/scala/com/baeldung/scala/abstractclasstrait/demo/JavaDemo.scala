package com.baeldung.scala.abstractclasstrait.demo
import com.baeldung.scala.javaabstractclass.Books

class JavaDemo extends Books{
  def showFirstBook: String ={
    getFirstBook()
  }

  def getRecommendation(): String ={
    return getFirstBook()
  }
}
