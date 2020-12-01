package parser.scalameta.tests

import scala.meta._

class ParserTests extends munit.FunSuite {

  implicit val dialect = scala.meta.dialects.Dotty

  def assertParsed(sourceCode: String, expected: Source): Unit = {
    val parsed = sourceCode
      .parse[Source]
      .fold(
        erro => throw erro.details,
        success => success
      )
    assertEquals(parsed.structure, expected.structure)
  }

  test("test-simple-indent") {
    assertParsed(
      """|class A:
           |  def b =
           |   println(1)
           |""".stripMargin,
      Source(
        List(
          Defn.Class(
            Nil,
            Type.Name("A"),
            Nil,
            Ctor.Primary(Nil, Name(""), Nil),
            Template(
              Nil,
              Nil,
              Self(Name(""), None),
              List(
                Defn.Def(
                  Nil,
                  Term.Name("b"),
                  Nil,
                  Nil,
                  None,
                  Term.Apply(Term.Name("println"), List(Lit.Int(1)))
                )
              )
            )
          )
        )
      )
    )
  }
}
