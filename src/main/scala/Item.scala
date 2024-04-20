case class Item(name: String, contents: Map[String, String]) {
  // nameとcontentsの定義とゲッターまで設定済み
  def toCsvRow(fieldNames: List[String]): List[String] = {
    // fieldNames = リストの項目名⇒既に設定済みと考えてOK
    // fieldNames = List("name", "address", "others");
    // fieldNamesリストの中身と同じkeyを持つvalueをcontentsから取り出す(⇒for文にする?)
    def pickValue(field: String) = {
      // fieldNamesリストの項目を変数fと定義⇒1項目ずつ代入してcontentsマップから取り出す
      if (contents.contains(f)) {
        // fieldNamesリストにfの項目があるか→fieldNamesリストのkeyをfieldNameと定義(定義方法が分からず)
        contents.get(f).getUnsafe;
      } else {
        // return "";
        "";
      }
    }

    val values = fieldNames.map(field=>pickValue(field))
    // 上の変数valuesで引き出してきたvalueをcsvとして当てはめる式を考える。
  }
}

// def getContents(f)の定義
// def getContents(f) {
// if (ある時) {
//  contents.get(f).getUnsafe; getUnsafeは破綻する可能性有⇒パターンマッチなら絶対破綻しない
// } else {
//  return "";
// }
// }

// fieldNames.map(f => getContents(f))
// ↑fieldNamesのValueを1つずつ取ってきて、それぞれをgetContents(f)に変換してくれる(for文から関数へ値を渡すところまで融合)

// パターンマッチ(Javaのswitch文に相当)
// contents.get(f) match {
// case Some(x) => x　←左辺と右辺のxは揃える
// case None => ""
// }

// ↓
// パターンマッチの1行化(Option型の時のみ有効)
// contents.get(f).getOrElse("") ←最後の()にはNoneで返す値
