package gg.warcraft.monolith.api.entity.attribute

case class Attributes(modifiers: Set[Attribute.Modifier]) {
  private lazy val modifiersByGroup = modifiers.groupBy { _.typed }

  private var computed: Map[Attribute, Float] = Map.empty

  def get(attribute: Attribute): Float =
    if (!computed.contains(attribute)) {
      val flat = modifiersByGroup(Attribute.Modifier.FLAT)
        .foldRight(0f) { _.value + _ }
      val percent = modifiersByGroup(Attribute.Modifier.PERCENT)
        .foldRight(0f) { _.value + _ }
      val value = flat + (1 + percent) * flat
      computed += (attribute -> value)
      value
    } else computed(attribute)
}
