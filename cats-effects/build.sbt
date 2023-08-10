name := "cats-effects"
val catsEffect = "org.typelevel" %% "cats-effect" % "3.5.1"
libraryDependencies += catsEffect
libraryDependencies += "org.typelevel" %% "munit-cats-effect-3" % "1.0.7" % Test
libraryDependencies += "junit" % "junit" % "4.13.2" % Test
val scalaV = "2.13.11"
scalaVersion := scalaV