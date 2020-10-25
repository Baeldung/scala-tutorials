package com.baeldung.scala.voidtypes

import org.scalatest.funsuite.AnyFunSuite

class NilEmptyListTest extends AnyFunSuite{

  test("Check length of Nil list"){
    assert(Nil.length == 0)
  }

  test("Check equality for List initialized with Nil and List()"){
    val myFirstEmptyList:List[String] = Nil
    val mySecondEmptyList:List[String] = List()
    assert(myFirstEmptyList == mySecondEmptyList)
  }

  test("Check list created using cons operator with Nil"){
    assert("A" :: 1 :: Nil == List("A",1))
  }


}
