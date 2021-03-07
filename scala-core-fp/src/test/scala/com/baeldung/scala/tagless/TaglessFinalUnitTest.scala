package com.baeldung.scala.tagless

import com.baeldung.scala.tagless.TaglessFinal.{Product, Program, ProgramWithDep, ShoppingCart}
import org.scalatest.FlatSpec

class TaglessFinalUnitTest extends FlatSpec {

  "Program.createAndAddToCart" should "create a cart and add a new product using the implicit object resolution" in {
    import com.baeldung.scala.tagless.TaglessFinal.ShoppingCarts._
    val iPhone = Product("iPhone12", "The new iPhone 12")

    val cartState = Program.createAndToCart[ScRepoState](iPhone, "cart1")
    val (_, cart) = cartState.run(Map()).value

    assert {
      cart.get.products == List(iPhone)
    }
  }

  "Program.createAndAddToCart" should "create a cart and add a new product using the smart constructors" in {
    import com.baeldung.scala.tagless.TaglessFinal.ShoppingCarts._
    val iPhone = Product("iPhone12", "The new iPhone 12")
    val program = ProgramWithDep(ShoppingCartWithDependencyInterpreter.make())

    val cartState = program.createAndToCart(iPhone, "cart1")
    val (_, cart) = cartState.run(Map()).value

    assert {
      cart.get.products == List(iPhone)
    }
  }
}
