val scalaTestDeps = Seq(
  "org.scalatest" %% "scalatest" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-wordspec" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-flatspec" % "3.2.16" % Test
)
val jUnitInterface = "com.github.sbt" % "junit-interface" % "0.13.3" % "test"
val scalaMock = "org.scalamock" %% "scalamock" % "5.2.0" % Test

val sparkVersion = "3.4.1"
val spireVersion = "0.18.0"
val kafkaVersion = "3.5.0"
val pureconfigVersion = "0.17.4"
val jackSonVersion = "2.15.1"
val log4jApiScalaVersion = "12.0"
val log4jVersion = "2.20.0"
val avro4sVersion = "3.1.1"
val kafkaAvroSerializer = "6.0.0"
val scalaV = "2.13.11"
scalaVersion := scalaV
name := "scala-libraries-5"
resolvers += "Kafka avro serializer" at "https://packages.confluent.io/maven"
scalaVersion := scalaV
val sparkCoreDep = "org.apache.spark" %% "spark-core" % sparkVersion
val sparkSqlDep = "org.apache.spark" %% "spark-sql" % sparkVersion
libraryDependencies ++= scalaTestDeps
libraryDependencies ++= Seq(
  sparkSqlDep,
  sparkCoreDep,
  "org.typelevel" %% "spire" % spireVersion,
  "org.apache.kafka" % "kafka-clients" % kafkaVersion,
  "com.github.pureconfig" %% "pureconfig" % pureconfigVersion,
  "com.fasterxml.jackson.core" % "jackson-databind" % jackSonVersion,
  "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % jackSonVersion,
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % jackSonVersion,
  "com.sksamuel.avro4s" %% "avro4s-core" % avro4sVersion,
  "io.confluent" % "kafka-avro-serializer" % kafkaAvroSerializer,
  "org.apache.logging.log4j" %% "log4j-api-scala" % log4jApiScalaVersion,
  "org.apache.logging.log4j" % "log4j-core" % log4jVersion % Runtime
)
