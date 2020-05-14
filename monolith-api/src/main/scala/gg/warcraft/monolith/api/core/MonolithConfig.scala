package gg.warcraft.monolith.api.core

import gg.warcraft.monolith.api.block.box.BlockBox
import gg.warcraft.monolith.api.item.ItemType
import gg.warcraft.monolith.api.world.Direction

case class MonolithConfig(
    // Staff Permissions
    staffPermission: String,
    modPermission: String,
    adminPermission: String,
    devPermission: String,
    // Staff Tools
    moderatingItem: ItemType,
    debuggingItem: ItemType,
    // Build Repository
    buildRepositoryBox: BlockBox,
    buildRepositoryOrientation: Direction,
    // Maintenance Mode
    maintenanceMode: Boolean,
    maintenanceModePermission: String,
    // Miscellaneous
    shutdownMessage: String
)
