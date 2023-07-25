import ai.kien.python.Python
lazy val python = Python()

//enable this for running on scala-native. Also need to use %%% on scalapy dependency for using scalanative
//enablePlugins(ScalaNativePlugin)
//nativeLinkStubs := true
//lazy val pythonLdFlags = python.ldflags.get
//nativeLinkingOptions ++= pythonLdFlags

lazy val javaOpts = python.scalapyProperties.get.map { case (k, v) =>
  s"""-D$k=$v"""
}.toSeq

javaOptions ++= javaOpts
