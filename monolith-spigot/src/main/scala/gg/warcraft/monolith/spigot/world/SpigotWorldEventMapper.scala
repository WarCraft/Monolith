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

package gg.warcraft.monolith.spigot.world

import gg.warcraft.monolith.api.core.event.EventService
import gg.warcraft.monolith.api.entity.{
  EntityDespawnEvent, EntityPreDespawnEvent, EntityPreRespawnEvent,
  EntityRespawnEvent
}
import gg.warcraft.monolith.spigot.entity.SpigotEntityService
import org.bukkit.Chunk
import org.bukkit.event.world.{ChunkLoadEvent, ChunkUnloadEvent}
import org.bukkit.event.{EventHandler, EventPriority, Listener}

class SpigotWorldEventMapper(implicit
    eventService: EventService,
    entityService: SpigotEntityService
) extends Listener {
  private var despawnEvents: Map[Chunk, List[EntityPreDespawnEvent]] = Map.empty
  private var respawnEvents: Map[Chunk, List[EntityPreRespawnEvent]] = Map.empty

  @EventHandler(priority = EventPriority.HIGH)
  def preLoad(event: ChunkLoadEvent): Unit = {
    val events = event.getChunk.getEntities
      .flatMap { it => entityService.getEntityOption(it.getUniqueId) }
      .map { it => EntityPreRespawnEvent(it, it.location) }
      .map(eventService.publish)
    respawnEvents += (event.getChunk -> events)
  }

  @EventHandler(priority = EventPriority.MONITOR)
  def onLoad(event: ChunkLoadEvent): Unit = {
    respawnEvents(event.getChunk).foreach { preEvent =>
      import preEvent._
      if (allowed) {
        entityService.teleportEntity(
          entity.id,
          location,
          entity.eyeLocation.rotation
        )
        eventService << EntityRespawnEvent(entity, location)
      } else entityService.removeEntity(entity.id)
    }
    respawnEvents -= event.getChunk
  }

  @EventHandler(priority = EventPriority.HIGH)
  def preUnload(event: ChunkUnloadEvent): Unit = {
    val events = event.getChunk.getEntities
      .flatMap { it => entityService.getEntityOption(it.getUniqueId) }
      .map { EntityPreDespawnEvent(_) }
      .map(eventService.publish)
    despawnEvents += (event.getChunk -> events)
  }

  @EventHandler(priority = EventPriority.MONITOR)
  def onUnload(event: ChunkUnloadEvent): Unit = {
    despawnEvents(event.getChunk).foreach { preEvent =>
      import preEvent._
      if (allowed) {
        eventService << EntityDespawnEvent(entity)
      } else entityService.removeEntity(entity.id)
    }
    despawnEvents -= event.getChunk
  }
}
