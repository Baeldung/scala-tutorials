package com.baeldung.scala.classvsobject

import java.io.Serializable

object ObjectExamples {

  object Router {
    val baseUrl: String = "https://www.baeldung.com"

    case class Response(baseUrl: String, path: String, action: String)

    private def getAction(path: String): Response = {
      Response(baseUrl, path, "GET")
    }

    private def postAction(path: String): Response = {
      Response(baseUrl, path, "POST")
    }

    private def patchAction(path: String): Response = {
      Response(baseUrl, path, "PATCH")
    }

    private def putAction(path: String): Response = {
      Response(baseUrl, path, "PUT")
    }

    private def deleteAction(path: String): Response = {
      Response(baseUrl, path, "DELETE")
    }
  }

  class Router(path: String) {
    import Router._
    def get(): Response = getAction(path)
    def post(): Response = postAction(path)
    def patch(): Response = patchAction(path)
    def put(): Response = putAction(path)
    def delete(): Response = deleteAction(path)
  }

  sealed class BaeldungEnvironment extends Serializable {
    val name: String = "int"
  }

  object BaeldungEnvironment {

    case class ProductionEnvironment() extends BaeldungEnvironment {
      override val name: String = "production"
    }

    case class StagingEnvironment() extends BaeldungEnvironment {
      override val name: String = "staging"
    }

    case class IntEnvironment() extends BaeldungEnvironment {
      override val name: String = "int"
    }

    case class TestEnvironment() extends BaeldungEnvironment {
      override val name: String = "test"
    }

    def fromEnvString(env: String): Option[BaeldungEnvironment] = {
      env.toLowerCase match {
        case "int"        => Some(IntEnvironment())
        case "staging"    => Some(StagingEnvironment())
        case "production" => Some(ProductionEnvironment())
        case "test"       => Some(TestEnvironment())
        case _            => None
      }
    }
  }
}
