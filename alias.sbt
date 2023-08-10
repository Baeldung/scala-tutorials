addCommandAlias(
  "specialTests",
  "testOnly com.baeldung.scala.scalatest.runner.ScalaTestRunnerTests -- -z \"spaces\" -n \"BooleanTests\""
)

addCommandAlias("compileAndRunSpecialTest", "compile;specialTests")

addCommandAlias(
  "ci",
  ";clean;compile;test:compile;it:compile;scalafmtCheckAll;test"
)

addCommandAlias(
  "integrationTests",
  """;set ThisBuild/IntegrationTest/testOptions += Tests.Filter(t => !t.endsWith("ManualTest") && !t.endsWith("LiveTest") ); it:test""".stripMargin
)

addCommandAlias(
  "ciFull",
  """;ci; set ThisBuild/IntegrationTest/testOptions += Tests.Filter(t => !t.endsWith("ManualTest") && !t.endsWith("LiveTest") ); it:test""".stripMargin
)

addCommandAlias(
  "manualTests",
  """;ci; set ThisBuild/IntegrationTest/testOptions += Tests.Filter(t => t.endsWith("ManualTest")); it:test""".stripMargin
)

addCommandAlias(
  "liveTests",
  """;ci; set ThisBuild/IntegrationTest/testOptions += Tests.Filter(t => t.endsWith("LiveTest")); it:test""".stripMargin
)
