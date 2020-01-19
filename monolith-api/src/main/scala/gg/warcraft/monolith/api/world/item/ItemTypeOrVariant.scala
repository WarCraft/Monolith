package gg.warcraft.monolith.api.world.item

import com.fasterxml.jackson.annotation.JsonCreator
import gg.warcraft.monolith.api.Implicits

object ItemTypeOrVariant {
  private val itemService = Implicits.itemService

  @JsonCreator
  def apply(data: String): ItemTypeOrVariant =
    itemService.parseData(data)
}

trait ItemTypeOrVariant
