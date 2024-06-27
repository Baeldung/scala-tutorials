import ReleaseTransformations.*

lazy val root = (project in file("."))
  .settings(
    name := "sbt-release"
  )

releaseVersionFile := file("version/version.sbt")
releaseCrossBuild := true
releaseUseGlobalVersion := false
releaseVersionBump := sbtrelease.Version.Bump.NextStable
publish / skip := true

releaseTagComment        := s"Releasing ${(ThisBuild / version).value} using sbt-release"
releaseCommitMessage     := s"Setting version to ${(ThisBuild / version).value} using sbt-release"
releaseNextCommitMessage := s"Setting version to ${(ThisBuild / version).value} using sbt-release"

releaseNextVersion := (releaseVersion => releaseVersion.split("\\.") match {
  case Array(major, minor, bugfix) =>
    s"$major.$minor.${bugfix.toInt + 1}"
})

val customReleaseStep = ReleaseStep(action = step => {
  val extracted = Project.extract(step)
  val v = extracted.get(Keys.version)
  println(s"Custom release step that prints the new version: $v")
  step
})
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,              // : ReleaseStep
  inquireVersions,                        // : ReleaseStep
  runClean,                               // : ReleaseStep
  runTest,                                // : ReleaseStep
  setReleaseVersion,                      // : ReleaseStep
  commitReleaseVersion,                   // : ReleaseStep, performs the initial git checks
  tagRelease,                             // : ReleaseStep
  //  publishArtifacts,                       // : ReleaseStep, checks whether `publishTo` is properly set up
  setNextVersion,                         // : ReleaseStep
  commitNextVersion,                      // : ReleaseStep
  customReleaseStep
  //  pushChanges                             // : ReleaseStep, also checks that an upstream branch is properly configured
)
