//import scala.util.{Failure, Success}
//import scala.util.parsing.combinator.Parsers

/*　
●参考にしたサイト↓
ファイルの出力：http://www.mwsoft.jp/programming/scala/fileread.html
コマンド引数がない時のエラー出力：https://teratail.com/questions/254093
例外処理：https://scala-text.github.io/scala_text/error-handling.html
例外処理２：http://www.nct9.ne.jp/m_hiroi/java/scala12.html
 */
object Main {

  def fileName (sectionName : String) = {
    sectionName.replace(" ", "_") + ".csv"
  }

  def main(args:Array[String]): Unit = {
    val input = "Hello World"
    println(input)

    if(args.length == 0) {
      println(
        """コマンドライン引数を与えて使ってね♡
          |使用例: sbt "run sample/input/bigip.conf
          |""".stripMargin)
    } else {
      val filename = (args(0))
      Parser.readFile(filename) match {
        case Parser.Success(name_items, _) =>
          val sections = Section.groupItems(name_items)
          sections.foreach {sec =>
            val filename = fileName(sec.name)
            sec.saveFile(filename)
            println(s"CSVファイルを作成しました: $filename")
          }
        case Parser.Error(message, next) =>
          println("ERROR: " +message)
          println(next)
        case Parser.Failure(message, next) =>
          println("Failure: " +message)
          println("  position: " + next.pos)
      }
    }


    /*
    val testItemInput ="""apm report default-report {
                         |    report-name sessionReports/sessionSummary
                         |    user /Common/admin
                         |}""".stripMargin

    Parser.parse(Parser.item, testItemInput) match {
      case Parser.Success(r, _next) => println(r)
      case Parser.Failure(e, _next) => println(s"残念: ${e.toString()}")
      case Parser.Error(e, _next) => println(s"エラー: ${e.toString()}")
    }
    val inputData = List(
      ("foo", Item("default", Map("report-name" -> "Summary"))),
      ("bar", Item("monitor", Map("rule" -> "none"))),
      ("foo", Item("report2", Map("report-name" -> "Common", "user" -> "admin")))
    )
    val outputData = Section.groupItems(inputData)
    println(outputData)

    val testInput1 = """user /Common/admin"""
    val testInput2 =
      """default-report {
    report-name sessionReports/sessionSummary
    user /Common/admin
    }"""
    Parser.parse(Parser.field, testInput1) match {
      case Parser.Success(r, _next) => println(r)
      case Parser.Failure(e, _next) => println(s"残念: ${e.toString()}")
      case Parser.Error(e, _next) => println(s"エラー: ${e.toString()}")
    }
    Parser.parse(Parser.field, testInput2) match {
      case Parser.Success(r, _next) => println(r)
      case Parser.Failure(e, _next) => println(s"残念: ${e.toString()}")
      case Parser.Error(e, _next) => println(s"エラー: ${e.toString()}")
    }

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
    }*/
  }
}