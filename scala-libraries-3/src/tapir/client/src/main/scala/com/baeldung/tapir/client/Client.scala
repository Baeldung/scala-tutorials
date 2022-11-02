package com.baeldung.tapir.client

import com.baeldung.tapir.endpoint.{AnimalEndpoints, ErrorResponse, Kitten}
import sttp.client3._
import sttp.model.StatusCode
import sttp.tapir.DecodeResult
import sttp.tapir.client.sttp.SttpClientInterpreter

object GetClient extends App {

  val kittenRequest: Unit => Request[DecodeResult[Either[String, List[Kitten]]], Any] =
    SttpClientInterpreter()
      .toRequest(AnimalEndpoints.kittens, Some(uri"http://localhost:11223"))

  val backend = HttpClientSyncBackend()
  val kittensR: RequestT[Identity, String, Any] = kittenRequest().response(asStringAlways)
  val kittensResponse = kittensR.send(backend)
  println(s"kittens: ${kittensResponse.body}")
}

object ClientPost extends App {

  val kittenPostRequest: Kitten => Request[DecodeResult[Either[(StatusCode, ErrorResponse), (StatusCode, Kitten)]], Any] =
    SttpClientInterpreter()
      .toRequest(AnimalEndpoints.kittensPost, Some(uri"http://localhost:11223"))

  val backend = HttpClientSyncBackend()

  val chaseR: RequestT[Identity, String, Any] = kittenPostRequest(Kitten(12L, "chase", "male", 14)).response(asStringAlways)

  val chaseResponse = chaseR.send(backend)

  println(s"chase: ${chaseResponse.body}")
}
