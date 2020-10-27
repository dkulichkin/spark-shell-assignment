name := "SparkExample"

version := "0.0.1"

scalaVersion := "2.12.12"
val sparkVersion = "3.0.1"

libraryDependencies += "org.postgresql" % "postgresql" % "42.2.18"
libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion % Provided
libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion % Provided
libraryDependencies += "com.github.mrpowers" %% "spark-fast-tests" % "0.21.3" % Test
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % Test
libraryDependencies += "com.github.scopt" %% "scopt" % "3.7.1" % Compile

// test suite settings
fork in Test := true
javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled")


// test run settings
//parallelExecution in Test := false
assembly / test := {}

// Enable integration tests
// Defaults.itSettings
// lazy val root = project.in(file(".")).configs(IntegrationTest)

// Measure time for each test
Test / testOptions += Tests.Argument("-oD")
// IntegrationTest / testOptions += Tests.Argument("-oD")


// don't include Scala in the JAR file
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
artifactName := { (sv: ScalaVersion, module: ModuleID, artifact: Artifact) =>
  artifact.name + "_" + sv.binary + "-" + sparkVersion + "_" + module.revision + "." + artifact.extension
}
