package com.baeldung.scala.zio.httpapp.app

import com.baeldung.scala.zio.httpapp.repo.InMemoryRecipeRepo
import com.baeldung.scala.zio.httpapp.service.RecipeService
import zio.test.*
import zio.http.*
import zio.*
import zio.http.netty.server.NettyDriver

object RecipeHttpAppSpec extends ZIOSpecDefault:

  def status(response: Response): Status = response.status

  override def spec = suite("RecipeHttpApp")(
    test("get should return 404 for non existent recipes") {
      for {
        client <- ZIO.service[Client]
        testRequest <- getRecipe1
        _ <- TestServer.addHandler {
          RecipeHttpApp.getHandler
        }
        response1 <- client.request(testRequest)
      } yield assertTrue(status(response1) == Status.NotFound)
    }.provideSome[Client with Driver](
      InMemoryRecipeRepo.layer,
      RecipeService.layer,
      TestServer.layer,
      Scope.default
    ),
    test("post should create recipes") {
      for {
        client <- ZIO.service[Client]
        testRequest1 <- postRecipe1
        _ <- TestServer.addHandler {
          RecipeHttpApp.getHandler
        }
        _ <- TestServer.addHandler {
          RecipeHttpApp.postHandler
        }
        response1 <- client.request(testRequest1)
        testRequest2 <- getRecipe1
        response2 <- client.request(testRequest2)
      } yield assertTrue(
        status(response1) == Status.Ok,
        status(response2) == Status.Ok
      )
    }.provideSome[Client with Driver](
      InMemoryRecipeRepo.layer,
      RecipeService.layer,
      TestServer.layer,
      Scope.default
    )
  ).provide(
    ZLayer.succeed(Server.Config.default.onAnyOpenPort),
    Client.default,
    Driver.default
  )

  private def getRecipe1 =
    for {
      port <- ZIO.serviceWith[Server](_.port)
    } yield Request
      .get(
        URL(Path.root / "recipes" / "1").withPort(port)
      )
      .addHeaders(Headers(Header.Accept(MediaType.text.`plain`)))

  private def postRecipe1 =
    for {
      port <- ZIO.serviceWith[Server](_.port)
    } yield Request
      .post(
        Body.fromString("""
            |{
            | "id": 1,
            | "name": "test-recipe",
            | "ingredients": ["ingr1", "ingr2"]
            |}
            |""".stripMargin),
        url = URL(Path.root / "recipes").withPort(port)
      )
      .addHeaders(Headers(Header.Accept(MediaType.text.`plain`)))
