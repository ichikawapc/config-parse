/*　
●参考にしたサイト↓
ファイルの出力：http://www.mwsoft.jp/programming/scala/fileread.html

●やること↓
#14 入力ファイルをコマンドライン引数から取得して、その行数を標準出力する
入力ファイルをコマンドライン引数から取得して、その行数を標準出力する
• main関数の中に書いてください
• コマンドライン引数は main関数の引数の args です
• scalaでは Source.fromFile("ファイル名") でFileからSourceというデータを取ることができます
• Sourceの getLinesというメソッドでファイルの中身の全部の行をとることができます
• length メソッドで長さを取ることができます
• println(s"行数は $n 行だよ♪") で標準出力します
確認方法
sample.config というファイルが手元にあったとして、
sbt "run sample.config"
を実行して行数がでたら成功です。
 */


object Main {
  def main(args:Array[String]): Unit = {
    val input = "Hello World"
    println(input)
    val filename = args(0)  //コマンドラインからファイル名を取得
    val source = io.Source.fromFile(filename, "utf-8") //ファイルを文字コードUTF-8で出力
    val lines = source.getLines //Sourceの getLinesというメソッドでファイルの中身の全部の行をとる
    val lineLength = lines.length //length メソッドでlinesの長さを取る
    println(s"行数は $lineLength 行だよ♪")
    source.close
}
}
