val dottyVersion = "3.0.0-M2"
val parserVersion = "2.13.4"

val pprintVersion = "0.6.0"
val MUnitFramework = new TestFramework("munit.Framework")

lazy val dotty = project
  .in(file("dotty"))
  .settings(
    name := "dotty-parsing",
    scalaVersion := dottyVersion,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
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
      "org.scalameta" %% "scalameta" % "4.4.0+21-c5c28796-SNAPSHOT",
      "org.scalameta" %% "munit" % "0.7.19" % Test,
      "com.lihaoyi" %% "pprint" % pprintVersion
    )
  )
