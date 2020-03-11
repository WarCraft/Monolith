package gg.warcraft.monolith.api.combat

object CombatValue {
  object Modifier extends Enumeration {
    type Type = Value
    val PERCENT, FLAT, OVERRIDE = Value
  }

  case class Modifier(
      source: CombatSource,
      typed: CombatValue.Modifier.Type,
      value: Float
  )
}

case class CombatValue(
    source: CombatSource,
    modifiers: List[CombatValue.Modifier] = Nil,
    base: Float
) {
  lazy val modified: Float = {
    val overrideMods = modifiers filter { _.typed == CombatValue.Modifier.OVERRIDE }
    if (overrideMods.isEmpty) {
      val percentMods = modifiers filter { _.typed == CombatValue.Modifier.PERCENT }
      val flatMods = modifiers filter { _.typed == CombatValue.Modifier.FLAT }
      base + percentMods.map(base * _.value).sum + flatMods.map(_.value).sum
    } else overrideMods.map(_.value).max
  }
}
