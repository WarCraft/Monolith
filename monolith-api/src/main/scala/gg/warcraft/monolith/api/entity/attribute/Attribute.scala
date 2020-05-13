package gg.warcraft.monolith.api.entity.attribute

trait Attribute {
  val name: String
}

object Attribute {
  case class Modifier(
      attribute: Attribute,
      typed: Modifier.Type,
      value: Float
  )

  object Modifier extends Enumeration {
    type Type = Value
    val FLAT, PERCENT = Value

    def flat(attribute: Attribute, value: Float): Modifier =
      Modifier(attribute, FLAT, value)

    def percent(attribute: Attribute, value: Float): Modifier =
      Modifier(attribute, PERCENT, value)
  }

  sealed abstract class Generic(override val name: String) extends Attribute
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

  sealed abstract class Horse(override val name: String) extends Attribute
  object Horse {
    case object JUMP_STRENGTH extends Horse("Jump Strength")
  }

  sealed abstract class Zombie(override val name: String) extends Attribute
  object Zombie {
    case object SPAWN_REINFORCEMENTS extends Horse("Spawn Reinforcements")
  }
}
