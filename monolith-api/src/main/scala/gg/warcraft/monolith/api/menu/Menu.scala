package gg.warcraft.monolith.api.menu

object Menu {
  object Size {
    def fromRows(rows: Int): Size = rows match {
      case 1 => ONE_ROW
      case 2 => TWO_ROWS
      case 3 => THREE_ROWS
      case 4 => FOUR_ROWS
      case 5 => FIVE_ROWS
      case 6 => SIX_ROWS
    }
  }

  sealed abstract class Size(val slots: Int)
  case object ONE_ROW extends Size(9)
  case object TWO_ROWS extends Size(18)
  case object THREE_ROWS extends Size(27)
  case object FOUR_ROWS extends Size(36)
  case object FIVE_ROWS extends Size(45)
  case object SIX_ROWS extends Size(54)
}

case class Menu(
    title: String,
    size: Menu.Size,
    buttons: Map[Int, Button] = Map.empty
)
