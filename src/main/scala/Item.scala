case class Item(name :String,
                contents : Map[String, String]) {

  //Section内で同じフィールド数や順番になるようにfieldNamesをパラメータにする
  def toCsvRow(fieldNames: List[String]): List[String] = {
    def pickValue(field: String) = {
      contents.get(field) match {
        case None => ""
        case Some(v) => v
      }
    }
    name :: fieldNames.map(pickValue(_))
  }

}
