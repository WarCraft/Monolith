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

  // TODO refactor Color into list of String vals
  def toString(color: Color): String = color match {
    case Color.Aqua    => "AQUA"
    case Color.Black   => "BLACK"
    case Color.Blue    => "BLUE"
    case Color.Fuchsia => "FUCHSIA"
    case Color.Gray    => "GRAY"
    case Color.Green   => "GREEN"
    case Color.Lime    => "LIME"
    case Color.Maroon  => "MAROON"
    case Color.Navy    => "NAVY"
    case Color.Olive   => "OLIVE"
    case Color.Orange  => "ORANGE"
    case Color.Purple  => "PURPLE"
    case Color.Red     => "RED"
    case Color.Silver  => "SILVER"
    case Color.Teal    => "TEAL"
    case Color.White   => "WHITE"
    case Color.Yellow  => "YELLOW"
  }
}
