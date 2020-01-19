package gg.warcraft.monolith.api.world.block

import com.fasterxml.jackson.annotation.JsonCreator
import gg.warcraft.monolith.api.Implicits

object BlockTypeVariantOrState {
  private val worldService = Implicits.worldService

  @JsonCreator
  def apply(data: String): BlockTypeVariantOrState =
    worldService.parseData(data)
}

trait BlockTypeVariantOrState
