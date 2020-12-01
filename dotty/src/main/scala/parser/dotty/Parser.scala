package parser.dotty

import dotty.tools.dotc.interactive.InteractiveDriver
import dotty.tools.dotc.util.SourceFile
import dotty.tools.dotc.core.Contexts.Context
import java.nio.file.Files
import java.nio.file.Paths
import java.io.File
import scala.collection.mutable.ListBuffer
import scala.jdk.CollectionConverters._

object Parser {

  def main(args: Array[String]): Unit = {

    val classpath = Array(
      "/home/tgodzik/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.2/scala-library-2.13.2.jar",
      "/home/tgodzik/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala3-library_3.0.0-M1/3.0.0-M1/scala3-library_3.0.0-M1-3.0.0-M1.jar"
    )
    val driver = new InteractiveDriver(
      List("-color:never", "-classpath", classpath.mkString(File.pathSeparator))
    )

    val sourceCode = Files.readAllLines(Paths.get("./Source.stat")).asScala.mkString("\n")
    val filename = "Section.scala"

    driver.run(
      java.net.URI.create(s"file:///$filename"),
      SourceFile.virtual(filename, sourceCode)
    )
    val tree = driver.currentCtx.run.units.head.untpdTree
    pprint.log(tree)
  }


}
