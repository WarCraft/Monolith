package gg.warcraft.monolith.api.entity.attribute

object Attribute {
  type Generic = Generic.Value

  object Generic extends Enumeration {
    implicit def toVal(v: Value): Val = v.asInstanceOf[Val]
    implicit def toAttribute(v: Value): Attribute = v.asInstanceOf[Attribute]

    protected case class Val() extends super.Val with Attribute {}

    val ARMOR, ARMOR_TOUGHNESS, ATTACK_DAMAGE, ATTACK_SPEED, FLYING_SPEED,
        FOLLOW_RANGE, KNOCKBACK_RESISTANCE, LUCK, MAX_HEALTH, MOVEMENT_SPEED = Val
  }

  type Horse = Horse.Value
  object Horse extends Enumeration {
    implicit def toVal(v: Value): Val = v.asInstanceOf[Val]
    implicit def toAttribute(v: Value): Attribute = v.asInstanceOf[Attribute]

    protected case class Val() extends super.Val with Attribute {}

    val JUMP_STRENGTH = Val
  }

  type Zombie = Zombie.Value
  object Zombie extends Enumeration {
    implicit def toVal(v: Value): Val = v.asInstanceOf[Val]
    implicit def toAttribute(v: Value): Attribute = v.asInstanceOf[Attribute]

    protected case class Val() extends super.Val with Attribute {}

    val SPAWN_REINFORCEMENTS = Val
  }
}

trait Attribute {
  val id: Any
  val value: Float
}
