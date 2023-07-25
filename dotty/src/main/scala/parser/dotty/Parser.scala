package parser.dotty

import dotty.tools.dotc.interactive.InteractiveDriver
import dotty.tools.dotc.util.SourceFile
import dotty.tools.dotc.core.Contexts.Context
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.Path
import java.io.File
import scala.collection.mutable.ListBuffer
import scala.jdk.CollectionConverters._

import coursierapi.Dependency
import coursierapi.Fetch

object Parser {

  def main(args: Array[String]): Unit = {
  
    val fetch = Fetch.create()

    fetch.addDependencies(Dependency.of("org.scala-lang", "scala3-library_3", "3.1.3"))
    val extraLibraries: Seq[Path] = fetch
      .fetch()
      .asScala
      .map(_.toPath()).toSeq

    val driver = new InteractiveDriver(
      List("-Ykind-projector", "-color:never", "-classpath", extraLibraries.mkString(File.pathSeparator))
    )

    val sourceCode = Files.readAllLines(Paths.get("./Source.stat")).asScala.mkString("\n")
    val filename = "Section.scala"

    val diags = driver.run(
      java.net.URI.create(s"file:///$filename"),
      SourceFile.virtual(filename, sourceCode)
    )
    val tree = driver.currentCtx.run.units.head.untpdTree
    pprint.log(tree)
    pprint.log(diags)
    pprint.log(tree.show(using driver.currentCtx))
  }


}
