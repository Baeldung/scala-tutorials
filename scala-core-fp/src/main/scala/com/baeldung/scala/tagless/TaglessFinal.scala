package com.baeldung.scala.tagless

import cats.Monad
import cats.data.State
import cats.syntax.flatMap._
import cats.syntax.functor._

object TaglessFinal {

  case class Product(id: String, description: String)

  case class ShoppingCart(id: String, products: List[Product])

  trait ShoppingCarts[F[_]] {
    def create(id: String): F[Unit]

    def find(id: String): F[Option[ShoppingCart]]

    def add(sc: ShoppingCart, product: Product): F[ShoppingCart]
  }

  object ShoppingCarts {
    type ShoppingCartRepository = Map[String, ShoppingCart]
    type ScRepoState[A] = State[ShoppingCartRepository, A]

    def apply[F[_]](implicit sc: ShoppingCarts[F]): ShoppingCarts[F] = sc

    implicit object TestShoppingCartInterpreter extends ShoppingCarts[ScRepoState] {
      override def create(id: String): ScRepoState[Unit] =
        State.modify { carts =>
          val shoppingCart = ShoppingCart(id, List())
          carts + (id -> shoppingCart)
        }

      override def find(id: String): ScRepoState[Option[ShoppingCart]] =
        State.inspect { carts =>
          carts.get(id)
        }

      override def add(sc: ShoppingCart, product: Product): ScRepoState[ShoppingCart] =
        State { carts =>
          val products = sc.products
          val updatedCart = sc.copy(products = product :: products)
          (carts + (sc.id -> updatedCart), updatedCart)
        }
    }

    class ShoppingCartWithDependencyInterpreter private(repo: ShoppingCartRepository)
      extends ShoppingCarts[ScRepoState] {
      override def create(id: String): ScRepoState[Unit] = ???

      override def find(id: String): ScRepoState[Option[ShoppingCart]] = ???

      override def add(sc: ShoppingCart, product: Product): ScRepoState[ShoppingCart] = ???
    }

    object ShoppingCartWithDependencyInterpreter {
      def make(): ShoppingCartWithDependencyInterpreter = {
        new ShoppingCartWithDependencyInterpreter(repository)
      }

      private val repository: ShoppingCartRepository = Map()
    }

  }

  object Program {

    // Using implicit objects (explicit)
    def createAndAddToCart[F[_] : Monad](product: Product, cartId: String)
                                        (implicit shoppingCarts: ShoppingCarts[F]): F[ShoppingCart] =
      for {
        _ <- shoppingCarts.create(cartId)
        maybeSc <- shoppingCarts.find(cartId)
      } yield {
        maybeSc match {
          case Some(sc) => shoppingCarts.add(sc, product)
          case _ => maybeSc
        }
      }

    // Using the summoned values pattern
    def createAndToCart[F[_] : Monad : ShoppingCarts](product: Product, cartId: String): Unit = {
      for {
        _ <- ShoppingCarts[F].create(cartId)
        maybeSc <- ShoppingCarts[F].find(cartId)
      } yield {
        maybeSc match {
          case Some(sc) => ShoppingCarts[F].add(sc, product)
          case _ => maybeSc
        }
      }
    }
  }

  class ProgramWithDep[F[_] : Monad](carts: ShoppingCarts[F]) {
    def createAndToCart(product: Product, cartId: String): Unit = {
      for {
        _ <- carts.create(cartId)
        maybeSc <- carts.find(cartId)
      } yield {
        maybeSc match {
          case Some(sc) => carts.add(sc, product)
          case _ => maybeSc
        }
      }
    }
  }

}
