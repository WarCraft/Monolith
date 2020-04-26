package gg.warcraft.monolith.api.entity.attribute

object Attribute {
  trait Type {
    val name: String
  }

  sealed abstract class Generic(override val name: String) extends Type
  object Generic {
    case object ARMOR extends Generic("Armor")
    case object ARMOR_TOUGHNESS extends Generic("Armor Toughness")
    case object ATTACK_DAMAGE extends Generic("Attack Damage")
    case object ATTACK_SPEED extends Generic("Attack Speed")
    case object FLYING_SPEED extends Generic("Flying Speed")
    case object FOLLOW_RANGE extends Generic("Follow Range")
    case object KNOCKBACK_RESISTANCE extends Generic("Knockback Resistance")
    case object LUCK extends Generic("Luck")
    case object MAX_HEALTH extends Generic("Max Health")
    case object MOVEMENT_SPEED extends Generic("Movement Speed")
  }

  sealed abstract class Horse(override val name: String) extends Type
  object Horse {
    case object JUMP_STRENGTH extends Horse("Jump Strength")
  }

  sealed abstract class Zombie(override val name: String) extends Type
  object Zombie {
    case object SPAWN_REINFORCEMENTS extends Horse("Spawn Reinforcements")
  }
}

trait Attribute {
  val typed: Attribute.Type
  val value: Float
}
