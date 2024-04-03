package com.baeldung.scala.zio.prelude.voting

import zio.prelude._

object Voting {

  object Votes extends Subtype[Int] {
    implicit val associativeVotes: Associative[Votes] =
      new Associative[Votes] {
        override def combine(l: => Votes, r: => Votes): Votes =
          Votes(l + r)
      }
  }

  private type Votes = Votes.Type

  object Topic extends Subtype[String]

  private type Topic = Topic.Type

  final case class VoteState(map: Map[Topic, Votes]) {
    self =>
    def combine(that: VoteState): VoteState =
      VoteState(self.map combine that.map)

  }

}
