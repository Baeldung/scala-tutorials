package com.baeldung.arrival.actions

import com.baeldung.arrival.controller.action.SourceActions
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.wordspec.AnyWordSpec
import play.api.http.Status.{BAD_REQUEST, NO_CONTENT}
import play.api.mvc.Headers
import play.api.mvc.Results.NoContent
import play.api.test.{FakeRequest, Helpers}

class SourceActionsUnitTest
  extends AnyWordSpec
  with SourceActions
  with ScalaFutures {

  private def anyContentParser =
    Helpers.stubControllerComponents().parsers.anyContent

  private def globalEc = scala.concurrent.ExecutionContext.Implicits.global

  "SourceAction" should {
    "return BAD_REQUEST status for missing source header" in {
      val testee = SourceAction(anyContentParser)(globalEc) { _ => NoContent }

      whenReady(testee.apply(FakeRequest())) { result =>
        assert(result.header.status === BAD_REQUEST)
      }
    }

    "return NO_CONTENT status for when source header is present" in {
      val testee = SourceAction(anyContentParser)(globalEc) { _ => NoContent }
      whenReady(
        testee.apply(FakeRequest().withHeaders(Headers("source" -> "foo")))
      ) { result =>
        assert(result.header.status === NO_CONTENT)
      }
    }
  }

}
