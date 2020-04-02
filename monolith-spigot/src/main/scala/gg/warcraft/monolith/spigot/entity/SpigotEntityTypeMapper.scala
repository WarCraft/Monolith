package gg.warcraft.monolith.spigot.entity

import gg.warcraft.monolith.api.entity.Entity
import org.bukkit.entity.{EntityType => SpigotEntityType}

class SpigotEntityTypeMapper {
  def map(typed: SpigotEntityType): Entity.Type =
    Entity.Type withName typed.name

  def map(typed: Entity.Type): SpigotEntityType =
    SpigotEntityType valueOf typed.toString
}
