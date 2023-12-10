import com.sun.tools.javac.jvm.Items
case class Section(name: String, attributes: List[String], itemList: List[Item])

object Section {
  def groupItems(items: List[(String, Item)]): List[Section] = {
    items.groupBy(_._1).map {
      case (sectionName, itemLists) =>
        val sectionAttributes = itemLists.flatMap(_._2.cacontents.keys).distinct.toList
        val sectionItems = itemLists.map(_._2)
        Section(sectionName, sectionAttributes, sectionItems)
    }.toList
  }
}