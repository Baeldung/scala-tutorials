package com.baeldung.scala.zio.httpapp.app

import com.baeldung.scala.zio.httpapp.service.RecipeService
import com.baeldung.scala.zio.httpapp.repo.RecipeRepo
import zio.*
import zio.http.{Root, *}
import zio.json.*

import scala.util.{Failure, Success, Try}

object RecipeHttpApp:

  val appContext = "recipes"

  private type RecipeEffect = ZIO[RecipeService, Throwable, Response]

  val getHandler: PartialFunction[Request, RecipeEffect] = {
    case Method.GET -> Root / RecipeHttpApp.appContext / id =>
      (for {
        idLong <- ZIO.fromTry(Try(id.toLong))
        response <- ZIO
          .serviceWithZIO[RecipeService](_.find(idLong))
          .map({
            case Some(recipe) => Response.json(recipe.toJson)
            case None         => Response.status(Status.NotFound)
          })
      } yield response).orDie
  }

  val postHandler: PartialFunction[Request, RecipeEffect] = {
    case req @ (Method.POST -> Root / RecipeHttpApp.appContext) =>
      (for {
        u <- req.body.asString.map(_.fromJson[Recipe])
        response <- u match {
          case Left(e) =>
            ZIO
              .debug(s"Failed to parse the input: $e")
              .as(
                Response.text(e).withStatus(Status.BadRequest)
              )
          case Right(recipe) =>
            ZIO
              .serviceWithZIO[RecipeService](_.save(recipe))
              .map(recipe => Response.json(recipe.toJson))
        }
      } yield response).orDie
  }

  private val putHandler: PartialFunction[Request, RecipeEffect] = {
    case req @ Method.PUT -> Root / RecipeHttpApp.appContext =>
      (for {
        u <- req.body.asString.map(_.fromJson[Recipe])
        response <- u match {
          case Left(e) =>
            ZIO
              .debug(s"Failed to parse the input: $e")
              .as(
                Response.text(e).withStatus(Status.BadRequest)
              )
          case Right(recipe) =>
            ZIO
              .serviceWithZIO[RecipeService](_.update(recipe))
              .map({
                case Some(recipe) => Response.json(recipe.toJson)
                case None         => Response.status(Status.NotFound)
              })
        }
      } yield response).orDie
  }

  private val deleteHandler: PartialFunction[Request, RecipeEffect] = {
    case Method.DELETE -> Root / RecipeHttpApp.appContext / id =>
      (for {
        idLong <- ZIO.fromTry(Try(id.toLong))
        response <- ZIO
          .serviceWithZIO[RecipeService](_.delete(idLong))
          .map({
            case Some(recipe) => Response.json(recipe.toJson)
            case None         => Response.status(Status.NotFound)
          })
      } yield response).orDie
  }

  def apply(): Http[RecipeService, Throwable, Request, Response] =
    Http.collectZIO[Request] { getHandler } ++
      Http.collectZIO[Request] { postHandler } ++
      Http.collectZIO[Request] { putHandler } ++
      Http.collectZIO[Request] { deleteHandler }
