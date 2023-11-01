import sys.process._

lazy val myProject = (project in file("."))
  .enablePlugins(BuildInfoPlugin)
  .settings(
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
    buildInfoPackage := "buindInfoArticle"
  )

resolvers += "my-internal-repo" at "https://my-artifact-repo/"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test"

buildInfoKeys ++= Seq[BuildInfoKey](
  // Add a build number. Automatically incremented every time the code is compiled.
  buildInfoBuildNumber,
  // Add resolvers
  resolvers,
  // Add library dependencies
  libraryDependencies,
  // Add custom version key and append timestamp to the version number
  BuildInfoKey.map(version) { case (k, v) =>
    "mycustom" + k.capitalize -> s"$v-${System.currentTimeMillis.toString}"
  },
  // Add a custom field with the team owning the package
  "ownerTeam" -> "BestTeam",
  // Add a custom field with the build size
  BuildInfoKey.action("buildSize") {
    "du -h -d 0 ." !!
  }
)

buildInfoOptions := Seq(
  BuildInfoOption.ToJson, // Add a toJson method to BuildInfo
  BuildInfoOption.ToMap, // Add a toMap method to BuildInfo
  BuildInfoOption.BuildTime, // Add timestamp values
  BuildInfoOption.Traits("com.baeldung.XMLTranformer")
)

//settings for scoverage

ThisBuild / coverageEnabled := true

ThisBuild / coverageExcludedFiles := """.*SlickTables;.*/scalaz/.*"""

ThisBuild / coverageExcludedPackages := "<empty>;.*CollectionMonoid.*;.*reactivemongo.*"
