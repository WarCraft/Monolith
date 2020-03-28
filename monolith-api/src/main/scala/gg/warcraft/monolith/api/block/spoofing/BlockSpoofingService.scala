package gg.warcraft.monolith.api.block.spoofing

import java.util.UUID

import gg.warcraft.monolith.api.block.Block
import gg.warcraft.monolith.api.world.BlockLocation

trait BlockSpoofingService {
  def spoofBlock(playerId: UUID, fakeBlocks: Block*): Unit
  def unspoofBlock(playerId: UUID, locations: BlockLocation*): Unit
  def unspoofAll(playerIds: UUID*): Unit
}
