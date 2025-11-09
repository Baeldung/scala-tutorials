addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.6")

addSbtPlugin("org.scala-native" % "sbt-scala-native" % "0.5.9")
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.20.1")

addSbtPlugin("com.lightbend.akka.grpc" % "sbt-akka-grpc" % "2.2.1")
libraryDependencies += "ai.kien" %% "python-native-libs" % "0.2.5"

// Make sure to keep the play plugin in sync with the sub modules under play-scala group
addSbtPlugin("org.playframework" % "sbt-plugin" % "3.0.9")

addSbtPlugin("org.playframework.twirl" % "sbt-twirl" % "2.0.1")
addSbtPlugin("com.earldouglas" % "xsbt-web-plugin" % "4.2.1")

addSbtPlugin("pl.project13.scala" % "sbt-jmh" % "0.4.8")
libraryDependencies += "org.scala-js" %% "scalajs-env-jsdom-nodejs" % "1.1.1"

resolvers += Resolver.jcenterRepo

addSbtPlugin("net.aichler" % "sbt-jupiter-interface" % "0.11.1")

ThisBuild / libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
