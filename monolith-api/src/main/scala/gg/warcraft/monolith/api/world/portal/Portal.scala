package gg.warcraft.monolith.api.world.portal

import gg.warcraft.monolith.api.core.Cancellable
import gg.warcraft.monolith.api.entity.Entity
import gg.warcraft.monolith.api.math.Vector3f
import gg.warcraft.monolith.api.world.Location

case class Portal(
    entryLocation: Location,
    exitLocation: Location,
    exitOrientation: Vector3f
)(
    val predicate: Entity => Boolean,
    val effect: Cancellable
)
