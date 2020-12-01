package parser.scalameta

import java.nio.file.Files
import java.nio.file.Paths
import java.io.File
import scala.collection.mutable.ListBuffer
import scala.jdk.CollectionConverters._
import scala.meta._

object Parser {

  def main(args: Array[String]): Unit = {

    val sourceCode = Files.readAllLines(Paths.get("./Source.stat")).asScala.mkString("\n")

    implicit val dialect = scala.meta.dialects.Dotty
    val parsed = sourceCode.parse[Source]
    pprint.log(parsed)
  }


}
