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

package gg.warcraft.monolith.spigot.entity

import java.util.UUID

import gg.warcraft.monolith.api.entity.{Entity, Equipment}
import gg.warcraft.monolith.api.entity.team.{Team, TeamService}
import gg.warcraft.monolith.api.entity.Entity.Type
import gg.warcraft.monolith.api.entity.attribute.{Attributes, AttributeService}
import gg.warcraft.monolith.api.entity.data.EntityDataService
import gg.warcraft.monolith.api.entity.status.{Status, StatusService}
import gg.warcraft.monolith.api.math.{AABBf, Vector3f}
import gg.warcraft.monolith.api.world.Location
import gg.warcraft.monolith.spigot.item.SpigotItemMapper
import gg.warcraft.monolith.spigot.math.{SpigotAABBfMapper, SpigotVectorMapper}
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper

class SpigotEntityAdapter(entity: SpigotEntity)(implicit
    attributeService: AttributeService,
    statusService: StatusService,
    teamService: TeamService,
    dataService: EntityDataService,
    boundingBoxMapper: SpigotAABBfMapper,
    entityTypeMapper: SpigotEntityTypeMapper,
    vectorMapper: SpigotVectorMapper,
    locationMapper: SpigotLocationMapper,
    itemMapper: SpigotItemMapper
) extends Entity {
  override lazy val id: UUID = entity.getUniqueId
  override lazy val typed: Type = entityTypeMapper.map(entity.getType)

  override def team: Option[Team] = dataService.data(id).team
  override def attributes: Attributes = attributeService.getAttributes(id)
  override def status: Status = statusService.getStatus(id)

  override def name: String = entity.getName
  override def location: Location = locationMapper.map(entity.getLocation)
  override def eyeLocation: Location = locationMapper.map(entity.getEyeLocation)
  override def boundingBox: AABBf = boundingBoxMapper.map(entity.getBoundingBox)
  override def velocity: Vector3f = vectorMapper.map(entity.getVelocity)
  override def health: Float = entity.getHealth.toFloat
  override def isAlive: Boolean = !entity.isDead
  override def isGrounded: Boolean = entity.isOnGround
  override def equipment: Equipment = Equipment(
    itemMapper.map(entity.getEquipment.getHelmet),
    itemMapper.map(entity.getEquipment.getChestplate),
    itemMapper.map(entity.getEquipment.getLeggings),
    itemMapper.map(entity.getEquipment.getBoots),
    itemMapper.map(entity.getEquipment.getItemInMainHand),
    itemMapper.map(entity.getEquipment.getItemInOffHand)
  )
}
