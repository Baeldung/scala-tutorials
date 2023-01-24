package com.baeldung.scala.concurrency

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.freespec.AsyncFreeSpecLike
import org.scalatest.matchers.should.Matchers

class FutureAndPromiseUnitTest extends AsyncFreeSpecLike with Matchers with ScalaFutures {
  import ScalaAndPromise._

  "sampleFuture() should return 5" in {
    sampleFuture().map(_ shouldBe 5)
  }

  "createUser() should return a Future containing the new User" in {
    createUser("John", "John@emaple.com", "iLoveScala").map { user =>
      user.name shouldBe "John"
      user.email shouldBe "John@emaple.com"
      user.password shouldBe "685db2b1e0e627942eca56bdf16909d7"
      user.avatar shouldBe
        new Avatar("http://avatar.example.com/user/23k520f23f4.png")
    }
  }

  "runByPromise() should return a Future containing the result of computation" in {
    runByPromise(5).map(_ shouldBe 5)
  }
}

