package gg.warcraft.monolith.api.world.portal.config

import gg.warcraft.monolith.api.world.config.LocationConfig
import gg.warcraft.monolith.api.world.Direction

case class PortalConfig(
    entryLocation: LocationConfig,
    exitLocation: LocationConfig,
    exitOrientation: Direction
)
