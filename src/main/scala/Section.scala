import com.github.tototoshi.csv.CSVWriter
import java.io.File

case class Section(name : String,
                   fieldNames : List[String],
                   items : List[Item]) {
  def toTable() : List[List[String]] = {
    val header = "Item Name" :: fieldNames
    val body = items.map(_.toCsvRow(fieldNames))
    header :: body
  }

  def saveFile(fileName: String) = {
    val csv = toTable()
    val writer = CSVWriter.open(new File(fileName))
    writer.writeAll(csv)
  }
}

object Section {
  def groupItems(items: List[(String, Item)]): List[Section] = {
    items.groupBy(_._1).map {
      case (sectionName, itemLists) =>
        val sectionFieldNames = itemLists.flatMap(_._2.contents.keys).distinct.toList
        val sectionItems = itemLists.map(_._2)
        Section(sectionName, sectionFieldNames, sectionItems)
    }.toList
  }
}
