package com.baeldung.scala.akka.typed

import akka.actor.testkit.typed.Effect.Spawned
import akka.actor.testkit.typed.scaladsl.{BehaviorTestKit, TestInbox}
import com.baeldung.scala.akka.typed.PortfolioApplication.Bank.{CreatePortfolio, PortfolioCreated}
import com.baeldung.scala.akka.typed.PortfolioApplication.BankMain.Start
import com.baeldung.scala.akka.typed.PortfolioApplication.PortfolioActor.{Buy, PortfolioCommand}
import com.baeldung.scala.akka.typed.PortfolioApplication._
import org.scalatest.flatspec.AnyFlatSpec

class PortfolioApplicationUnitTest extends AnyFlatSpec {

  "A Stock" should "let you add some quantity to actual" in {
    val appleStock = Stock("AAPL", 1000L)
    assertResult(Stock("AAPL", 1500L)) {
      appleStock.buy(500L)
    }
  }

  it should "let you sell a quantity that you actually own" in {
    val appleStock = Stock("AAPL", 1000L)
    assertResult(Stock("AAPL", 500)) {
      appleStock.sell(500L)
    }
  }

  it should "not let you sell a quantity that you do not own" in {
    val appleStock = Stock("AAPL", 1000L)
    assertResult(Stock("AAPL", 1000L)) {
      appleStock.sell(1500L)
    }
  }

  "A Portfolio" should "add a new bought stock to the owned stocks list" in {
    val emptyPortfolio = Portfolio(Map.empty)
    assertResult(Portfolio(Map("AAPL" -> Stock("AAPL", 500L)))) {
      emptyPortfolio.buy("AAPL", 500L)
    }
  }

  it should "add a quantity for a stock that is already owned" in {
    val applePortfolio = Portfolio(Map("AAPL" -> Stock("AAPL", 500L)))
    assertResult(Portfolio(Map("AAPL" -> Stock("AAPL", 1500L)))) {
      applePortfolio.buy("AAPL", 1000L)
    }
  }

  it should "sell stocks that are owned" in {
    val applePortfolio = Portfolio(Map("AAPL" -> Stock("AAPL", 1000L)))
    assertResult(Portfolio(Map("AAPL" -> Stock("AAPL", 700L)))) {
      applePortfolio.sell("AAPL", 300L)
    }
  }

  it should "not sell more stocks than owned" in {
    val applePortfolio = Portfolio(Map("AAPL" -> Stock("AAPL", 1000L)))
    assertResult(Portfolio(Map("AAPL" -> Stock("AAPL", 1000L)))) {
      applePortfolio.sell("AAPL", 2000L)
    }
  }

  "The BankMain" should "create a new Bank" in {
    val testKit = BehaviorTestKit(BankMain())
    val bank = testKit.expectEffectType[Spawned[CreatePortfolio]]
    assertResult("bank") {
      bank.childName
    }
  }

  it should "create a new client for every received Start message" in {
    val testKit = BehaviorTestKit(BankMain())
    testKit.run(Start("Alice"))
    testKit.expectEffectType[Spawned[CreatePortfolio]]
    val client = testKit.expectEffectType[Spawned[PortfolioCreated]]
    assertResult("Alice") {
      client.childName
    }
  }

  "The Bank" should "create a new portfolio for a client" in {
    val clientInbox = TestInbox[PortfolioCreated]()
    val testKit = BehaviorTestKit(Bank())
    testKit.run(CreatePortfolio(clientInbox.ref))
    val portfolio = testKit.expectEffectType[Spawned[PortfolioCommand]]
    clientInbox.expectMessage(PortfolioCreated(portfolio.ref))
  }

  "The BankClientUsingTheTellPattern" should "ask the bank to create a new portfolio on creation" in {
    val bankInbox = TestInbox[CreatePortfolio]()
    val testKit = BehaviorTestKit(BankClientUsingTheTellPattern(bankInbox.ref))
    bankInbox.expectMessage(CreatePortfolio(testKit.ref))
  }

  it should "ask to buy AAPL stocks to an empty portfolio" in {
    val portfolioInbox = TestInbox[PortfolioCommand]()
    val bankInbox = TestInbox[CreatePortfolio]()
    val testKit = BehaviorTestKit(BankClientUsingTheTellPattern(bankInbox.ref))
    testKit.run(PortfolioCreated(portfolioInbox.ref))
    portfolioInbox.expectMessage(Buy("AAPL", 100L))
  }
}
