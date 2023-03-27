package com.baeldung.scala3.exportclause

class CacheImpl2 {
  private val cache: collection.mutable.Map[String, Int] =
    collection.mutable.Map.empty
  def getFromCache(key: String): Option[Int] = cache.get(key)
  def getKeys: List[String] = cache.keys.toList
  def clearCache: Unit = cache.clear()
  def save(key:String, value:Int) = cache.put(key, value)
  def test = "hello"
}

class CacheImpl {
  private val cache: collection.mutable.Map[String, Int] =
    collection.mutable.Map.empty
  def getFromCache(key: String): Option[Int] = cache.get(key)
  def clear: Unit = cache.clear()
}
class CacheService {
  private val cacheImpl = new CacheImpl
  export cacheImpl.getFromCache as get
}

class Usage {
  val service = new CacheService
  service.get("key")
}
class CacheService2(private val cacheImpl: CacheImpl2) {
  //export cacheImpl.getKeys
  export cacheImpl.*
  export cacheImpl.test as _
  //consider override error
  //def getFromCache(key: String) = cacheImpl.getFromCache(key)
  //def save(key: String, value:Int) = cacheImpl.save(key, value)
  export cacheImpl.getFromCache as get
  private def mytest = test
}

object ExportTest extends App {
  val service = new CacheService2(new CacheImpl2)
  service.save("k1", 100)
  service.save("k2", 200)
  println(service.getFromCache("k2"))
  println(service.getKeys)
  println(service.get("k2"))
  service.test

}


class Foo {
  def test = "hello"
  def test2 = "hello 2"
}
class Bar {
  private val foo = new Foo
  export foo.{test2 as _, *}
}
class FooBar {
  val bar = new Bar
  bar.test
  bar.test2
}