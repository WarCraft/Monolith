package gg.warcraft.monolith.api.core

import gg.warcraft.monolith.api.world.item.ItemType

case class MonolithConfig(
    devPermission: String,
    debuggingItem: ItemType,
    staffPermission: String,
    modPermission: String,
    adminPermission: String,
    moderatingItem: ItemType
)
