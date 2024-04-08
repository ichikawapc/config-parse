/*
参考にしたサイト↓
正規表現：https://qiita.com/haryon0103/items/dffbe5333be1fc8a6908
 */

import scala.util.parsing.combinator._  //パーサコンビネーターライブラリを使用するためのimport文
import scala.util.matching.Regex  //正規表現を使用するためのimport文

object Parser extends RegexParsers {  //RegexParsersトレイトを継承したオブジェクト(Parser)を作成
  override def skipWhitespace = true  //スペースをスキップ
  def name : Regex =  """[0-9a-zA-Z/:_.,-]+""".r    //英数字記号の文字列を抽出
  def names : Parser[List[String]] = name.*         //nameをリスト化（Parser[A] は、A 型の値を解析するためのパーサ）
  def run(text: String) : ParseResult[List[String]] = parse(names, text)  //textに対してnamesを実行。ParserResult[List[String]]型で返す。ParserResult[A]は、パーサによって処理された入力に対する結果を格納し、パースが成功したかどうか、失敗したかどうか、エラーが発生したかどうかを示す。

  def simpleContent : Parser[String] = for {
    field <- names <~ whiteSpace
    content <- names <~ "\n"
  } yield content.mkString  //fieldとcontentがシンプルに対になってる

  def blockContent : Parser[String] = for {
    field <- names <~ whiteSpace <~ elem('{') <~ "\n"
    content <- (names <~ whiteSpace <~ elem('}')).*
  } yield content.mkString("\n")   //contentが{}で囲まれてる

  def content : Parser[String] = simpleContent | blockContent

}