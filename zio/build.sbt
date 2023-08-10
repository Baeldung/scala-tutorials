name := "zio"
val zioVersion = "2.0.15"
val scalaV = "2.13.11"
scalaVersion := scalaV
libraryDependencies += "dev.zio" %% "zio" % zioVersion
libraryDependencies += "dev.zio" %% "zio-streams" % zioVersion
libraryDependencies += "dev.zio" %% "zio-test-sbt" % zioVersion % Test
testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
