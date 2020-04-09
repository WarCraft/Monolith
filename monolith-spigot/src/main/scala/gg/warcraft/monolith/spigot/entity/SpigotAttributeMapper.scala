package gg.warcraft.monolith.spigot.entity

import gg.warcraft.monolith.api.entity.attribute.Attribute
import org.bukkit.attribute.{Attribute => SpigotAttribute}

class SpigotAttributeMapper {
  def map(attribute: SpigotAttribute): Attribute.Generic = attribute match {
    case SpigotAttribute.GENERIC_ARMOR           => Attribute.Generic.ARMOR
    case SpigotAttribute.GENERIC_ARMOR_TOUGHNESS => Attribute.Generic.ARMOR_TOUGHNESS
    case SpigotAttribute.GENERIC_ATTACK_DAMAGE   => Attribute.Generic.ATTACK_DAMAGE
    case SpigotAttribute.GENERIC_ATTACK_SPEED    => Attribute.Generic.ATTACK_SPEED
    case SpigotAttribute.GENERIC_FLYING_SPEED    => Attribute.Generic.FLYING_SPEED
    case SpigotAttribute.GENERIC_FOLLOW_RANGE    => Attribute.Generic.FOLLOW_RANGE
    case SpigotAttribute.GENERIC_LUCK            => Attribute.Generic.LUCK
    case SpigotAttribute.GENERIC_MAX_HEALTH      => Attribute.Generic.MAX_HEALTH
    case SpigotAttribute.GENERIC_MOVEMENT_SPEED  => Attribute.Generic.MOVEMENT_SPEED
    case SpigotAttribute.GENERIC_KNOCKBACK_RESISTANCE =>
      Attribute.Generic.KNOCKBACK_RESISTANCE

    case SpigotAttribute.HORSE_JUMP_STRENGTH => Attribute.Horse.JUMP_STRENGTH
    case SpigotAttribute.ZOMBIE_SPAWN_REINFORCEMENTS =>
      Attribute.Zombie.SPAWN_REINFORCEMENTS
  }

  def map(attribute: Attribute.Generic): SpigotAttribute = attribute match {
    case Attribute.Generic.ARMOR           => SpigotAttribute.GENERIC_ARMOR
    case Attribute.Generic.ARMOR_TOUGHNESS => SpigotAttribute.GENERIC_ARMOR_TOUGHNESS
    case Attribute.Generic.ATTACK_DAMAGE   => SpigotAttribute.GENERIC_ATTACK_DAMAGE
    case Attribute.Generic.ATTACK_SPEED    => SpigotAttribute.GENERIC_ATTACK_SPEED
    case Attribute.Generic.FLYING_SPEED    => SpigotAttribute.GENERIC_FLYING_SPEED
    case Attribute.Generic.FOLLOW_RANGE    => SpigotAttribute.GENERIC_FOLLOW_RANGE
    case Attribute.Generic.LUCK            => SpigotAttribute.GENERIC_LUCK
    case Attribute.Generic.MAX_HEALTH      => SpigotAttribute.GENERIC_MAX_HEALTH
    case Attribute.Generic.MOVEMENT_SPEED  => SpigotAttribute.GENERIC_MOVEMENT_SPEED
    case Attribute.Generic.KNOCKBACK_RESISTANCE =>
      SpigotAttribute.GENERIC_KNOCKBACK_RESISTANCE

    case Attribute.Horse.JUMP_STRENGTH => SpigotAttribute.HORSE_JUMP_STRENGTH
    case Attribute.Zombie.SPAWN_REINFORCEMENTS =>
      SpigotAttribute.ZOMBIE_SPAWN_REINFORCEMENTS
  }
}
