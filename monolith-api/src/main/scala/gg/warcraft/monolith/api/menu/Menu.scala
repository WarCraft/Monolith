package gg.warcraft.monolith.api.menu

object Menu {
  type Size = Size.Value
  object Size extends Enumeration {
    protected case class Val(slots: Int) extends super.Val
    implicit def toVal(v: Value): Val = v.asInstanceOf[Val]

    val ONE_ROW = Val(9)
    val TWO_ROWS = Val(18)
    val THREE_ROWS = Val(27)
    val FOUR_ROWS = Val(36)
    val FIVE_ROWS = Val(45)
    val SIX_ROWS = Val(54)

    def fromRows(rows: Int): Size = rows match {
      case 2 => TWO_ROWS
      case 3 => THREE_ROWS
      case 4 => FOUR_ROWS
      case 5 => FIVE_ROWS
      case 6 => SIX_ROWS
      case _ => ONE_ROW
    }
  }
}

case class Menu(
    title: String,
    size: Menu.Size,
    buttons: Map[Int, Button] = Map.empty
)
