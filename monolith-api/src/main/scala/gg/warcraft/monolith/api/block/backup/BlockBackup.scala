package gg.warcraft.monolith.api.block.backup

import java.util.UUID

import gg.warcraft.monolith.api.world.BlockLocation

case class BlockBackup(
    id: UUID,
    data: String,
    location: BlockLocation
)
