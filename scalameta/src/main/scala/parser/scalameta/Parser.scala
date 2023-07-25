package parser.scalameta

import java.nio.file.Files
import java.nio.file.Paths
import java.io.File
import scala.collection.mutable.ListBuffer
import scala.jdk.CollectionConverters._
import scala.meta._
import scala.collection.immutable
import scala.meta.internal.semanticdb.SymbolInformation.{Property => p}

object Parser {

  def main(args: Array[String]): Unit = {

    val sourceCode = Files.readAllLines(Paths.get("./Source.stat")).asScala.mkString("\n")

    implicit val dialect = scala.meta.dialects.Scala3.withAllowFewerBraces(true)
    val parsed = sourceCode.parse[Source]
    pprint.log(p.values.last.value << 1)
    println(parsed.get.syntax)
    parsed.get.children match {
      case  (t @ Type.Function(params,_)) :: next => 
      case _ =>
    }
  }


}
