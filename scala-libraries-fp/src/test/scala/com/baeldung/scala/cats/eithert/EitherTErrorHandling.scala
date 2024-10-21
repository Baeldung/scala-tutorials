package com.baeldung.scala.cats.eithert

import cats.data.EitherT

import scala.concurrent.Future

object SimpleErrorHandling {

  case class User(userId: String, name: String, isActive: Boolean)
  def getUserProfile(userId: String): Either[String, User] = ???
  def calculateDiscount(user: User): Either[String, Double] = ???
  def placeOrder(
    itemId: String,
    discount: Double,
    user: User
  ): Either[String, String] = ???

  def performAction(userId: String, itemId: String): Either[String, String] =
    for {
      user <- getUserProfile(userId)
      discount <- calculateDiscount(user)
      orderId <- placeOrder(itemId, discount, user)
    } yield orderId

}

object FutureErrorHandling {

  case class User(userId: String, name: String, isActive: Boolean)
  def getUserProfile(userId: String): Future[Either[String, User]] = ???
  def calculateDiscount(user: User): Future[Either[String, Double]] = ???
  def placeOrder(
    itemId: String,
    discount: Double,
    user: User
  ): Future[Either[String, String]] = ???

  import scala.concurrent.Future
  import scala.concurrent.ExecutionContext.Implicits.global

  def performAction(
    userId: String,
    itemId: String
  ): Future[Either[String, String]] = {
    for {
      userEither <- getUserProfile(userId)
      result <- userEither match {
        case Left(error) => Future.successful(Left(error))
        case Right(user) =>
          for {
            discountEither <- calculateDiscount(user)
            orderResult <- discountEither match {
              case Left(error)     => Future.successful(Left(error))
              case Right(discount) => placeOrder(itemId, discount, user)
            }
          } yield orderResult
      }
    } yield result
  }

}

object EitherTErrorHandling {

  import scala.concurrent.ExecutionContext.Implicits.global
  case class User(userId: String, name: String, isActive: Boolean)
  def getUserProfile(userId: String): Future[Either[String, User]] = ???
  def calculateDiscount(user: User): Future[Either[String, Double]] = ???
  def placeOrder(
    itemId: String,
    discount: Double,
    user: User
  ): Future[Either[String, String]] = ???

  def performAction(
    userId: String,
    itemId: String
  ): Future[Either[String, String]] = {
    (for {
      user <- EitherT(getUserProfile(userId))
      discount <- EitherT(calculateDiscount(user))
      orderId <- EitherT(placeOrder(itemId, discount, user))
    } yield orderId).value
  }

}
