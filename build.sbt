val dottyVersion = "3.0.0-M1"


val excludeSourcecode = ExclusionRule(organization = "com.lihaoyi", name = "sourcecode_2.13")
lazy val root = project
  .in(file("."))
  .settings(
    name := "dotty-simple",
    version := "0.1.0",
    scalaVersion := dottyVersion,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
    libraryDependencies ++= List(
      "ch.epfl.lamp" %% "dotty-compiler" % scalaVersion.value
    )
  )
