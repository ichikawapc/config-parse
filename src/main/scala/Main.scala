import scala.util.Try
import scala.util.parsing.input.CharSequenceReader
/*　
●参考にしたサイト↓
ファイルの出力：http://www.mwsoft.jp/programming/scala/fileread.html
コマンド引数がない時のエラー出力：https://teratail.com/questions/254093
例外処理：https://scala-text.github.io/scala_text/error-handling.html
例外処理２：http://www.nct9.ne.jp/m_hiroi/java/scala12.html
 */
object Main {
  def main(args:Array[String]): Unit = {
    val input = "Hello World"
    println(input)

    val inputData = List(
      ("foo", Item("default", Map("report-name" -> "Summary"))),
      ("bar", Item("monitor", Map("rule" -> "none"))),
      ("foo", Item("report2", Map("report-name" -> "Common", "user" -> "admin")))
    )
    val outputData = Section.groupItems(inputData)
    println(outputData)

    val testInput1 = "user /Common/admin"
    val testInput2 =
      """availability-zone {
      valid-values { a b c d }
      }""".stripMargin

    val testSimpleContent = Parser.parse(Parser.content, testInput1)
    val testBlockContent = Parser.parse(Parser.content, testInput2)

    //val testSimpleContent = Parser.content(new CharSequenceReader(testInput1))
    //val testBlockContent = Parser.content(new CharSequenceReader(testInput2))
    println(testSimpleContent)
    println(testBlockContent)

    if(args.length == 0) {
      println("コマンドライン引数を与えて使ってね♡")
      println("使用例: sbt \"run sample/input/bigip.conf\"")
    } else {
      val filename = (args(0))  //コマンドラインからファイル名を取得
      try {
        val source = io.Source.fromFile(filename, "utf-8") //ファイルを文字コードUTF-8で出力
        val lines = source.getLines //Sourceの getLinesというメソッドでファイルの中身の全部の行をとる
        val lineLength = lines.length //length メソッドでlinesの長さを取る
        println(s"行数は $lineLength 行だよ♪")
        source.close
      } catch {
        case e: java.io.FileNotFoundException =>  //「java.io.FileNotFoundException」というエラーコードをキャッチ
          println(s"$filename は存在しません")
      }
    }
  }
}