package com.baeldung.scala.zio.httpapp

import com.baeldung.scala.zio.httpapp.app.{
  IngredientWebsocketApp,
  RecipeHttpApp,
  SpeedHttpApp
}
import com.baeldung.scala.zio.httpapp.service.RecipeService
import com.baeldung.scala.zio.httpapp.repo.InMemoryRecipeRepo
import com.baeldung.scala.zio.httpapp.repo.RecipeRepo
import zio.http.{RequestHandlerMiddlewares, Server}
import zio.{Ref, Scope, ZIO, ZIOAppArgs, ZIOAppDefault, ZLayer}

object MainApp extends ZIOAppDefault:
  def run: ZIO[Environment with ZIOAppArgs with Scope, Throwable, Any] =

    val headerMiddleware =
      RequestHandlerMiddlewares.addHeader("X-Environment", "Dev")

    val loggingMiddleware = RequestHandlerMiddlewares.requestLogging(
      logRequestBody = true,
      logResponseBody = true
    )

    val recipeApp = RecipeHttpApp() @@ headerMiddleware @@ loggingMiddleware

    Server
      .serve(
        (recipeApp ++ IngredientWebsocketApp()).withDefaultErrorResponse
      )
      .provide(
        Server.defaultWithPort(8080),
        InMemoryRecipeRepo.layer,
        RecipeService.layer
      )
