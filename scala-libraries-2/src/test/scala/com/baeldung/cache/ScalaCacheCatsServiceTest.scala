package com.baeldung.cache

import com.baeldung.cache.service.{CatsService, GuavaCacheCatsConfig}
import org.scalatest.BeforeAndAfterEach
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import scala.collection.JavaConverters._

class ScalaCacheCatsServiceTest
  extends AnyWordSpec
  with Matchers
  with BeforeAndAfterEach {

  override def beforeEach(): Unit = {
    GuavaCacheCatsConfig.underlyingGuavaCacheCats.invalidateAll()
  }

  "ScalaCache with Cats api" should {
    "use the cats mode and cache the result successfully for a pure IO" in {
      import GuavaCacheCatsConfig._
      val service = new CatsService()
      val result = service.getUserPure(22)
      GuavaCacheCatsConfig.underlyingGuavaCacheCats.size() shouldBe 0
      result.unsafeRunSync()
      GuavaCacheCatsConfig.underlyingGuavaCacheCats.size() shouldBe 1
      GuavaCacheCatsConfig.underlyingGuavaCacheCats
        .asMap()
        .keySet()
        .asScala shouldBe
        Set("com.baeldung.cache.service.CatsService.getUserPure(22)")
      service.count shouldBe 1
      val result2 = service.getUserPure(22).unsafeRunSync()
      service.count shouldBe 1
      GuavaCacheCatsConfig.underlyingGuavaCacheCats.size() shouldBe 1

    }

    "defer caching operation till the execution" in {
      val service = new CatsService()
      val result = service.getUserCatsIO(500)
      GuavaCacheCatsConfig.underlyingGuavaCacheCats.size() shouldBe 0
      result.unsafeRunSync()
      GuavaCacheCatsConfig.underlyingGuavaCacheCats.size() shouldBe 1
    }

    "use the cats mode and cache the result successfully for an IO " in {
      import GuavaCacheCatsConfig._
      val service = new CatsService()
      val result = service.getUserIO(5)
      GuavaCacheCatsConfig.underlyingGuavaCacheCats.size() shouldBe 0
      result.unsafeRunSync()
      GuavaCacheCatsConfig.underlyingGuavaCacheCats.size() shouldBe 1
      GuavaCacheCatsConfig.underlyingGuavaCacheCats
        .asMap()
        .keySet()
        .asScala shouldBe
        Set("com.baeldung.cache.service.CatsService.getUserIO(5)")
      service.count shouldBe 1
      val result2 = service.getUserIO(5).unsafeRunSync()
      service.count shouldBe 1
      GuavaCacheCatsConfig.underlyingGuavaCacheCats.size() shouldBe 1

    }
  }

}
