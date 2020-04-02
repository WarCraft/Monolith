package gg.warcraft.monolith.spigot.entity

import java.util.UUID

import gg.warcraft.monolith.api.entity.{Entity, Equipment}
import gg.warcraft.monolith.api.entity.team.{Team, TeamService}
import gg.warcraft.monolith.api.entity.Entity.Type
import gg.warcraft.monolith.api.entity.attribute.{Attributes, AttributeService}
import gg.warcraft.monolith.api.entity.data.EntityDataService
import gg.warcraft.monolith.api.entity.status.{Status, StatusService}
import gg.warcraft.monolith.api.math.Vector3f
import gg.warcraft.monolith.api.world.Location
import gg.warcraft.monolith.spigot.item.SpigotItemMapper
import gg.warcraft.monolith.spigot.math.SpigotVectorMapper
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper

class SpigotEntityAdapter(entity: SpigotEntity)(
    implicit attributeService: AttributeService,
    statusService: StatusService,
    teamService: TeamService,
    dataService: EntityDataService,
    entityTypeMapper: SpigotEntityTypeMapper,
    vectorMapper: SpigotVectorMapper,
    locationMapper: SpigotLocationMapper,
    itemMapper: SpigotItemMapper
) extends Entity {
  override lazy val id: UUID = entity.getUniqueId
  override lazy val typed: Type = entityTypeMapper map entity.getType

  override def team: Option[Team] = (dataService getEntityData id).get.team
  override def attributes: Attributes = attributeService getAttributes id
  override def status: Status = statusService getStatus id

  override def name: String = entity.getName
  override def location: Location = locationMapper map entity.getLocation
  override def eyeLocation: Location = locationMapper map entity.getEyeLocation
  override def velocity: Vector3f = vectorMapper map entity.getVelocity
  override def health: Float = entity.getHealth.toFloat
  override def isGrounded: Boolean = entity.isOnGround
  override def equipment: Equipment = Equipment(
    itemMapper map entity.getEquipment.getHelmet,
    itemMapper map entity.getEquipment.getChestplate,
    itemMapper map entity.getEquipment.getLeggings,
    itemMapper map entity.getEquipment.getBoots,
    itemMapper map entity.getEquipment.getItemInMainHand,
    itemMapper map entity.getEquipment.getItemInOffHand
  )
}
