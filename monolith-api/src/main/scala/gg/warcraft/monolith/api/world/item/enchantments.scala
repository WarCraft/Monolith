package gg.warcraft.monolith.api.world.item

final case class AquaInfinity(level: Int)
    extends HelmetEnchantment {
  val maxLevel = 1
}

final case class BaneOfArthropods(level: Int)
    extends SwordEnchantment
    with AxeEnchantment {
  val maxLevel = 5
}

final case class BlastProtection(level: Int)
    extends ArmorEnchantment {
  val maxLevel = 4
}

final case class Channeling(level: Int)
    extends TridentEnchantment {
  val maxLevel = 1
}

final case class CurseOfBinding(level: Int)
    extends ArmorEnchantment {
  val maxLevel = 1
} // TODO pumpkin / mob head / elytra

final case class CurseOfVanishing(level: Int)
    extends ItemEnchantment {
  val maxLevel = 1
}

final case class DepthStrider(level: Int)
    extends BootsEnchantment {
  val maxLevel = 3
}

final case class Efficiency(level: Int)
    extends AxeEnchantment
    with PickaxeEnchantment
    with ShearsEnchantment
    with ShovelEnchantment {
  val maxLevel = 5
}

final case class FeatherFalling(level: Int)
    extends BootsEnchantment {
  val maxLevel = 4
}

final case class FireAspect(level: Int)
    extends SwordEnchantment {
  val maxLevel = 2
}

final case class FireProtection(level: Int)
    extends ArmorEnchantment {
  val maxLevel = 4
}

final case class Flame(level: Int)
    extends BowEnchantment {
  val maxLevel = 1
}

final case class Fortune(level: Int)
    extends AxeEnchantment
    with PickaxeEnchantment
    with ShovelEnchantment {
  val maxLevel = 3
}

final case class FrostWalker(level: Int)
    extends BootsEnchantment {
  val maxLevel = 2
}

final case class Impaling(level: Int)
    extends TridentEnchantment {
  val maxLevel = 5
}

final case class Infinity(level: Int)
    extends BowEnchantment {
  val maxLevel = 1
}

final case class Knockback(level: Int)
    extends SwordEnchantment {
  val maxLevel = 2
}

final case class Looting(level: Int)
    extends SwordEnchantment {
  val maxLevel = 3
}

final case class Loyalty(level: Int)
    extends TridentEnchantment {
  val maxLevel = 3
}

final case class LuckOfTheSea(level: Int)
    extends FishingRodEnchantment {
  val maxLevel = 3
}

final case class Lure(level: Int)
    extends FishingRodEnchantment {
  val maxLevel = 3
}

final case class Mending(level: Int)
    extends ItemEnchantment {
  val maxLevel = 1
}

final case class Multishot(level: Int)
    extends CrossbowEnchantment {
  val maxLevel = 1
}

final case class Piercing(level: Int)
    extends CrossbowEnchantment {
  val maxLevel = 4
}

final case class Power(level: Int)
    extends BowEnchantment {
  val maxLevel = 5
}

final case class ProjectileProtection(level: Int)
    extends ArmorEnchantment {
  val maxLevel = 4
}

final case class Protection(level: Int)
    extends ArmorEnchantment {
  val maxLevel = 4
}

final case class Punch(level: Int)
    extends BowEnchantment {
  val maxLevel = 2
}

final case class QuickCharge(level: Int)
    extends CrossbowEnchantment {
  val maxLevel = 3
}

final case class Respiration(level: Int)
    extends HelmetEnchantment {
  val maxLevel = 3
}

final case class Riptide(level: Int)
    extends TridentEnchantment {
  val maxLevel = 3
}

final case class Sharpness(level: Int)
    extends SwordEnchantment
    with AxeEnchantment {
  val maxLevel = 5
}

final case class SilkTouch(level: Int)
    extends AxeEnchantment
    with PickaxeEnchantment
    with ShovelEnchantment {
  val maxLevel = 1
}

final case class Smite(level: Int)
    extends SwordEnchantment
    with AxeEnchantment {
  val maxLevel = 5
}

final case class SweepingEdge(level: Int)
    extends SwordEnchantment {
  val maxLevel = 3
}

final case class Thorns(level: Int)
    extends ArmorEnchantment {
  val maxLevel = 3
}

final case class Unbreaking(level: Int)
    extends ItemEnchantment {
  val maxLevel = 3
}
