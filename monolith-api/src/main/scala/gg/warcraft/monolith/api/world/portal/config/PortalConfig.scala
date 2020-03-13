package gg.warcraft.monolith.api.world.portal.config

import gg.warcraft.monolith.api.block.BlockDirection
import gg.warcraft.monolith.api.world.config.LocationConfig

case class PortalConfig(
    entryLocation: LocationConfig,
    exitLocation: LocationConfig,
    exitOrientation: BlockDirection
)
