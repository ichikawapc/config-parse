import scala.util.Try

/*　
●参考にしたサイト↓
ファイルの出力：http://www.mwsoft.jp/programming/scala/fileread.html
コマンド引数がない時のエラー出力：https://teratail.com/questions/254093
 */
object Main {
  def main(args:Array[String]): Unit = {
    val input = "Hello World"
    println(input)
    if(args.length == 0) {
      println("コマンドライン引数を与えて使ってね♡")
      println("使用例: sbt \"run sample/input/bigip.conf\"")
    } else {
      val filename = (args(0))  //コマンドラインからファイル名を取得
      val source = io.Source.fromFile(filename, "utf-8") //ファイルを文字コードUTF-8で出力
      val lines = source.getLines //Sourceの getLinesというメソッドでファイルの中身の全部の行をとる
      val lineLength = lines.length //length メソッドでlinesの長さを取る
      println(s"行数は $lineLength 行だよ♪")
      source.close
    }
  }
}
