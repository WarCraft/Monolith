/*
 * MIT License
 *
 * Copyright (c) 2020 WarCraft <https://github.com/WarCraft>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package gg.warcraft.monolith.api.world.portal

import gg.warcraft.monolith.api.effect.Effect
import gg.warcraft.monolith.api.entity.{Entity, EntityService}
import gg.warcraft.monolith.api.math.Vector3f
import gg.warcraft.monolith.api.world.Location

class PortalService(implicit
    entityService: EntityService
) {
  private final val TELEPORT_RADIUS = 1

  private var _portals: Set[Portal] = Set.empty

  def portals: Set[Portal] = _portals

  def spawnPortal(
      entryLocation: Location,
      exitLocation: Location,
      exitOrientation: Vector3f,
      predicate: Entity => Boolean,
      effect: Effect
  ): Portal = {
    val portal = Portal(
      entryLocation,
      exitLocation,
      exitOrientation,
      predicate,
      effect
    )
    _portals += portal
    portal
  }

  def updatePortal(
      portal: Portal,
      predicate: Entity => Boolean,
      effect: Effect
  ): Unit = {
    portal.effect.stop()
    effect.start()
    _portals += portal.copy(predicate = predicate, effect = effect)
  }

  def removePortal(portal: Portal): Unit = {
    _portals -= portal
    portal.effect.stop()
  }

  private[portal] def tick(portal: Portal): Unit = {
    import portal._
    entityService
      .getNearbyEntities(entryLocation, TELEPORT_RADIUS)
      .filter { _.typed == Entity.Type.PLAYER }
      .filter { predicate.apply }
      .foreach { it =>
        if (exitOrientation == null)
          entityService.teleportEntity(it.id, exitLocation)
        else entityService.teleportEntity(it.id, exitLocation, exitOrientation)
      }
  }
}
