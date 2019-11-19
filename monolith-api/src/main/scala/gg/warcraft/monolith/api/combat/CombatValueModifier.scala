package gg.warcraft.monolith.api.combat

case class CombatValueModifier(
    `type`: CombatValueModifierType,
    modifier: Float,
    source: CombatSource
)
