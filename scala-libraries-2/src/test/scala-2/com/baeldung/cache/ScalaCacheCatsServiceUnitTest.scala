package com.baeldung.cache

import com.baeldung.cache.service.{CatsService, GuavaCacheCatsConfig}
import org.scalatest.BeforeAndAfterEach
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import scala.collection.JavaConverters._

class ScalaCacheCatsServiceUnitTest
  extends AnyWordSpec
  with Matchers
  with BeforeAndAfterEach {

  "ScalaCache with Cats api" should {
    "use the cats mode and cache the result successfully for a pure IO" in {
      val config = new GuavaCacheCatsConfig
      import config._
      val service = new CatsService(config)
      val result = service.getUserPure(22)
      config.underlyingGuavaCacheCats.size() shouldBe 0
      result.unsafeRunSync()
      config.underlyingGuavaCacheCats.size() shouldBe 1
      config.underlyingGuavaCacheCats
        .asMap()
        .keySet()
        .asScala shouldBe
        Set("com.baeldung.cache.service.CatsService.getUserPure(22)")
      service.count shouldBe 1
      val result2 = service.getUserPure(22).unsafeRunSync()
      service.count shouldBe 1
      config.underlyingGuavaCacheCats.size() shouldBe 1

    }

    "defer caching operation till the execution" in {
      val config = new GuavaCacheCatsConfig
      val service = new CatsService(config)
      val result = service.getUserCatsIO(500)
      config.underlyingGuavaCacheCats.size() shouldBe 0
      result.unsafeRunSync()
      config.underlyingGuavaCacheCats.size() shouldBe 1
    }

    "use the cats mode and cache the result successfully for an IO " in {
      val config = new GuavaCacheCatsConfig
      import config._
      val service = new CatsService(config)
      val result = service.getUserIO(5)
      config.underlyingGuavaCacheCats.size() shouldBe 0
      result.unsafeRunSync()
      config.underlyingGuavaCacheCats.size() shouldBe 1
      config.underlyingGuavaCacheCats
        .asMap()
        .keySet()
        .asScala shouldBe
        Set("com.baeldung.cache.service.CatsService.getUserIO(5)")
      service.count shouldBe 1
      val result2 = service.getUserIO(5).unsafeRunSync()
      service.count shouldBe 1
      config.underlyingGuavaCacheCats.size() shouldBe 1

    }
  }

}
