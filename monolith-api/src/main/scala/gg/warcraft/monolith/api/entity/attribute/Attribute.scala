package gg.warcraft.monolith.api.entity.attribute

object Attribute {
  type Generic = Generic.Value
  object Generic extends Enumeration {
    val ARMOR, ARMOR_TOUGHNESS, ATTACK_DAMAGE, ATTACK_SPEED, FLYING_SPEED,
        FOLLOW_RANGE, KNOCKBACK_RESISTANCE, LUCK, MAX_HEALTH, MOVEMENT_SPEED = Value
  }

  type Horse = Horse.Value
  object Horse extends Enumeration {
    val JUMP_STRENGTH = Value
  }

  type Zombie = Zombie.Value
  object Zombie extends Enumeration {
    val SPAWN_REINFORCEMENTS = Value
  }
}

trait Attribute {
  val id: Any
  val value: Float
}
