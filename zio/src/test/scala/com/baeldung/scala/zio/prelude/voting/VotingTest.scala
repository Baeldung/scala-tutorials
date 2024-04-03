package com.baeldung.scala.zio.prelude.voting

import org.scalatest.wordspec.AnyWordSpec

class VotingTest extends AnyWordSpec {

  import Voting._

  "a voting object" should {
    "correctly combine vote states" in {
      val zioHttp = Topic("zio-http")
      val uziHttp = Topic("uzi-http")
      val zioTlsHttp = Topic("zio-tls-http")

      val leftVotes = VoteState(Map(zioHttp -> Votes(4), uziHttp -> Votes(2)))
      val rightVotes =
        VoteState(Map(zioHttp -> Votes(2), zioTlsHttp -> Votes(2)))

      val totalVotes = leftVotes combine rightVotes

      assertResult(Votes(6))(totalVotes.map(zioHttp))
      assertResult(Votes(2))(totalVotes.map(uziHttp))
      assertResult(Votes(2))(totalVotes.map(zioTlsHttp))
    }
  }
}
