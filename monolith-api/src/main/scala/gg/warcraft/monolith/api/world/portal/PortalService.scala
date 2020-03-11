package gg.warcraft.monolith.api.world.portal

import gg.warcraft.monolith.api.entity.{Entity, EntityType}
import gg.warcraft.monolith.api.entity.service.{
  EntityCommandService, EntityQueryService
}
import gg.warcraft.monolith.api.math.Vector3f
import gg.warcraft.monolith.api.util.Cancellable
import gg.warcraft.monolith.api.world.Location

import scala.jdk.CollectionConverters._

class PortalService(
    implicit entityQueryService: EntityQueryService,
    entityCommandService: EntityCommandService
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
    entityQueryService
      .getNearbyEntities(entryLocation, TELEPORT_RADIUS)
      .asScala
      .filter { _.getType == EntityType.PLAYER }
      .filter { predicate.apply }
      .foreach { it =>
        if (exitOrientation == null) entityCommandService.teleport(it.getId, exitLocation)
        else entityCommandService.teleport(it.getId, exitLocation, exitOrientation)
      }
  }
}
