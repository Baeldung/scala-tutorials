package com.baeldung.scala.adt

import com.baeldung.scala.adt.Examples._
import org.scalatest.flatspec.AnyFlatSpec

class ExamplesUnitTest extends AnyFlatSpec {

  "isTheMostImportantPiece" should "return true when passing a King" in {
    assert(Examples.isTheMostImportantPiece(ChessPiece(White, King)) == true)
  }

  it should "return true when pasing a piece which is not a King" in {
    assert(Examples.isTheMostImportantPiece(ChessPiece(White, Rook)) == false)
  }

}
