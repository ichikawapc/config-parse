case class Item(name : String, contents : Map[String, String]) {
    // nameとcontentsの定義とゲッターまで設定済み
    def toCsvRow(fieldNames : List[String]) : List[String] = {
        // fieldNamesのリストの中身を定義
        fieldNames = List["name", "address", "others"];
        // contentsの中身を"address"とその他("others")で分類
        contents : Option[address]
            contents match {
                case Some(address) => List.address->address;
                case None => List.address->" ";
            }
        contents : Option[others]
            contents match {
                case Some(others) => Lis.others->others;
                case None => List.others->" ";
            }
        // 各項目をfieldNamesのリストに格納する
        "name", "address", "others" +: (fieldNames).toList;
    }
}
