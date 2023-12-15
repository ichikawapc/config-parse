case class Section(name : String,
                   fieldNames : List[String],
                   items : List[Item])

object Section {
  def groupItems(items: List[(String, Item)]): List[Section] = {
    items.groupBy(_._1).map {
      case (sectionName, itemLists) =>
        val sectionFieldNames = itemLists.flatMap(_._2.cacontents.keys).distinct.toList
        val sectionItems = itemLists.map(_._2)
        Section(sectionName, sectionFieldNames, sectionItems)
    }.toList
  }
}