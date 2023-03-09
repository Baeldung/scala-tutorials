package com.baeldung.scala.options

object OptionExample extends App {

  def getTopScore(
    player: Player,
    tournament: Tournament
  ): Option[(Player, Int)] = {
    player.getFavoriteTeam
      .flatMap(tournament.getTopScore)
      .map(score => (player, score))
  }

  def whoHasTopScoringTeam(
    playerA: Player,
    playerB: Player,
    tournament: Tournament
  ): Option[(Player, Int)] = {
    getTopScore(playerA, tournament).foldRight(
      getTopScore(playerB, tournament)
    ) { case (playerAInfo, playerBInfo) =>
      playerBInfo
        .filter { case (_, scoreB) =>
          scoreB > playerAInfo._2
        }
        .orElse(Some(playerAInfo))
    }
  }

}
