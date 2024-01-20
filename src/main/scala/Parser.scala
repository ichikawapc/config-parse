/*
参考にしたサイト↓
正規表現：https://qiita.com/haryon0103/items/dffbe5333be1fc8a6908
 */

import scala.util.parsing.combinator._
import scala.util.matching.Regex

object Parser extends RegexParsers {
  override def skipWhitespace = true
  def name: Regex =  """[0-9a-zA-Z/:-_.,]+""".r
  def names: Parser[List[String]] = name.*
  def run(text: String):ParseResult[List[String]] = parse(names, text)
  def item : Parser[(String, Item)] = for {
    ns <- names <~ whiteSpace <~ elem('{') <~ "\n"
    conts <- (Section.sectionItems <~ "\n").*
    _ <- elem('}')
  } yield {
    (ns, conts)
  }
}