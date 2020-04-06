package gg.warcraft.monolith.api.world.portal

import gg.warcraft.monolith.api.core.Cancellable
import gg.warcraft.monolith.api.entity.{Entity, EntityService}
import gg.warcraft.monolith.api.math.Vector3f
import gg.warcraft.monolith.api.world.Location

class PortalService(
    implicit entityService: EntityService
) {
  private final val TELEPORT_RADIUS = 1

  private var _portals: List[Portal] = Nil

  def portals: List[Portal] = _portals

  def spawnPortal(
      entryLocation: Location,
      exitLocation: Location,
      exitOrientation: Vector3f,
      predicate: Entity => Boolean,
      effect: Cancellable
  ): Portal = {
    val portal = Portal(
      entryLocation,
      exitLocation,
      exitOrientation
    )(predicate, effect)
    _portals ::= portal
    portal
  }

  def removePortal(portal: Portal): Unit = {
    _portals = _portals filter { _ != portal }
    portal.effect.cancel()
  }

  private[portal] def tick(portal: Portal): Unit = {
    import portal._
    entityService
      .getNearbyEntities(entryLocation, TELEPORT_RADIUS)
      .filter { _.typed == Entity.Type.PLAYER }
      .filter { predicate.apply }
      .foreach { it =>
        if (exitOrientation == null)
          entityService teleportEntity (it.id, exitLocation)
        else entityService teleportEntity (it.id, exitLocation, exitOrientation)
      }
  }
}
