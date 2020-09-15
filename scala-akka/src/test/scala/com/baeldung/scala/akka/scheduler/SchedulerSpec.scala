package com.baeldung.scala.akka.scheduler

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest.{Matchers, WordSpecLike}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class SchedulerSpec
    extends TestKit(ActorSystem("test-system"))
    with ImplicitSender
    with WordSpecLike
    with Matchers {

  "Akka scheduler" must {

    "execute the task exactly once after the provided time" in {
      val greeter = system.actorOf(Props(classOf[Greetings]))
      val greet = Greet("Detective", "Lucifer")
      system.scheduler.scheduleOnce(500.millis, greeter, greet)
      val expectedMessage = Greeted("Lucifer: Hello, Detective")
      expectMsg(1.seconds, expectedMessage)
      //should not get anymore message
      expectNoMessage(1.seconds)
      system.stop(greeter)
    }

    "execute the task exactly once using Runnable interface" in {
      val greeter =
        system.actorOf(Props(classOf[Greetings]), "Greeter-With-Runnable")
      val greet = Greet("Detective", "Lucifer")
      system.scheduler.scheduleOnce(500.millis, new Runnable {
        override def run(): Unit = greeter ! greet
      })

      val expectedMessage = Greeted("Lucifer: Hello, Detective")
      expectMsg(1.seconds, expectedMessage)
      //should not get anymore message
      expectNoMessage(1.seconds)
      system.stop(greeter)
    }

    "execute a task periodically" in {
      val greeter =
        system.actorOf(Props(classOf[Greetings]), "Periodic-Greeter")
      val greet = Greet("Detective", "Lucifer")
      system.scheduler.schedule(10.millis, 250.millis, greeter, greet)

      val expectedMessage = Greeted("Lucifer: Hello, Detective")
      expectMsg(300.millis, expectedMessage)
      //get another message after 500 millis
      expectMsg(300.millis, expectedMessage)
      expectMsg(300.millis, expectedMessage)
      system.stop(greeter)
    }

    "execute a task periodically using scheduleWithFixedDelay" in {
      val greeter =
        system.actorOf(
          Props(classOf[Greetings]),
          "Periodic-Greeter-Fixed-Delay"
        )
      val greet = Greet("Detective", "Lucifer")
      system.scheduler.scheduleWithFixedDelay(
        10.millis,
        250.millis,
        greeter,
        greet
      )

      val expectedMessage = Greeted("Lucifer: Hello, Detective")
      expectMsg(300.millis, expectedMessage)
      //get another message after 500 millis
      expectMsg(300.millis, expectedMessage)
      expectMsg(300.millis, expectedMessage)
      system.stop(greeter)
    }

    "execute a task periodically using Runnable interface" in {
      val greeter =
        system.actorOf(Props(classOf[Greetings]), "Periodic-Greeter-Runnable")
      val greet = Greet("Detective", "Lucifer")
      system.scheduler.schedule(10.millis, 250.millis, new Runnable {
        override def run(): Unit = greeter ! greet
      })

      val expectedMessage = Greeted("Lucifer: Hello, Detective")
      expectMsg(300.millis, expectedMessage)
      //get another message after 500 millis
      expectMsg(300.millis, expectedMessage)
      expectMsg(300.millis, expectedMessage)
      system.stop(greeter)
    }

    "cancel a running scheduler" in {
      val greeter =
        system.actorOf(Props(classOf[Greetings]), "Cancelling-Greeter")
      val greet = Greet("Detective", "Lucifer MorningStar")
      val schedulerInstance =
        system.scheduler.schedule(10.millis, 1.seconds, greeter, greet)

      val expectedMessage = Greeted("Lucifer MorningStar: Hello, Detective")
      expectMsg(350.millis, expectedMessage)
      //Cancel the schedule, should not get any more messages after that
      schedulerInstance.cancel()
      schedulerInstance.isCancelled shouldBe true
      expectNoMsg(1.seconds)

    }

    "scheduleAtFixedRate should run the next execution at fixed rate even if the previous task took more time" in {
      val greeter =
        system.actorOf(Props(classOf[Greetings]), "Fixed-Rate-Scheduling")
      val greet = Greet("Detective", "Lucifer")
      var flag = true
      system.scheduler.scheduleAtFixedRate(10.millis, 250.millis)(new Runnable {
        override def run(): Unit = {
          if (flag)
            Thread.sleep(200)
          flag = false
          greeter ! greet
        }
      })

      val expectedMessage = Greeted("Lucifer: Hello, Detective")
      expectMsg(250.millis, expectedMessage)
      //get the next message in 50 millis
      expectMsg(50.millis, expectedMessage)
      system.stop(greeter)
    }

  }

}
