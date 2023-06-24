import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.2.11"
  lazy val redisClients = "redis.clients" % "jedis" % "4.3.1"
  lazy val jacksonScala =
    "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.14.1"
  lazy val mockito = "org.scalatestplus" %% "mockito-3-4" % "3.2.10.0"
}
