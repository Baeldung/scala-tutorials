addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.0")

addSbtPlugin("org.scala-native" % "sbt-scala-native" % "0.4.10")
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.13.0")

addSbtPlugin("com.lightbend.akka.grpc" % "sbt-akka-grpc" % "2.2.1")
libraryDependencies += "ai.kien" %% "python-native-libs" % "0.2.4"
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.19")
ThisBuild / libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always

addSbtPlugin("com.typesafe.sbt" % "sbt-twirl" % "1.4.2")
addSbtPlugin("com.earldouglas" % "xsbt-web-plugin" % "4.2.1")

addSbtPlugin("pl.project13.scala" % "sbt-jmh" % "0.4.3")
libraryDependencies += "org.scala-js" %% "scalajs-env-jsdom-nodejs" % "1.0.0"

resolvers += Resolver.jcenterRepo

addSbtPlugin("net.aichler" % "sbt-jupiter-interface" % "0.8.3")
