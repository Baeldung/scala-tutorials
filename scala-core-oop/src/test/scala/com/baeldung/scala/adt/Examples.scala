package com.baeldung.scala.adt

import org.scalatest.FlatSpec
import Examples._

class ExamplesUnitTest extends FlatSpec {

  "isTheMostImportantPiece" should "return true when passing a King" in {
    assert(Examples.isTheMostImportantPiece(ChessPiece(White, King)) == true)
  }

  it should "return true when pasing a piece which is not a King" in {
    assert(Examples.isTheMostImportantPiece(ChessPiece(White, Rook)) == false)
  }

}
