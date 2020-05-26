/*
 * MIT License
 *
 * Copyright (c) 2020 WarCraft <https://github.com/WarCraft>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package gg.warcraft.monolith.spigot.item

import java.util

import gg.warcraft.monolith.api.block.variant._
import gg.warcraft.monolith.api.item._
import gg.warcraft.monolith.api.item.variant.{StructureBlockVariant, _}
import gg.warcraft.monolith.spigot.Extensions._
import org.bukkit.{Material, NamespacedKey}
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.meta.Damageable
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.Plugin

import scala.jdk.CollectionConverters._

private object SpigotItemMapper {
  private final val cache: util.EnumMap[Material, SpigotItem => Option[Item]] =
    new util.EnumMap(classOf[Material])
}

class SpigotItemMapper(
    private implicit val plugin: Plugin,
    private implicit val typeMapper: SpigotItemTypeMapper,
    private implicit val variantMapper: SpigotItemVariantMapper
) {
  private val itemNameKey = new NamespacedKey(plugin, "canonicalDisplayName")

  def map(item: SpigotItem): Option[Item] = {
    if (item == null) return None
    val builder = SpigotItemMapper.cache.computeIfAbsent(item.getType, compute)
    builder.apply(item)
  }

  private def compute(material: Material): SpigotItem => Option[Item] = {
    if (material.name.endsWith("AIR")) return _ => None

    // Compute common item data TODO map default name and item attributes
    val name                            = (item: SpigotItem) => {
      val meta = item.getItemMeta
      if (meta != null) {
        val data = meta.getPersistentDataContainer
        if (data.has(itemNameKey, PersistentDataType.STRING)) {
          data.get(itemNameKey, PersistentDataType.STRING)
        } else meta.getDisplayName
      } else ""
    }
    val tt: SpigotItem => List[String]  = (item: SpigotItem) => {
      val meta = item.getItemMeta
      if (meta != null && meta.getLore != null) meta.getLore.asScala.toList
      else List.empty
    }
    val attr: SpigotItem => Set[String] = (item: SpigotItem) => {
      val meta = item.getItemMeta
      if (meta != null) Set.empty else Set.empty
    }
    val hAttr                           = (item: SpigotItem) => {
      val meta = item.getItemMeta
      if (meta != null) meta.hasItemFlag(ItemFlag.HIDE_ATTRIBUTES) else false
    }

    // Lazily compute generic item data
    lazy val count = (item: SpigotItem) => item.getAmount
    lazy val dura = (item: SpigotItem) => {
      val meta = item.getItemMeta
      val damage = if (meta != null) meta.asInstanceOf[Damageable].getDamage else 0
      item.getType.getMaxDurability - damage
    }
    lazy val unbr = (item: SpigotItem) => {
      val meta = item.getItemMeta
      if (meta != null) meta.isUnbreakable else false
    }
    lazy val hUnbr = (item: SpigotItem) => {
      val meta = item.getItemMeta
      if (meta != null) meta.hasItemFlag(ItemFlag.HIDE_UNBREAKABLE) else false
    }
    def variant[T <: ItemVariant](item: SpigotItem): T =
      variantMapper.map(item.getType).asInstanceOf[T]

    // Lazily construct tuples for all the different types of parameter sets
    lazy val p = (item: SpigotItem) => // default params tuple builder
      (name(item), tt(item), count(item), attr(item), hAttr(item))
    lazy val s = (item: SpigotItem) => // singleton params tuple builder
      (name(item), tt(item), attr(item), hAttr(item))
    lazy val d = (i: SpigotItem) => // durable params tuple builder
      (name(i), tt(i), attr(i), hAttr(i), dura(i), unbr(i), hUnbr(i))
    def v[T <: ItemVariant](i: SpigotItem) = // variable params tuple builder
    (variant[T](i), name(i), tt(i), count(i), attr(i), hAttr(i))
    def e[T <: ItemVariant](i: SpigotItem) = // equipment params tuple builder
    (variant[T](i), name(i), tt(i), attr(i), hAttr(i), dura(i), unbr(i), hUnbr(i))

    // Map item builder
    val builder: SpigotItem => Item = material match {
      case Material.APPLE                  => (i => Apple.tupled(p(i)))
      case Material.ARMOR_STAND            => (i => ArmorStand.tupled(p(i)))
      case Material.BAMBOO                 => (i => Bamboo.tupled(p(i)))
      case Material.BARREL                 => (i => Barrel.tupled(p(i)))
      case Material.BARRIER                => (i => Barrier.tupled(p(i)))
      case Material.BEACON                 => (i => Beacon.tupled(p(i)))
      case Material.BEDROCK                => (i => Bedrock.tupled(p(i)))
      case Material.BEE_NEST               => (i => BeeNest.tupled(p(i)))
      case Material.BEEHIVE                => (i => Beehive.tupled(p(i)))
      case Material.BEETROOT               => (i => Beetroot.tupled(p(i)))
      case Material.BELL                   => (i => Bell.tupled(p(i)))
      case Material.BLAST_FURNACE          => (i => BlastFurnace.tupled(p(i)))
      case Material.BLAZE_POWDER           => (i => BlazeRod.tupled(p(i)))
      case Material.BLAZE_ROD              => (i => BlazeRod.tupled(p(i)))
      case Material.BONE                   => (i => Bone.tupled(p(i)))
      case Material.BONE_BLOCK             => (i => BoneBlock.tupled(p(i)))
      case Material.BONE_MEAL              => (i => BoneMeal.tupled(p(i)))
      case Material.BOOK                   => (i => Book.tupled(p(i)))
      case Material.BOOKSHELF              => (i => Bookshelf.tupled(p(i)))
      case Material.BOW                    => (i => Bow.tupled(d(i)))
      case Material.BOWL                   => (i => Bowl.tupled(p(i)))
      case Material.BREAD                  => (i => Bread.tupled(p(i)))
      case Material.BREWING_STAND          => (i => BrewingStand.tupled(p(i)))
      case Material.CACTUS                 => (i => Cactus.tupled(p(i)))
      case Material.CAKE                   => (i => Cake.tupled(s(i)))
      case Material.CAMPFIRE               => (i => Campfire.tupled(p(i)))
      case Material.CARROT                 => (i => Carrot.tupled(p(i)))
      case Material.CARROT_ON_A_STICK      => (i => CarrotOnAStick.tupled(d(i)))
      case Material.CARTOGRAPHY_TABLE      => (i => CartographyTable.tupled(p(i)))
      case Material.CAULDRON               => (i => Cauldron.tupled(p(i)))
      case Material.CHARCOAL               => (i => Charcoal.tupled(p(i)))
      case Material.CHORUS_FLOWER          => (i => ChorusFlower.tupled(p(i)))
      case Material.CHORUS_PLANT           => (i => ChorusPlant.tupled(p(i)))
      case Material.CLAY                   => (i => ClayBlock.tupled(p(i)))
      case Material.CLAY_BALL              => (i => Clay.tupled(p(i)))
      case Material.CLOCK                  => (i => Clock.tupled(p(i)))
      case Material.COAL                   => (i => Coal.tupled(p(i)))
      case Material.COAL_BLOCK             => (i => CoalBlock.tupled(p(i)))
      case Material.COAL_ORE               => (i => CoalOre.tupled(p(i)))
      case Material.COBWEB                 => (i => Cobweb.tupled(p(i)))
      case Material.COCOA_BEANS            => (i => CocoaBeans.tupled(p(i)))
      case Material.COMPARATOR             => (i => Comparator.tupled(p(i)))
      case Material.COMPASS                => (i => Compass.tupled(p(i)))
      case Material.COMPOSTER              => (i => Composter.tupled(p(i)))
      case Material.CONDUIT                => (i => Conduit.tupled(p(i)))
      case Material.COOKIE                 => (i => Cookie.tupled(p(i)))
      case Material.CRAFTING_TABLE         => (i => CraftingTable.tupled(p(i)))
      case Material.CROSSBOW               => (i => Crossbow.tupled(d(i)))
      case Material.DAYLIGHT_DETECTOR      => (i => DaylightDetector.tupled(p(i)))
      case Material.DEAD_BUSH              => (i => DeadBush.tupled(p(i)))
      case Material.DEBUG_STICK            => (i => DebugStick.tupled(s(i)))
      case Material.DIAMOND                => (i => Diamond.tupled(p(i)))
      case Material.DIAMOND_BLOCK          => (i => DiamondBlock.tupled(p(i)))
      case Material.DIAMOND_ORE            => (i => DiamondOre.tupled(p(i)))
      case Material.DISPENSER              => (i => Dispenser.tupled(p(i)))
      case Material.DRAGON_BREATH          => (i => DragonBreath.tupled(p(i)))
      case Material.DRAGON_EGG             => (i => DragonEgg.tupled(p(i)))
      case Material.DRIED_KELP             => (i => DriedKelp.tupled(p(i)))
      case Material.DRIED_KELP_BLOCK       => (i => DriedKelpBlock.tupled(p(i)))
      case Material.DROPPER                => (i => Dropper.tupled(p(i)))
      case Material.EGG                    => (i => Egg.tupled(p(i)))
      case Material.ELYTRA                 => (i => Elytra.tupled(d(i)))
      case Material.EMERALD                => (i => Emerald.tupled(p(i)))
      case Material.EMERALD_BLOCK          => (i => EmeraldBlock.tupled(p(i)))
      case Material.EMERALD_ORE            => (i => EmeraldOre.tupled(p(i)))
      case Material.ENCHANTED_BOOK         => (i => EnchantedBook.tupled(s(i)))
      case Material.ENCHANTING_TABLE       => (i => EnchantingTable.tupled(p(i)))
      case Material.ENDER_EYE              => (i => EnderEye.tupled(p(i)))
      case Material.ENDER_PEARL            => (i => EnderPearl.tupled(p(i)))
      case Material.END_CRYSTAL            => (i => EndCrystal.tupled(p(i)))
      case Material.END_PORTAL_FRAME       => (i => EndPortalFrame.tupled(p(i)))
      case Material.END_ROD                => (i => EndRod.tupled(p(i)))
      case Material.END_STONE              => (i => EndStone.tupled(p(i)))
      case Material.END_STONE_BRICKS       => (i => EndStoneBrick.tupled(p(i)))
      case Material.EXPERIENCE_BOTTLE      => (i => BottleOfEnchanting.tupled(p(i)))
      case Material.FARMLAND               => (i => Farmland.tupled(p(i)))
      case Material.FEATHER                => (i => Feather.tupled(p(i)))
      case Material.FISHING_ROD            => (i => FishingRod.tupled(d(i)))
      case Material.FIREWORK_ROCKET        => (i => FireworkRocket.tupled(p(i)))
      case Material.FIRE_CHARGE            => (i => FireCharge.tupled(p(i)))
      case Material.FLETCHING_TABLE        => (i => FletchingTable.tupled(p(i)))
      case Material.FLINT                  => (i => Flint.tupled(p(i)))
      case Material.FLINT_AND_STEEL        => (i => FlintAndSteel.tupled(d(i)))
      case Material.FLOWER_POT             => (i => FlowerPot.tupled(p(i)))
      case Material.FURNACE                => (i => Furnace.tupled(p(i)))
      case Material.GHAST_TEAR             => (i => GhastTear.tupled(p(i)))
      case Material.GLASS_BOTTLE           => (i => GlassBottle.tupled(p(i)))
      case Material.GLISTERING_MELON_SLICE => (i => GoldenMelonSlice.tupled(p(i)))
      case Material.GLOWSTONE              => (i => Glowstone.tupled(p(i)))
      case Material.GLOWSTONE_DUST         => (i => GlowstoneDust.tupled(p(i)))
      case Material.GOLDEN_CARROT          => (i => GoldenCarrot.tupled(p(i)))
      case Material.GOLD_BLOCK             => (i => GoldBlock.tupled(p(i)))
      case Material.GOLD_INGOT             => (i => GoldIngot.tupled(p(i)))
      case Material.GOLD_NUGGET            => (i => GoldNugget.tupled(p(i)))
      case Material.GOLD_ORE               => (i => GoldOre.tupled(p(i)))
      case Material.GRASS_BLOCK            => (i => GrassBlock.tupled(p(i)))
      case Material.GRASS_PATH             => (i => GrassPath.tupled(p(i)))
      case Material.GRAVEL                 => (i => Gravel.tupled(p(i)))
      case Material.GRINDSTONE             => (i => Grindstone.tupled(p(i)))
      case Material.GUNPOWDER              => (i => Gunpowder.tupled(p(i)))
      case Material.HAY_BLOCK              => (i => HayBale.tupled(p(i)))
      case Material.HEART_OF_THE_SEA       => (i => HeartOfTheSea.tupled(p(i)))
      case Material.HONEY_BLOCK            => (i => HoneyBlock.tupled(p(i)))
      case Material.HONEYCOMB              => (i => Honeycomb.tupled(p(i)))
      case Material.HONEYCOMB_BLOCK        => (i => HoneycombBlock.tupled(p(i)))
      case Material.HOPPER                 => (i => Hopper.tupled(p(i)))
      case Material.INK_SAC                => (i => InkSac.tupled(p(i)))
      case Material.IRON_BARS              => (i => IronBars.tupled(p(i)))
      case Material.IRON_BLOCK             => (i => IronBlock.tupled(p(i)))
      case Material.IRON_INGOT             => (i => IronIngot.tupled(p(i)))
      case Material.IRON_NUGGET            => (i => IronNugget.tupled(p(i)))
      case Material.IRON_ORE               => (i => IronOre.tupled(p(i)))
      case Material.ITEM_FRAME             => (i => ItemFrame.tupled(p(i)))
      case Material.JACK_O_LANTERN         => (i => JackOfTheLantern.tupled(p(i)))
      case Material.JIGSAW                 => (i => JigsawBlock.tupled(p(i)))
      case Material.JUKEBOX                => (i => Jukebox.tupled(p(i)))
      case Material.KELP                   => (i => Kelp.tupled(p(i)))
      case Material.KNOWLEDGE_BOOK         => (i => KnowledgeBook.tupled(s(i)))
      case Material.LADDER                 => (i => Ladder.tupled(p(i)))
      case Material.LANTERN                => (i => Lantern.tupled(p(i)))
      case Material.LAPIS_BLOCK            => (i => LapisBlock.tupled(p(i)))
      case Material.LAPIS_LAZULI           => (i => Lapis.tupled(p(i)))
      case Material.LAPIS_ORE              => (i => LapisOre.tupled(p(i)))
      case Material.LEAD                   => (i => Lead.tupled(p(i)))
      case Material.LEATHER                => (i => Leather.tupled(p(i)))
      case Material.LECTERN                => (i => Lectern.tupled(p(i)))
      case Material.LEVER                  => (i => Lever.tupled(p(i)))
      case Material.LILY_PAD               => (i => LilyPad.tupled(p(i)))
      case Material.LOOM                   => (i => Loom.tupled(p(i)))
      case Material.MAGMA_BLOCK            => (i => MagmaBlock.tupled(p(i)))
      case Material.MAGMA_CREAM            => (i => MagmaCream.tupled(p(i)))
      case Material.MELON                  => (i => Melon.tupled(p(i)))
      case Material.MELON_SLICE            => (i => MelonSlice.tupled(p(i)))
      case Material.MYCELIUM               => (i => Mycelium.tupled(p(i)))
      case Material.NAME_TAG               => (i => NameTag.tupled(p(i)))
      case Material.NAUTILUS_SHELL         => (i => NautilusShell.tupled(p(i)))
      case Material.NETHERRACK             => (i => Netherrack.tupled(p(i)))
      case Material.NETHER_QUARTZ_ORE      => (i => QuartzOre.tupled(p(i)))
      case Material.NETHER_STAR            => (i => NetherStar.tupled(p(i)))
      case Material.NETHER_WART            => (i => NetherWart.tupled(p(i)))
      case Material.NETHER_WART_BLOCK      => (i => NetherWartBlock.tupled(p(i)))
      case Material.NOTE_BLOCK             => (i => NoteBlock.tupled(p(i)))
      case Material.OBSERVER               => (i => Observer.tupled(p(i)))
      case Material.OBSIDIAN               => (i => Obsidian.tupled(p(i)))
      case Material.PAINTING               => (i => Painting.tupled(p(i)))
      case Material.PAPER                  => (i => Paper.tupled(p(i)))
      case Material.PHANTOM_MEMBRANE       => (i => PhantomMembrane.tupled(p(i)))
      case Material.PODZOL                 => (i => Podzol.tupled(p(i)))
      case Material.PRISMARINE_CRYSTALS    => (i => PrismarineCrystals.tupled(p(i)))
      case Material.PRISMARINE_SHARD       => (i => PrismarineShard.tupled(p(i)))
      case Material.PUFFERFISH             => (i => Pufferfish.tupled(p(i)))
      case Material.PUMPKIN_PIE            => (i => PumpkinPie.tupled(p(i)))
      case Material.PURPUR_BLOCK           => (i => PurpurBlock.tupled(p(i)))
      case Material.QUARTZ                 => (i => Quartz.tupled(p(i)))
      case Material.RABBIT_FOOT            => (i => RabbitFoot.tupled(p(i)))
      case Material.RABBIT_HIDE            => (i => RabbitHide.tupled(p(i)))
      case Material.REDSTONE               => (i => Redstone.tupled(p(i)))
      case Material.REDSTONE_BLOCK         => (i => RedstoneBlock.tupled(p(i)))
      case Material.REDSTONE_LAMP          => (i => RedstoneLamp.tupled(p(i)))
      case Material.REDSTONE_ORE           => (i => RedstoneOre.tupled(p(i)))
      case Material.REDSTONE_TORCH         => (i => RedstoneTorch.tupled(p(i)))
      case Material.REPEATER               => (i => Repeater.tupled(p(i)))
      case Material.ROTTEN_FLESH           => (i => RottenFlesh.tupled(p(i)))
      case Material.SADDLE                 => (i => Saddle.tupled(s(i)))
      case Material.SCAFFOLDING            => (i => Scaffolding.tupled(p(i)))
      case Material.SCUTE                  => (i => Scute.tupled(p(i)))
      case Material.SEAGRASS               => (i => Seagrass.tupled(p(i)))
      case Material.SEA_LANTERN            => (i => SeaLantern.tupled(p(i)))
      case Material.SEA_PICKLE             => (i => SeaPickle.tupled(p(i)))
      case Material.SHEARS                 => (i => Shears.tupled(d(i)))
      case Material.SHIELD                 => (i => Shield.tupled(d(i)))
      case Material.SHULKER_SHELL          => (i => ShulkerShell.tupled(p(i)))
      case Material.SLIME_BALL             => (i => Slimeball.tupled(p(i)))
      case Material.SLIME_BLOCK            => (i => SlimeBlock.tupled(p(i)))
      case Material.SMITHING_TABLE         => (i => SmithingTable.tupled(p(i)))
      case Material.SMOKER                 => (i => Smoker.tupled(p(i)))
      case Material.SNOW                   => (i => Snow.tupled(p(i)))
      case Material.SNOWBALL               => (i => Snowball.tupled(p(i)))
      case Material.SNOW_BLOCK             => (i => SnowBlock.tupled(p(i)))
      case Material.SOUL_SAND              => (i => SoulSand.tupled(p(i)))
      case Material.SPAWNER                => (i => Spawner.tupled(p(i)))
      case Material.STICK                  => (i => Stick.tupled(p(i)))
      case Material.STONECUTTER            => (i => Stonecutter.tupled(p(i)))
      case Material.STRING                 => (i => PieceOfString.tupled(p(i)))
      case Material.SUGAR                  => (i => Sugar.tupled(p(i)))
      case Material.SUGAR_CANE             => (i => SugarCane.tupled(p(i)))
      case Material.SWEET_BERRIES          => (i => SweetBerries.tupled(p(i)))
      case Material.TNT                    => (i => TNT.tupled(p(i)))
      case Material.TORCH                  => (i => Torch.tupled(p(i)))
      case Material.TOTEM_OF_UNDYING       => (i => TotemOfUndying.tupled(s(i)))
      case Material.TRIDENT                => (i => Trident.tupled(d(i)))
      case Material.TRIPWIRE_HOOK          => (i => TripwireHook.tupled(p(i)))
      case Material.TROPICAL_FISH          => (i => TropicalFish.tupled(p(i)))
      case Material.TURTLE_EGG             => (i => TurtleEgg.tupled(p(i)))
      case Material.TURTLE_HELMET          => (i => TurtleHelmet.tupled(d(i)))
      case Material.VINE                   => (i => Vines.tupled(p(i)))
      case Material.WHEAT                  => (i => Wheat.tupled(p(i)))
      case Material.WRITABLE_BOOK          => (i => BookAndQuill.tupled(s(i)))
      case Material.WRITTEN_BOOK           => (i => WrittenBook.tupled(p(i)))

      // FIREWORK_STAR TODO retrieve actual color from tooltip data
      case Material.FIREWORK_STAR =>
        i =>
          val variant = FireworkStarVariant.RED
          FireworkStar(variant, name(i), tt(i), count(i), attr(i), hAttr(i))

      // GOLDEN_APPLE TODO move ENCHANTED and CARVED onto Apple/PumpkinVariant?
      case Material.GOLDEN_APPLE =>
        i =>
          GoldenApple(enchanted = false, name(i), tt(i), count(i), attr(i), hAttr(i))

      case Material.ENCHANTED_GOLDEN_APPLE =>
        i =>
          GoldenApple(enchanted = true, name(i), tt(i), count(i), attr(i), hAttr(i))

        // MAP
      case Material.MAP =>
        (i => Map(filled = false, name(i), tt(i), count(i), attr(i), hAttr(i)))

      // TODO does a filled map have lots of extra data? Make own item?
      case Material.FILLED_MAP =>
        (i => Map(filled = true, name(i), tt(i), count(i), attr(i), hAttr(i)))

      // PUMPKIN
      case Material.PUMPKIN =>
        (i => Pumpkin(carved = false, name(i), tt(i), count(i), attr(i), hAttr(i)))

      case Material.CARVED_PUMPKIN =>
        (i => Pumpkin(carved = true, name(i), tt(i), count(i), attr(i), hAttr(i)))

      case m if m.isAndesite   => (i => Andesite.tupled(v[AndesiteVariant](i)))
      case m if m.isAnvil      => (i => Anvil.tupled(v[AnvilVariant](i)))
      case m if m.isArrow      => (i => Arrow.tupled(v[ArrowVariant](i)))
      case m if m.isAxe        => (i => Axe.tupled(e[AxeVariant](i)))
      case m if m.isBanner     => (i => Banner.tupled(v[BannerVariant](i)))
      case m if m.isBed        => (i => Bed.tupled(v[BedVariant](i)))
      case m if m.isBeef       => (i => Beef.tupled(v[BeefVariant](i)))
      case m if m.isBoat       => (i => Boat.tupled(v[BoatVariant](i)))
      case m if m.isBoots      => (i => Boots.tupled(e[BootsVariant](i)))
      case m if m.isBrick      => (i => Brick.tupled(v[BrickVariant](i)))
      case m if m.isBrickBlock => (i => BrickBlock.tupled(v[BrickBlockVariant](i)))
      case m if m.isBucket     => (i => Bucket.tupled(v[BucketVariant](i)))
      case m if m.isButton     => (i => Button.tupled(v[ButtonVariant](i)))
      case m if m.isCarpet     => (i => Carpet.tupled(v[CarpetVariant](i)))
      case m if m.isChest      => (i => Chest.tupled(v[ChestVariant](i)))
      case m if m.isChestplate => (i => Chestplate.tupled(e[ChestplateVariant](i)))
      case m if m.isChicken    => (i => Chicken.tupled(v[ChickenVariant](i)))
      case m if m.isCod        => (i => Cod.tupled(v[CodVariant](i)))
      case m if m.isConcrete   => (i => Concrete.tupled(v[ConcreteVariant](i)))
      case m if m.isCoral      => (i => Coral.tupled(v[CoralVariant](i)))
      case m if m.isCoralBlock => (i => CoralBlock.tupled(v[CoralBlockVariant](i)))
      case m if m.isCoralFan   => (i => CoralFan.tupled(v[CoralFanVariant](i)))
      case m if m.isDiorite    => (i => Diorite.tupled(v[DioriteVariant](i)))
      case m if m.isDirt       => (i => Dirt.tupled(v[DirtVariant](i)))
      case m if m.isDoor       => (i => Door.tupled(v[DoorVariant](i)))
      case m if m.isDye        => (i => Dye.tupled(v[DyeVariant](i)))
      case m if m.isFence      => (i => Fence.tupled(v[FenceVariant](i)))
      case m if m.isFenceGate  => (i => FenceGate.tupled(v[FenceGateVariant](i)))
      case m if m.isFern       => (i => Fern.tupled(v[FernVariant](i)))
      case m if m.isFlower     => (i => Flower.tupled(v[FlowerVariant](i)))
      case m if m.isGlass      => (i => Glass.tupled(v[GlassVariant](i)))
      case m if m.isGlassPane  => (i => GlassPane.tupled(v[GlassPaneVariant](i)))
      case m if m.isGranite    => (i => Granite.tupled(v[GraniteVariant](i)))
      case m if m.isGrass      => (i => Grass.tupled(v[GrassVariant](i)))
      case m if m.isHelmet     => (i => Helmet.tupled(e[HelmetVariant](i)))
      case m if m.isHoe        => (i => Hoe.tupled(e[HoeVariant](i)))
      case m if m.isIce        => (i => Ice.tupled(v[IceVariant](i)))
      case m if m.isLeaves     => (i => Leaves.tupled(v[LeavesVariant](i)))
      case m if m.isLeggings   => (i => Leggings.tupled(e[LeggingsVariant](i)))
      case m if m.isLog        => (i => Log.tupled(v[LogVariant](i)))
      case m if m.isMinecart   => (i => Minecart.tupled(v[MinecartVariant](i)))
      case m if m.isMobHead    => (i => MobHead.tupled(v[MobHeadVariant](i)))
      case m if m.isMushroom   => (i => Mushroom.tupled(v[MushroomVariant](i)))
      case m if m.isMusicDisc  => (i => MusicDisc.tupled(v[MusicDiscVariant](i)))
      case m if m.isMutton     => (i => Mutton.tupled(v[MuttonVariant](i)))
      case m if m.isPickaxe    => (i => Pickaxe.tupled(e[PickaxeVariant](i)))
      case m if m.isPillar     => (i => Pillar.tupled(v[PillarVariant](i)))
      case m if m.isPiston     => (i => Piston.tupled(v[PistonVariant](i)))
      case m if m.isPlanks     => (i => Planks.tupled(v[PlanksVariant](i)))
      case m if m.isPlant      => (i => Plant.tupled(v[PlantVariant](i)))
      case m if m.isPorkchop   => (i => Porkchop.tupled(v[PorkchopVariant](i)))
      case m if m.isPotato     => (i => Potato.tupled(v[PotatoVariant](i)))
      case m if m.isPrismarine => (i => Prismarine.tupled(v[PrismarineVariant](i)))
      case m if m.isRabbit     => (i => Rabbit.tupled(v[RabbitVariant](i)))
      case m if m.isRail       => (i => Rail.tupled(v[RailVariant](i)))
      case m if m.isSalmon     => (i => Salmon.tupled(v[SalmonVariant](i)))
      case m if m.isSand       => (i => Sand.tupled(v[SandVariant](i)))
      case m if m.isSandstone  => (i => Sandstone.tupled(v[SandstoneVariant](i)))
      case m if m.isSapling    => (i => Sapling.tupled(v[SaplingVariant](i)))
      case m if m.isSeeds      => (i => Seeds.tupled(v[SeedsVariant](i)))
      case m if m.isShulkerBox => (i => ShulkerBox.tupled(v[ShulkerBoxVariant](i)))
      case m if m.isShovel     => (i => Shovel.tupled(e[ShovelVariant](i)))
      case m if m.isSign       => (i => Sign.tupled(v[SignVariant](i)))
      case m if m.isSlab       => (i => Slab.tupled(v[SlabVariant](i)))
      case m if m.isSpawnEgg   => (i => SpawnEgg.tupled(v[SpawnEggVariant](i)))
      case m if m.isSpiderEye  => (i => SpiderEye.tupled(v[SpiderEyeVariant](i)))
      case m if m.isSponge     => (i => Sponge.tupled(v[SpongeVariant](i)))
      case m if m.isStairs     => (i => Stairs.tupled(v[StairsVariant](i)))
      case m if m.isStew       => (i => Stew.tupled(v[StewVariant](i)))
      case m if m.isStone      => (i => Stone.tupled(v[StoneVariant](i)))
      case m if m.isStoneBrick => (i => StoneBrick.tupled(v[StoneBrickVariant](i)))
      case m if m.isSword      => (i => Sword.tupled(e[SwordVariant](i)))
      case m if m.isTerracotta => (i => Terracotta.tupled(v[TerracottaVariant](i)))
      case m if m.isTrapdoor   => (i => Trapdoor.tupled(v[TrapdoorVariant](i)))
      case m if m.isWall       => (i => Wall.tupled(v[WallVariant](i)))
      case m if m.isWood       => (i => Wood.tupled(v[WoodVariant](i)))
      case m if m.isWool       => (i => Wool.tupled(v[WoolVariant](i)))

      // materials that were pushing the alignment above too far to the right
      case m if m.isChorusFruit =>
        (item => ChorusFruit.tupled(v[ChorusFruitVariant](item)))
      case m if m.isCobblestone =>
        (item => Cobblestone.tupled(v[CobblestoneVariant](item)))
      case m if m.isCommandBlock =>
        (item => CommandBlock.tupled(v[CommandBlockVariant](item)))
      case m if m.isConcretePowder =>
        (item => ConcretePowder.tupled(v[ConcretePowderVariant](item)))
      case m if m.isInfestedBlock =>
        (item => InfestedBlock.tupled(v[InfestedBlockVariant](item)))
      case m if m.isMushroomBlock =>
        (item => MushroomBlock.tupled(v[MushroomBlockVariant](item)))
      case m if m.isPressurePlate =>
        (item => PressurePlate.tupled(v[PressurePlateVariant](item)))
      case m if m.isQuartzBlock =>
        (item => QuartzBlock.tupled(v[QuartzBlockVariant](item)))
      case m if m.isStructureBlock =>
        (item => StructureBlock.tupled(v[StructureBlockVariant](item)))

      // materials that were simply too long all together
      case m if m.isBannerPattern =>
        item =>
          val v = variant[BannerPatternVariant](item)
          BannerPattern(v, name(item), tt(item), attr(item), hAttr(item))

      case m if m.isGlazedTerracotta =>
        (item => GlazedTerracotta.tupled(v[GlazedTerracottaVariant](item)))

      case m if m.isHorseArmor =>
        item =>
          val v = variant[HorseArmorVariant](item)
          HorseArmor(v, name(item), tt(item), attr(item), hAttr(item))

      case m if m.isPotion =>
        item =>
          val v = variant[PotionVariant](item)
          val meta = item.getItemMeta
          val hideEffects =
            if (meta != null) meta.hasItemFlag(ItemFlag.HIDE_POTION_EFFECTS)
            else false
          Potion(v, hideEffects, name(item), tt(item), attr(item), hAttr(item))

      case m if m.isWeightedPressurePlate =>
        (item => WeightedPressurePlate.tupled(v[WeightedPressurePlateVariant](item)))
    }

    (item: SpigotItem) => Option(builder.apply(item))
  }

  def map(item: Item): SpigotItem = {
    if (item == null) return new SpigotItem(Material.AIR)

    val material = item match {
      case it: VariableItem[_] => variantMapper.map(it)

      case it: GoldenApple =>
        if (it.enchanted) Material.ENCHANTED_GOLDEN_APPLE
        else Material.GOLDEN_APPLE

      case it: Map =>
        if (it.filled) Material.FILLED_MAP
        else Material.MAP

      case it: Pumpkin =>
        if (it.carved) Material.CARVED_PUMPKIN
        else Material.PUMPKIN

      case it => typeMapper.map(it.`type`)
    }

    val spigotItem = new SpigotItem(material)

    // Update stack size
    item match {
      case it: StackableItem => spigotItem.setAmount(it.count)
      case _                 => ()
    }

    // Update item meta
    val meta = spigotItem.getItemMeta
    if (item.name.contains("§")) {
      val data = meta.getPersistentDataContainer
      data.set(itemNameKey, PersistentDataType.STRING, item.name)
      meta.setDisplayName(item.name)
    } else meta.setDisplayName(item.name)
    meta.setLore(item.tooltip.asJava)
    if (item.hideAttributes) meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
    spigotItem.setItemMeta(meta)

    // TODO map skull meta

    spigotItem
  }
}
