val dottyVersion = "3.1.3"
val parserVersion = "2.13.8"

val pprintVersion = "0.7.3"
val MUnitFramework = new TestFramework("munit.Framework")

lazy val dotty = project
  .in(file("dotty"))
  .settings(
    name := "dotty-parsing",
    scalaVersion := dottyVersion,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
    libraryDependencies += "io.get-coursier" % "interface" % "1.0.1",
    libraryDependencies ++= List(
      "org.scala-lang" %% "scala3-compiler" % scalaVersion.value,
      "com.lihaoyi" %% "pprint" % pprintVersion
    )
  )

lazy val scalameta = project
  .in(file("scalameta"))
  .settings(
    name := "scalameta-parsing",
    scalaVersion := parserVersion,
    resolvers += Resolver.sonatypeRepo("public"),
    testFrameworks := List(MUnitFramework),
    libraryDependencies ++= List(
      "org.scalameta" %% "scalameta" % "4.7.1",
      "org.scalameta" %% "munit" % "0.7.19" % Test,
      "com.lihaoyi" %% "pprint" % pprintVersion
    )
  )
