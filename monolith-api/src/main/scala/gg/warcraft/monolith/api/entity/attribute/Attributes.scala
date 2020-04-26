package gg.warcraft.monolith.api.entity.attribute

case class Attributes(attributes: Set[Attribute]) {
  private var computed: Map[Attribute.Type, Float] = Map.empty

  def get(typed: Attribute.Type): Float =
    if (!computed.contains(typed)) {
      val value = attributes.foldRight(0f) { _.value + _ }
      computed += (typed -> value)
      value
    } else computed(typed)
}
