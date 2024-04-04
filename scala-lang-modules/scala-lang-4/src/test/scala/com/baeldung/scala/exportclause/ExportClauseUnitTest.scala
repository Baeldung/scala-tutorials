package com.baeldung.scala.exportclause

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.concurrent.duration.*

object TestScenario1 {
  class CacheImpl {
    private val cache: collection.mutable.Map[String, Int] =
      collection.mutable.Map.empty
    def getFromCache(key: String): Option[Int] = cache.get(key)
    def clear(): Unit = cache.clear()
  }
  class CacheService {
    private val cacheImpl = new CacheImpl
    export cacheImpl.getFromCache
  }
  class CacheServiceWithRename {
    private val cacheImpl = new CacheImpl
    export cacheImpl.getFromCache as get
  }
  class CacheServiceWithWildcard {
    private val cacheImpl = new CacheImpl
    export cacheImpl.*
  }
  class CacheServiceWithWildcardWithExclusion {
    private val cacheImpl = new CacheImpl
    export cacheImpl.{clear as _, *}
  }
}

object TestScenario2 {
  class CacheImpl {
    given timeout: FiniteDuration = 10.millis
    given myStr: String = "Hello"
  }
  class CacheService {
    private val cacheImpl = new CacheImpl
    export cacheImpl.given
  }
  class CacheServiceSelectively {
    private val cacheImpl = new CacheImpl
    export cacheImpl.given FiniteDuration
  }
}

object TestScenario3 {
  class CacheImpl {
    private val cache: collection.mutable.Map[String, Int] =
      collection.mutable.Map.empty
    def getFromCache(key: String): Option[Int] = cache.get(key)
    def getFromCache(key: String, category: String): Option[Int] =
      cache.get(key)
  }
  class CacheService {
    private val cacheImpl = new CacheImpl
    export cacheImpl.*
  }
  class CacheServiceWithRename {
    private val cacheImpl = new CacheImpl
    export cacheImpl.getFromCache as get
  }
}

class ExportClauseUnitTest extends AnyFlatSpec with Matchers {
  it should "export the selected member from CacheImpl" in {
    val service = new TestScenario1.CacheService
    """service.getFromCache("Key")""" should compile
    """service.clear()""" shouldNot compile
  }
  it should "export and rename a member" in {
    val service = new TestScenario1.CacheServiceWithRename
    """service.get("Key")""" should compile
    """service.getFromCache("key")""" shouldNot compile
  }
  it should "export all given instances" in {
    val service = new TestScenario2.CacheService
    service.timeout shouldBe 10.millis
    service.myStr shouldBe "Hello"
  }

  it should "export selectively given instances" in {
    val service = new TestScenario2.CacheServiceSelectively
    service.timeout shouldBe 10.millis
    "service.myStr" shouldNot compile
  }

  it should "export all members using wildcard export" in {
    val service = new TestScenario1.CacheServiceWithWildcard
    """service.getFromCache("Key")""" should compile
    """service.clear()""" should compile
  }

  it should "exclude some members from wildcard export" in {
    val service = new TestScenario1.CacheServiceWithWildcardWithExclusion
    """service.getFromCache("Key")""" should compile
    """service.clear()""" shouldNot compile
  }

  it should "export members correctly when there are overloaded members" in {
    val service = new TestScenario3.CacheService
    """service.getFromCache("Key")""" should compile
    """service.getFromCache("Key", "category")""" should compile
  }

  it should "export members correctly when there are overloaded members that are renamed" in {
    val service = new TestScenario3.CacheServiceWithRename
    """service.get("Key")""" should compile
    """service.get("Key", "category")""" should compile
  }

}
