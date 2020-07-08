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

class PortalService(implicit
    entityService: EntityService
) {
  private final val TELEPORT_RADIUS = 1

  private var _portals: Set[Portal] = Set.empty

  def portals: Set[Portal] = _portals

  def spawnPortal(
      config: Portal.Config,
      predicate: Entity => Boolean,
      effect: Effect
  ): Portal = {
    val orientation = config.orientation.map { _.toVector3f }
    val portal = Portal(config.entry, config.exit, orientation, predicate, effect)
    _portals += portal
    portal
  }

  def updatePortal(
      portal: Portal,
      predicate: Entity => Boolean,
      effect: Effect
  ): Unit = {
    portal.effect.stop()
    _portals += portal.copy(predicate = predicate, effect = effect)
    effect.start()
  }

  def removePortal(portal: Portal): Unit = {
    portal.effect.stop()
    _portals -= portal
  }

  private[portal] def tick(portal: Portal): Unit = {
    import portal._
    entityService
      .getNearbyEntities(entry, TELEPORT_RADIUS)
      .filter { _.typed == Entity.Type.PLAYER }
      .filter { predicate.apply }
      .foreach { player =>
        orientation match {
          case Some(orientation) =>
            entityService.teleportEntity(player.id, exit, orientation)
          case None =>
            entityService.teleportEntity(player.id, exit)
        }
      }
  }
}
