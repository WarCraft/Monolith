package gg.warcraft.monolith.api.core

sealed trait Color

object Color {
  case object Aqua extends Color
  case object Black extends Color
  case object Blue extends Color
  case object Fuchsia extends Color
  case object Gray extends Color
  case object Green extends Color
  case object Lime extends Color
  case object Maroon extends Color
  case object Navy extends Color
  case object Olive extends Color
  case object Orange extends Color
  case object Purple extends Color
  case object Red extends Color
  case object Silver extends Color
  case object Teal extends Color
  case object White extends Color
  case object Yellow extends Color

  def valueOf(string: String): Color = string match {
    case "AQUA"    => Color.Aqua
    case "BLACK"   => Color.Black
    case "BLUE"    => Color.Blue
    case "FUCHSIA" => Color.Fuchsia
    case "GRAY"    => Color.Gray
    case "GREEN"   => Color.Green
    case "LIME"    => Color.Lime
    case "MAROON"  => Color.Maroon
    case "NAVY"    => Color.Navy
    case "OLIVE"   => Color.Olive
    case "ORANGE"  => Color.Orange
    case "PURPLE"  => Color.Purple
    case "RED"     => Color.Red
    case "SILVER"  => Color.Silver
    case "TEAL"    => Color.Teal
    case "WHITE"   => Color.White
    case "YELLOW"  => Color.Yellow
  }
}
