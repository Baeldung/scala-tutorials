name := "scala-test-junit-5"
organization := "com.baeldung"

version := "1.0-SNAPSHOT"

scalaVersion := "2.13.12"

resolvers += Resolver.jcenterRepo

libraryDependencies += "net.aichler" % "jupiter-interface" % JupiterKeys.jupiterVersion.value % Test
