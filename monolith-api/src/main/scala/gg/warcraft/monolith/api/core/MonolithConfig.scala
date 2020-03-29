package gg.warcraft.monolith.api.core

import gg.warcraft.monolith.api.block.box.BlockBox
import gg.warcraft.monolith.api.item.ItemType
import gg.warcraft.monolith.api.world.Direction

case class MonolithConfig(
    devPermission: String,
    debuggingItem: ItemType,
    staffPermission: String,
    modPermission: String,
    adminPermission: String,
    moderatingItem: ItemType,
    buildRepositoryBox: BlockBox,
    buildRepositoryOrientation: Direction
)
