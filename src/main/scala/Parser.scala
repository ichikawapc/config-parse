/*
参考にしたサイト↓
正規表現：https://qiita.com/haryon0103/items/dffbe5333be1fc8a6908
 */

import scala.util.parsing.combinator._
import scala.util.matching.Regex

object Parser extends RegexParsers {
  override def skipWhitespace = true
  def name : Regex =  """[0-9a-zA-Z/:-_.,]+""".r    //英数字記号の文字列を抽出
  def names : Parser[List[String]] = name.*         //nameをリスト化
  def run(text: String) : ParseResult[List[String]] = parse(names, text)  //textに対してnamesを実行
  def simpleContent : Parser[(String, String)] = for {
    user <- names <~ whiteSpace
    content <- names <~ "\n"
  }   //fieldとcontentがシンプルに対になってる
  def blockContent : Parser[(String, String)] = ???      //contentが{}で囲まれてる
  def content : Parser[String] = simpleContent | blockContent
  /*def field : Parser[(String, String)]    //天才が作るやつ
  def item : Parser[(String, Item)] = for {
    ns <- names <~ elem('{') <~ "\n"
    fs <- (field <~ "\n").*
    _ <- elem('}')
  } yield {
    (ns, fs)
  }*/
}