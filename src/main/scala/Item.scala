case class Item(name: String, contents: Map[String, String]) {
  // nameとcontentsの定義とゲッターまで設定済み
  def toCsvRow(fieldNames: List[String]): List[String] = {
    // fieldNamesのリストの中身を定義(リストの中身は例え)
    // fieldNames = リストの項目名
    // fieldNames = List("name", "address", "others");
    // fieldNamesリストの中身と同じkeyを持つvalueをcontentsから取り出す(⇒for文にする?)
    def pickValue(field: String) = {
      // Option[String]型にする
      // for文(リスト内のcontentsの数だけ繰り返す)
      for (i = 0, i <field.length, i++) {
        val x = contents.get(field: String)
        // Option[String]型からString型へ変換
        Option.getOrElse(x)
        x match {
          case Some(fieldNames) => // fieldNamesのリストに格納したい
          case None             => null // スキップする(or空欄にしておく?)
        }
      }
    }
    // fieldNamesからcontentsの項目をリストに格納
    fieldNames.map(field => pickValue(field))
  }
}
