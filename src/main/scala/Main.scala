import dotty.tools.dotc.interactive.InteractiveDriver
import dotty.tools.dotc.interactive.Interactive
import dotty.tools.dotc.interactive.InteractiveCompiler
import dotty.tools.dotc.Compiler
import dotty.tools.dotc.ast.Trees.PackageDef
import dotty.tools.dotc.ast.untpd._
import dotty.tools.dotc.ast.Trees
import dotty.tools.io.{AbstractFile, VirtualDirectory}
import dotty.tools.repl.AbstractFileClassLoader
import dotty.tools.dotc.util.SourceFile
import dotty.tools.dotc.util.ScriptSourceFile
import dotty.tools.dotc.core.Contexts.Context
import java.io.File
import java.net.URI
import java.net.URL
import java.net.URLClassLoader

// import dotty.tools.dotc.parsing.Parsers.Parser
// import dotty.tools.dotc.parsing.Scanners.Scanner
import scala.collection.mutable.ListBuffer

object Main {

  def main(args: Array[String]): Unit = {

    val classpath = Array(
      "/home/tgodzik/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.2/scala-library-2.13.2.jar",
      "/home/tgodzik/.cache/coursier/v1/https/repo1.maven.org/maven2/ch/epfl/lamp/dotty-library_0.25/0.25.0-RC2/dotty-library_0.25-0.25.0-RC2.jar"
    )
    val driver = new InteractiveDriver(
      List("-color:never", "-classpath", classpath.mkString(File.pathSeparator))
    )
    val sourceCode =
      """|
         |type Elem[X] = X match {
         |  case (a, b) => b
         |}
         |""".stripMargin
    val filename = "Section.scala"

    driver.run(
      java.net.URI.create("file:///Section.scala"),
      SourceFile.virtual(filename, sourceCode)
      )
      val source = driver.currentCtx.run.units.head.untpdTree

    println(source)
//     sourceFile
//     val buffer = ListBuffer.empty[Int]
//     val scanner = Scanner(sourceFile)(using context)
//     // new Scanner(sourceFile)
//     val script = ScriptSourceFile(sourceFile.file, sourceFile.content)
//     // driver
//     driver.run(java.net.URI.create("file:///Hello.scala"), script)

//     // Trees.Def
//     // println("Hellllo!")
//     println(driver.currentCtx.run.units.head.untpdTree)
//     driver.currentCtx.run.units.head.untpdTree match {
//       case PackageDef(_, stats) =>
//         println("")
//         stats.foreach(println)
//         val res = stats.flatMap {
//           // case df : Trees.ValDef[_] if df.modifiers.nonEmpty => Some(Nil)
//           case df: Trees.ValDef[_]   => Some(List(df.name.toString))
//           case dfdf: Trees.DefDef[_] =>
//             // println(dfdf)
//             // println(dfdf.name)
//             None
//           case pat: PatDef =>
//             val res = pat.pats.flatMap {
//               case a: Tuple =>
//                 a.trees.map {
//                   case id: Ident =>
//                     id.name
//                 }
//               case _ => Nil
//             }
//             // println(res)
//             None
//           // case Defn.Var(_, pats, _, _) => Some(pats.flatMap(binders))
//           // case _: Defn => Some(Nil)
//           case _: Import => Some(Nil)
//           case _         => None

//         }
//     }
  // // driver.currentCtx.settings.se

  // val sourceFile = SourceFile.virtual("Hello.scala", source)
  // val loader = new AbstractFileClassLoader(target, appClassLoader)
  // val run = compiler.newRun(context)

  // // val result = run.compileFromStrings(List(sourceFile), Nil)
  // run.compileSources(List(sourceFile))
  // // driver.run
  // println(target)
  // println(target.isEmpty)
  // val cls: Class[_] = loader.loadClass("Hello")
  // val hello = cls.getConstructors()(0).newInstance()
  // println(cls)
  // println(hello)
  // val methods = cls.getDeclaredMethods()(0).invoke(hello)
  // println
}
}