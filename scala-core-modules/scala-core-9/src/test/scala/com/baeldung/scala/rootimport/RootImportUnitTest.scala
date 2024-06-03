package com.baeldung.scala.rootimport

import org.scalatest.flatspec.AnyFlatSpec

class RootImportUnitTest extends AnyFlatSpec {

  it should "use the custom List when using relative import" in {
    import scala.*
    val myList: scala.List = List(100)
    assert(myList.toString == "MyList[100]")
  }

  it should "use the scala collection List when using root import" in {
    import _root_.scala.*
    val scalaList: List[Int] = List(100)
    assert(scalaList.toString == "List(100)")
  }

}
