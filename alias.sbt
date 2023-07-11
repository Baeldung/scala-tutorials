addCommandAlias("specialTests", "testOnly com.baeldung.scala.scalatest.runner.ScalaTestRunnerTests -- -z \"spaces\" -n \"BooleanTests\"")

addCommandAlias("compileAndRunSpecialTest","compile;specialTests")