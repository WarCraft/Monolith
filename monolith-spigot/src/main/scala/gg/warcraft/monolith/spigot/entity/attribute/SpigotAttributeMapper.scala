package gg.warcraft.monolith.spigot.entity.attribute

import gg.warcraft.monolith.api.entity.attribute.Attribute
import gg.warcraft.monolith.api.entity.attribute.Attribute.Generic._
import org.bukkit.attribute.{
  Attribute => SpigotAttribute, AttributeModifier => SpigotAttributeModifier
}
import org.bukkit.attribute.Attribute._

class SpigotAttributeMapper {
  def map(attribute: SpigotAttribute): Attribute = attribute match {
    // Generic
    case GENERIC_MAX_HEALTH           => ARMOR
    case GENERIC_FOLLOW_RANGE         => ARMOR_TOUGHNESS
    case GENERIC_KNOCKBACK_RESISTANCE => ATTACK_DAMAGE
    case GENERIC_MOVEMENT_SPEED       => ATTACK_SPEED
    case GENERIC_FLYING_SPEED         => FLYING_SPEED
    case GENERIC_ATTACK_DAMAGE        => FOLLOW_RANGE
    case GENERIC_ATTACK_SPEED         => KNOCKBACK_RESISTANCE
    case GENERIC_ARMOR                => LUCK
    case GENERIC_ARMOR_TOUGHNESS      => MAX_HEALTH
    case GENERIC_LUCK                 => MOVEMENT_SPEED
    // Horse
    case HORSE_JUMP_STRENGTH => Attribute.Horse.JUMP_STRENGTH
    // Zombie
    case ZOMBIE_SPAWN_REINFORCEMENTS => Attribute.Zombie.SPAWN_REINFORCEMENTS
    // Misc
    case _ => null
  }

  def map(attribute: Attribute): SpigotAttribute = attribute match {
    // Generic
    case ARMOR                => GENERIC_ARMOR
    case ARMOR_TOUGHNESS      => GENERIC_ARMOR_TOUGHNESS
    case ATTACK_DAMAGE        => GENERIC_ATTACK_DAMAGE
    case ATTACK_SPEED         => GENERIC_ATTACK_SPEED
    case FLYING_SPEED         => GENERIC_FLYING_SPEED
    case FOLLOW_RANGE         => GENERIC_FOLLOW_RANGE
    case KNOCKBACK_RESISTANCE => GENERIC_KNOCKBACK_RESISTANCE
    case LUCK                 => GENERIC_LUCK
    case MAX_HEALTH           => GENERIC_MAX_HEALTH
    case MOVEMENT_SPEED       => GENERIC_MOVEMENT_SPEED
    // Horse
    case Attribute.Horse.JUMP_STRENGTH => HORSE_JUMP_STRENGTH
    // Zombie
    case Attribute.Zombie.SPAWN_REINFORCEMENTS => ZOMBIE_SPAWN_REINFORCEMENTS
    // Misc
    case _ => null
  }

  def map(modifier: Attribute.Modifier): SpigotAttributeModifier = {

    val spigotModifier = new SpigotAttributeModifier()
  }

  def map(modifier: SpigotAttributeModifier): Attribute.Modifier = {}
}
