import org.http4s.HttpRoutes
import org.http4s.dsl.io._
import cats.effect._

val helloWorldService = HttpRoutes.of[IO] { case GET -> Root / "hello" =>
  Ok("Hello, World!")
}
