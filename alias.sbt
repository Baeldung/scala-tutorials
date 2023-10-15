addCommandAlias(
  "specialTests",
  "testOnly com.baeldung.scala.scalatest.runner.ScalaTestRunnerTests -- -z \"spaces\" -n \"BooleanTests\""
)

addCommandAlias("compileAndRunSpecialTest", "compile;specialTests")

addCommandAlias(
  "onlyUnitTests",
  """; set ThisBuild/Test/testOptions += Tests.Filter(t => t.endsWith("UnitTest")); test""".stripMargin
)
