package gg.warcraft.monolith.api.entity.attribute

case class Attributes(attributes: Set[Attribute]) {
  private var computed: Map[AnyRef, Float] = Map.empty

  def get(id: AnyRef): Float =
    if (!computed.contains(id)) {
      val value = attributes.foldRight(0f) { _.value + _ }
      computed += (id -> value)
      value
    } else computed(id)
}
