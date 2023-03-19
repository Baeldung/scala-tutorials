package com.baeldung.scala3.exportclause

class CacheImpl {
  private val cache: collection.mutable.Map[String, Int] =
    collection.mutable.Map.empty
  def getFromCache(key: String): Option[Int] = cache.get(key)
  def getKeys: List[String] = cache.keys.toList
  def clearCache: Unit = cache.clear()
  def save(key:String, value:Int) = cache.put(key, value)
  def test = "hello"
}

class CacheService(private val cacheImpl: CacheImpl) {
  //export cacheImpl.getKeys
  export cacheImpl.test as _
  export cacheImpl.*
  //consider override error
  //def getFromCache(key: String) = cacheImpl.getFromCache(key)
  //def save(key: String, value:Int) = cacheImpl.save(key, value)
  export cacheImpl.getFromCache as get
  private def mytest = "Hello"
}

object ExportTest extends App {
  val service = new CacheService(new CacheImpl)
  service.save("k1", 100)
  service.save("k2", 200)
  println(service.getFromCache("k2"))
  println(service.getKeys)
  println(service.get("k2"))
  service.test

}
