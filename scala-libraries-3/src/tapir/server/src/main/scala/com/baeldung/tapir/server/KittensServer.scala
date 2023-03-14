package com.baeldung.tapir.server

import com.baeldung.tapir.endpoint.{AnimalEndpoints, ErrorResponse, Kitten}
import com.baeldung.tapir.server.{Database => DB}
import sttp.model.StatusCode
import sttp.tapir.server.akkahttp.AkkaHttpServerInterpreter

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object KittensServer extends App with BaseAkkaServer {

  val getKittens = AkkaHttpServerInterpreter().toRoute(
    AnimalEndpoints.kittens
      .serverLogic(_ => {
        Future.successful[Either[String, List[Kitten]]](Right(DB.kittens))
      })
  )

  val postKittens = AkkaHttpServerInterpreter().toRoute(
    AnimalEndpoints.kittensPost
      .serverLogic(kitten => {
        if (kitten.id <= 0) {
          Future.successful(Left(StatusCode.BadRequest -> ErrorResponse("negative ids are not accepted")))
        } else {
          if (DB.kittens.exists(_.id == kitten.id)) {
            Future.successful(Left(StatusCode.BadRequest -> ErrorResponse(s"kitten with id ${kitten.id} already exists")))
          } else {
            DB.kittens = DB.kittens :+ kitten
            Future.successful(Right(StatusCode.Ok -> kitten))
          }
        }
      })
  )

  val putKittens = AkkaHttpServerInterpreter().toRoute(
    AnimalEndpoints.kittensPut
      .serverLogic(kitten => {
        val updatedKittenOpt = DB.kittens.find(_.id == kitten.id).map(_.copy(name = kitten.name, gender = kitten.gender, ageInDays = kitten.ageInDays))
        updatedKittenOpt.map(updatedKitten => {
          DB.kittens = DB.kittens.filterNot(_.id == kitten.id) :+ updatedKitten
          Future.successful(Right(StatusCode.Ok -> updatedKitten))
        }).getOrElse(
          Future.successful(Left(StatusCode.NotFound -> ErrorResponse(s"kitten with id ${kitten.id} was not found")))
        )
      })
  )

  val deleteKittens = AkkaHttpServerInterpreter().toRoute(
    AnimalEndpoints.kittensDelete
      .serverLogic(kittenId => {
        val deletedKittenOpt = DB.kittens.find(_.id == kittenId)
        deletedKittenOpt.map(deletedKitten => {
          DB.kittens = DB.kittens.filterNot(_.id == kittenId)
          Future.successful(Right(StatusCode.Ok -> deletedKitten))
        }).getOrElse(
          Future.successful(Left(StatusCode.NotFound -> ErrorResponse(s"kitten with id $kittenId was not found")))
        )
      })
  )

  val endpointsForDocs = List(
    AnimalEndpoints.kittens,
    AnimalEndpoints.kittensPut,
    AnimalEndpoints.kittensPost,
    AnimalEndpoints.kittensDelete
  )

  start(Seq(getKittens, postKittens, putKittens, deleteKittens, withSwaggerDocs(endpointsForDocs)))

}
