package gg.warcraft.monolith.api.core

import gg.warcraft.monolith.api.world.item.ItemType

case class MonolithConfig(
    devPermission: String,
    staffPermission: String,
    modPermission: String,
    adminPermission: String,
    debuggingItem: ItemType,
    moderatingItem: ItemType
)
