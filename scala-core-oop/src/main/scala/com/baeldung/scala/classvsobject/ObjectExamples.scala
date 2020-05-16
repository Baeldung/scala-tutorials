package com.baeldung.scala.classvsobject

import java.io.Serializable

object ObjectExamples {

  object Router {
    val base_url: String = "https://www.baeldung.com"

    case class Response(base_url: String, path: String, action: String)

    private def get_action(path: String): Response = {
      Response(base_url, path, "GET")
    }

    private def post_action(path: String): Response = {
      Response(base_url, path, "POST")
    }

    private def patch_action(path: String): Response = {
      Response(base_url, path, "PATCH")
    }

    private def put_action(path: String): Response = {
      Response(base_url, path, "PUT")
    }

    private def delete_action(path: String): Response = {
      Response(base_url, path, "DELETE")
    }
  }

  class Router(path: String) {
    import Router._
    def get(): Response = get_action(path)
    def post(): Response = post_action(path)
    def patch(): Response = patch_action(path)
    def put(): Response = put_action(path)
    def delete(): Response = delete_action(path)
  }

  sealed class BaeldungEnvironment extends Serializable {val name: String = "int"}

  object BaeldungEnvironment {

    case class ProductionEnvironment() extends BaeldungEnvironment {override val name: String = "production"}

    case class StagingEnvironment() extends BaeldungEnvironment {override val name: String = "staging"}

    case class IntEnvironment() extends BaeldungEnvironment {override val name: String = "int"}

    case class TestEnvironment() extends BaeldungEnvironment {override val name: String = "test"}

    def fromEnvString(env: String): Option[BaeldungEnvironment] = {
      env.toLowerCase match {
        case "int" => Some(IntEnvironment())
        case "staging" => Some(StagingEnvironment())
        case "production" => Some(ProductionEnvironment())
        case "test" => Some(TestEnvironment())
        case e => None
      }
    }
  }

}
