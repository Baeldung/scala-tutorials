package com.baeldung.scala.tagless

import cats.Monad
import cats.syntax.flatMap._
import cats.syntax.functor._

object TaglessFinal {

  case class Product(id: String, description: String)

  trait Products[F[_]] {
    def findById(id: String): F[Product]
  }

  case class ShoppingCart(id: String, products: List[Product])

  trait ShoppingCarts[F[_]] {
    def findById(id: String): F[ShoppingCart]
  }

  object Program {
    def addToCart[F[_]: Monad](productId: String, cartId: String)
                              (implicit products: Products[F],
                               shoppingCarts: ShoppingCarts[F]): F[Option[ShoppingCart]] =
      for {
        pr <- products.findById(productId)
        sc <- shoppingCarts.findById(cartId)
        prs = sc.products
      } yield sc.copy(products = pr :: prs)
  }
}
