package gg.warcraft.monolith.api.item

trait ItemEnchantment {
  val level: Int
  val maxLevel: Int
  require(level > 0 && level <= maxLevel, {
    s"level is $level, must be > 0 and <= $maxLevel"
  })
}

trait BowEnchantment extends ItemEnchantment

trait CrossbowEnchantment extends ItemEnchantment

trait FishingRodEnchantment extends ItemEnchantment

trait ShearsEnchantment extends ItemEnchantment

trait TridentEnchantment extends ItemEnchantment

// ARMOR

trait BootsEnchantment extends ItemEnchantment

trait ChestplateEnchantment extends ItemEnchantment

trait HelmetEnchantment extends ItemEnchantment

trait LeggingsEnchantment extends ItemEnchantment

trait ArmorEnchantment
    extends BootsEnchantment
    with ChestplateEnchantment
    with HelmetEnchantment
    with LeggingsEnchantment

// TOOLS

trait AxeEnchantment extends ItemEnchantment

trait HoeEnchantment extends ItemEnchantment

trait PickaxeEnchantment extends ItemEnchantment

trait ShovelEnchantment extends ItemEnchantment

trait SwordEnchantment extends ItemEnchantment

trait ToolEnchantment
    extends AxeEnchantment
    with HoeEnchantment
    with PickaxeEnchantment
    with ShovelEnchantment
    with SwordEnchantment
