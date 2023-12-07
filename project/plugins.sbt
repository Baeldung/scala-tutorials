addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.0")

addSbtPlugin("org.scala-native" % "sbt-scala-native" % "0.4.16")
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.13.0")

addSbtPlugin("com.lightbend.akka.grpc" % "sbt-akka-grpc" % "2.2.1")
libraryDependencies += "ai.kien" %% "python-native-libs" % "0.2.4"

// Make sure to keep the play plugin in sync with the sub modules under play-scala group
addSbtPlugin("org.playframework" % "sbt-plugin" % "3.0.0")

addSbtPlugin("org.playframework.twirl" % "sbt-twirl" % "2.0.1")
addSbtPlugin("com.earldouglas" % "xsbt-web-plugin" % "4.2.1")

addSbtPlugin("pl.project13.scala" % "sbt-jmh" % "0.4.3")
libraryDependencies += "org.scala-js" %% "scalajs-env-jsdom-nodejs" % "1.0.0"

resolvers += Resolver.jcenterRepo

addSbtPlugin("net.aichler" % "sbt-jupiter-interface" % "0.8.3")

ThisBuild / libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
