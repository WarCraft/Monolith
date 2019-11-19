package gg.warcraft.monolith.api.combat

case class CombatValue(
    source: CombatSource,
    base: Float,
    modifiers: List[CombatValueModifier] = List.empty
) {
  lazy val modified: Float = {
    val overrideMods = modifiers.filter(_.`type` == CombatValueModifierType.OVERRIDE)
    if (overrideMods.isEmpty) {
      val percentMods = modifiers.filter(_.`type` == CombatValueModifierType.PERCENT)
      val flatMods = modifiers.filter(_.`type` == CombatValueModifierType.FLAT)
      base + percentMods.map(base * _.modifier).sum + flatMods.map(_.modifier).sum
    } else overrideMods.map(_.modifier).max
  }
}
