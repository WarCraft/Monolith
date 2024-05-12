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

import gg.warcraft.monolith.api.block.variant._
import gg.warcraft.monolith.api.core.Color
import gg.warcraft.monolith.api.item._
import gg.warcraft.monolith.api.item.variant.{StructureBlockVariant, _}
import gg.warcraft.monolith.spigot.Extensions._
import gg.warcraft.monolith.spigot.core.SpigotColorMapper
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.meta.{Damageable, LeatherArmorMeta}
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.Plugin
import org.bukkit.{Material, NamespacedKey}

import java.util
import scala.jdk.CollectionConverters._

private object SpigotItemMapper {
  private final val cache: util.EnumMap[
    Material,
    ((SpigotItem, Option[ItemVariant])) => Option[Item]
  ] = new util.EnumMap(classOf[Material])
}

class SpigotItemMapper(
    private implicit val plugin: Plugin,
    private implicit val typeMapper: SpigotItemTypeMapper,
    private implicit val variantMapper: SpigotItemVariantMapper,
    private implicit val colorMapper: SpigotColorMapper
) {
  private val itemNameKey = new NamespacedKey(plugin, "canonicalDisplayName")

  def map(
      item: SpigotItem,
      overrideVariant: Option[ItemVariant] = None
  ): Option[Item] = {
    if (item == null) return None
    val builder = SpigotItemMapper.cache.computeIfAbsent(item.getType, compute)
    builder.apply(item, overrideVariant)
  }

  private def compute(
      material: Material
  ): ((SpigotItem, Option[ItemVariant])) => Option[Item] = {
    if (material.name.endsWith("AIR")) return _ => None

    // Compute common item data TODO map default name and item attributes, make new itemStack and use from there?
    val name = (itemAndVariantOverride: (SpigotItem, Option[ItemVariant])) => {
      val item = itemAndVariantOverride._1
      val meta = item.getItemMeta
      if (meta != null) {
        val data = meta.getPersistentDataContainer
        if (data.has(itemNameKey, PersistentDataType.STRING)) {
          data.get(itemNameKey, PersistentDataType.STRING)
        } else if (meta.hasDisplayName) {
          meta.getDisplayName
        } else item.getI18NDisplayName
      } else item.getI18NDisplayName
    }
    val tt: ((SpigotItem, Option[ItemVariant])) => List[String] =
      (itemAndVariantOverride: (SpigotItem, Option[ItemVariant])) => {
        val meta = itemAndVariantOverride._1.getItemMeta
        if (meta != null && meta.getLore != null) meta.getLore.asScala.toList
        else List.empty
      }
    val attr: ((SpigotItem, Option[ItemVariant])) => Set[String] =
      (itemAndVariantOverride: (SpigotItem, Option[ItemVariant])) => {
        val meta = itemAndVariantOverride._1.getItemMeta
        if (meta != null) Set.empty else Set.empty
      }
    val hAttr = (itemAndVariantOverride: (SpigotItem, Option[ItemVariant])) => {
      val meta = itemAndVariantOverride._1.getItemMeta
      if (meta != null) meta.hasItemFlag(ItemFlag.HIDE_ATTRIBUTES) else false
    }

    // Lazily compute generic item data
    lazy val count = (itemAndVariantOverride: (SpigotItem, Option[ItemVariant])) =>
      itemAndVariantOverride._1.getAmount
    lazy val dura = (itemAndVariantOverride: (SpigotItem, Option[ItemVariant])) => {
      val item = itemAndVariantOverride._1
      val meta = item.getItemMeta
      val damage = if (meta != null) meta.asInstanceOf[Damageable].getDamage else 0
      item.getType.getMaxDurability - damage
    }
    lazy val unbr = (itemAndVariantOverride: (SpigotItem, Option[ItemVariant])) => {
      val meta = itemAndVariantOverride._1.getItemMeta
      if (meta != null) meta.isUnbreakable else false
    }
    lazy val hUnbr = (itemAndVariantOverride: (SpigotItem, Option[ItemVariant])) => {
      val meta = itemAndVariantOverride._1.getItemMeta
      if (meta != null) meta.hasItemFlag(ItemFlag.HIDE_UNBREAKABLE) else false
    }
    lazy val mdl = (itemAndVariantOverride: (SpigotItem, Option[ItemVariant])) => {
      val meta = itemAndVariantOverride._1.getItemMeta
      if (meta != null && meta.hasCustomModelData) Some(meta.getCustomModelData)
      else None
    }
    def variant[T <: ItemVariant](itemAndVariantOverride: (
        SpigotItem,
        Option[ItemVariant]
    )): T =
      itemAndVariantOverride._2 match {
        case Some(variant) => variant.asInstanceOf[T]
        case None =>
          val item = itemAndVariantOverride._1
          variantMapper.map(item).asInstanceOf[T]
      }

    // Lazily construct tuples for all the different types of parameter sets
    // default params tuple builder
    lazy val p = (i: (SpigotItem, Option[ItemVariant])) =>
      (name(i), tt(i), count(i), attr(i), hAttr(i), mdl(i))
    // singleton params tuple builder
    lazy val s = (i: (SpigotItem, Option[ItemVariant])) =>
      (name(i), tt(i), attr(i), hAttr(i), mdl(i))
    // durable params tuple builder
    lazy val d = (i: (SpigotItem, Option[ItemVariant])) =>
      (name(i), tt(i), attr(i), hAttr(i), mdl(i), dura(i), unbr(i), hUnbr(i))
    // variable params tuple builder
    def v[T <: ItemVariant](i: (SpigotItem, Option[ItemVariant])) =
      (variant[T](i), name(i), tt(i), count(i), attr(i), hAttr(i), mdl(i))
    // equipment params tuple builder
    def e[T <: ItemVariant](i: (SpigotItem, Option[ItemVariant])) =
      (
        variant[T](i),
        name(i),
        tt(i),
        attr(i),
        hAttr(i),
        mdl(i),
        dura(i),
        unbr(i),
        hUnbr(i)
      )

    // TODO simplify this massive match by grabbing class name from Material string?
    // Map item builder
    val builder: ((SpigotItem, Option[ItemVariant])) => Item = material match {
      case Material.AMETHYST_BLOCK      => (i => AmethystBlock.tupled(p(i)))
      case Material.AMETHYST_SHARD      => (i => AmethystShard.tupled(p(i)))
      case Material.ANCIENT_DEBRIS      => (i => AncientDebris.tupled(p(i)))
      case Material.APPLE               => (i => Apple.tupled(p(i)))
      case Material.ARMOR_STAND         => (i => ArmorStand.tupled(p(i)))
      case Material.BAMBOO              => (i => Bamboo.tupled(p(i)))
      case Material.BAMBOO_MOSAIC       => (i => BambooMosaic.tupled(p(i)))
      case Material.BARREL              => (i => Barrel.tupled(p(i)))
      case Material.BARRIER             => (i => Barrier.tupled(p(i)))
      case Material.BEACON              => (i => Beacon.tupled(p(i)))
      case Material.BEDROCK             => (i => Bedrock.tupled(p(i)))
      case Material.BEE_NEST            => (i => BeeNest.tupled(p(i)))
      case Material.BEEHIVE             => (i => Beehive.tupled(p(i)))
      case Material.BEETROOT            => (i => Beetroot.tupled(p(i)))
      case Material.BELL                => (i => Bell.tupled(p(i)))
      case Material.BIG_DRIPLEAF        => (i => BigDripleaf.tupled(p(i)))
      case Material.BLAST_FURNACE       => (i => BlastFurnace.tupled(p(i)))
      case Material.BLAZE_POWDER        => (i => BlazeRod.tupled(p(i)))
      case Material.BLAZE_ROD           => (i => BlazeRod.tupled(p(i)))
      case Material.BONE                => (i => Bone.tupled(p(i)))
      case Material.BONE_BLOCK          => (i => BoneBlock.tupled(p(i)))
      case Material.BONE_MEAL           => (i => BoneMeal.tupled(p(i)))
      case Material.BOOK                => (i => Book.tupled(p(i)))
      case Material.BOOKSHELF           => (i => Bookshelf.tupled(p(i)))
      case Material.BOW                 => (i => Bow.tupled(d(i)))
      case Material.BOWL                => (i => Bowl.tupled(p(i)))
      case Material.BREAD               => (i => Bread.tupled(p(i)))
      case Material.BREWING_STAND       => (i => BrewingStand.tupled(p(i)))
      case Material.BRUSH               => (i => Brush.tupled(s(i)))
      case Material.BUDDING_AMETHYST    => (i => BuddingAmethyst.tupled(p(i)))
      case Material.CACTUS              => (i => Cactus.tupled(p(i)))
      case Material.CAKE                => (i => Cake.tupled(s(i)))
      case Material.CALCITE             => (i => Calcite.tupled(p(i)))
      case Material.CARROT              => (i => Carrot.tupled(p(i)))
      case Material.CARROT_ON_A_STICK   => (i => CarrotOnAStick.tupled(d(i)))
      case Material.CARTOGRAPHY_TABLE   => (i => CartographyTable.tupled(p(i)))
      case Material.CAULDRON            => (i => Cauldron.tupled(p(i)))
      case Material.CHAIN               => (i => Chain.tupled(p(i)))
      case Material.CHARCOAL            => (i => Charcoal.tupled(p(i)))
      case Material.CHORUS_FLOWER       => (i => ChorusFlower.tupled(p(i)))
      case Material.CHORUS_PLANT        => (i => ChorusPlant.tupled(p(i)))
      case Material.CLAY                => (i => ClayBlock.tupled(p(i)))
      case Material.CLAY_BALL           => (i => Clay.tupled(p(i)))
      case Material.CLOCK               => (i => Clock.tupled(p(i)))
      case Material.COAL                => (i => Coal.tupled(p(i)))
      case Material.COAL_BLOCK          => (i => CoalBlock.tupled(p(i)))
      case Material.COBWEB              => (i => Cobweb.tupled(p(i)))
      case Material.COCOA_BEANS         => (i => CocoaBeans.tupled(p(i)))
      case Material.COMPARATOR          => (i => Comparator.tupled(p(i)))
      case Material.COMPASS             => (i => Compass.tupled(p(i)))
      case Material.COMPOSTER           => (i => Composter.tupled(p(i)))
      case Material.CONDUIT             => (i => Conduit.tupled(p(i)))
      case Material.COOKIE              => (i => Cookie.tupled(p(i)))
      case Material.COPPER_INGOT        => (i => CopperIngot.tupled(p(i)))
      case Material.CRAFTING_TABLE      => (i => CraftingTable.tupled(p(i)))
      case Material.CROSSBOW            => (i => Crossbow.tupled(d(i)))
      case Material.CRYING_OBSIDIAN     => (i => CryingObsidian.tupled(p(i)))
      case Material.DAYLIGHT_DETECTOR   => (i => DaylightDetector.tupled(p(i)))
      case Material.DEAD_BUSH           => (i => DeadBush.tupled(p(i)))
      case Material.DEBUG_STICK         => (i => DebugStick.tupled(s(i)))
      case Material.DEEPSLATE           => (i => Deepslate.tupled(p(i)))
      case Material.DIAMOND             => (i => Diamond.tupled(p(i)))
      case Material.DIAMOND_BLOCK       => (i => DiamondBlock.tupled(p(i)))
      case Material.DIRT_PATH           => (i => DirtPath.tupled(p(i)))
      case Material.DISC_FRAGMENT_5     => (i => DiscFragment.tupled(p(i)))
      case Material.DISPENSER           => (i => Dispenser.tupled(p(i)))
      case Material.DRAGON_BREATH       => (i => DragonBreath.tupled(p(i)))
      case Material.DRAGON_EGG          => (i => DragonEgg.tupled(p(i)))
      case Material.DRIED_KELP          => (i => DriedKelp.tupled(p(i)))
      case Material.DRIED_KELP_BLOCK    => (i => DriedKelpBlock.tupled(p(i)))
      case Material.DRIPSTONE_BLOCK     => (i => DripstoneBlock.tupled(p(i)))
      case Material.POINTED_DRIPSTONE   => (i => Dripstone.tupled(p(i)))
      case Material.DROPPER             => (i => Dropper.tupled(p(i)))
      case Material.ECHO_SHARD          => (i => EchoShard.tupled(p(i)))
      case Material.EGG                 => (i => Egg.tupled(p(i)))
      case Material.ELYTRA              => (i => Elytra.tupled(d(i)))
      case Material.EMERALD             => (i => Emerald.tupled(p(i)))
      case Material.EMERALD_BLOCK       => (i => EmeraldBlock.tupled(p(i)))
      case Material.ENCHANTED_BOOK      => (i => EnchantedBook.tupled(s(i)))
      case Material.ENCHANTING_TABLE    => (i => EnchantingTable.tupled(p(i)))
      case Material.ENDER_EYE           => (i => EnderEye.tupled(p(i)))
      case Material.ENDER_PEARL         => (i => EnderPearl.tupled(p(i)))
      case Material.END_CRYSTAL         => (i => EndCrystal.tupled(p(i)))
      case Material.END_PORTAL_FRAME    => (i => EndPortalFrame.tupled(p(i)))
      case Material.END_ROD             => (i => EndRod.tupled(p(i)))
      case Material.END_STONE           => (i => EndStone.tupled(p(i)))
      case Material.END_STONE_BRICKS    => (i => EndStoneBrick.tupled(p(i)))
      case Material.EXPERIENCE_BOTTLE   => (i => BottleOfEnchanting.tupled(p(i)))
      case Material.FARMLAND            => (i => Farmland.tupled(p(i)))
      case Material.FEATHER             => (i => Feather.tupled(p(i)))
      case Material.FISHING_ROD         => (i => FishingRod.tupled(d(i)))
      case Material.FIREWORK_ROCKET     => (i => FireworkRocket.tupled(p(i)))
      case Material.FIRE_CHARGE         => (i => FireCharge.tupled(p(i)))
      case Material.FLETCHING_TABLE     => (i => FletchingTable.tupled(p(i)))
      case Material.FLINT               => (i => Flint.tupled(p(i)))
      case Material.FLINT_AND_STEEL     => (i => FlintAndSteel.tupled(d(i)))
      case Material.FLOWER_POT          => (i => FlowerPot.tupled(p(i)))
      case Material.FROGSPAWN           => (i => Frogspawn.tupled(p(i)))
      case Material.FURNACE             => (i => Furnace.tupled(p(i)))
      case Material.GHAST_TEAR          => (i => GhastTear.tupled(p(i)))
      case Material.GILDED_BLACKSTONE   => (i => GildedBlackstone.tupled(p(i)))
      case Material.GLASS_BOTTLE        => (i => GlassBottle.tupled(p(i)))
      case Material.GLOW_BERRIES        => (i => GlowBerries.tupled(p(i)))
      case Material.GLOW_LICHEN         => (i => GlowLichen.tupled(p(i)))
      case Material.GLOWSTONE           => (i => Glowstone.tupled(p(i)))
      case Material.GLOWSTONE_DUST      => (i => GlowstoneDust.tupled(p(i)))
      case Material.GOLDEN_CARROT       => (i => GoldenCarrot.tupled(p(i)))
      case Material.GOLD_BLOCK          => (i => GoldBlock.tupled(p(i)))
      case Material.GOLD_INGOT          => (i => GoldIngot.tupled(p(i)))
      case Material.GOLD_NUGGET         => (i => GoldNugget.tupled(p(i)))
      case Material.GRASS_BLOCK         => (i => GrassBlock.tupled(p(i)))
      case Material.GRAVEL              => (i => Gravel.tupled(p(i)))
      case Material.GRINDSTONE          => (i => Grindstone.tupled(p(i)))
      case Material.GUNPOWDER           => (i => Gunpowder.tupled(p(i)))
      case Material.HANGING_ROOTS       => (i => HangingRoots.tupled(p(i)))
      case Material.HAY_BLOCK           => (i => HayBale.tupled(p(i)))
      case Material.HEART_OF_THE_SEA    => (i => HeartOfTheSea.tupled(p(i)))
      case Material.HONEY_BLOCK         => (i => HoneyBlock.tupled(p(i)))
      case Material.HONEYCOMB           => (i => Honeycomb.tupled(p(i)))
      case Material.HONEYCOMB_BLOCK     => (i => HoneycombBlock.tupled(p(i)))
      case Material.HOPPER              => (i => Hopper.tupled(p(i)))
      case Material.IRON_BARS           => (i => IronBars.tupled(p(i)))
      case Material.IRON_BLOCK          => (i => IronBlock.tupled(p(i)))
      case Material.IRON_INGOT          => (i => IronIngot.tupled(p(i)))
      case Material.IRON_NUGGET         => (i => IronNugget.tupled(p(i)))
      case Material.ITEM_FRAME          => (i => ItemFrame.tupled(p(i)))
      case Material.JACK_O_LANTERN      => (i => JackOfTheLantern.tupled(p(i)))
      case Material.JIGSAW              => (i => JigsawBlock.tupled(p(i)))
      case Material.JUKEBOX             => (i => Jukebox.tupled(p(i)))
      case Material.KELP                => (i => Kelp.tupled(p(i)))
      case Material.KNOWLEDGE_BOOK      => (i => KnowledgeBook.tupled(s(i)))
      case Material.LADDER              => (i => Ladder.tupled(p(i)))
      case Material.LAPIS_BLOCK         => (i => LapisBlock.tupled(p(i)))
      case Material.LAPIS_LAZULI        => (i => Lapis.tupled(p(i)))
      case Material.LEAD                => (i => Lead.tupled(p(i)))
      case Material.LEATHER             => (i => Leather.tupled(p(i)))
      case Material.LECTERN             => (i => Lectern.tupled(p(i)))
      case Material.LEVER               => (i => Lever.tupled(p(i)))
      case Material.LIGHT               => (i => LightBlock.tupled(p(i)))
      case Material.LIGHTNING_ROD       => (i => LightningRod.tupled(p(i)))
      case Material.LILY_PAD            => (i => LilyPad.tupled(p(i)))
      case Material.LODESTONE           => (i => Lodestone.tupled(p(i)))
      case Material.LOOM                => (i => Loom.tupled(p(i)))
      case Material.MAGMA_BLOCK         => (i => MagmaBlock.tupled(p(i)))
      case Material.MAGMA_CREAM         => (i => MagmaCream.tupled(p(i)))
      case Material.MANGROVE_ROOTS      => (i => MangroveRoots.tupled(p(i)))
      case Material.MELON               => (i => Melon.tupled(p(i)))
      case Material.MELON_SLICE         => (i => MelonSlice.tupled(p(i)))
      case Material.MOSS_BLOCK          => (i => MossBlock.tupled(p(i)))
      case Material.MOSS_CARPET         => (i => MossCarpet.tupled(p(i)))
      case Material.MUD                 => (i => Mud.tupled(p(i)))
      case Material.MUD_BRICKS          => (i => MudBrick.tupled(p(i)))
      case Material.MYCELIUM            => (i => Mycelium.tupled(p(i)))
      case Material.NAME_TAG            => (i => NameTag.tupled(p(i)))
      case Material.NAUTILUS_SHELL      => (i => NautilusShell.tupled(p(i)))
      case Material.NETHERRACK          => (i => Netherrack.tupled(p(i)))
      case Material.NETHER_GOLD_ORE     => (i => NetherGoldOre.tupled(p(i)))
      case Material.NETHER_QUARTZ_ORE   => (i => QuartzOre.tupled(p(i)))
      case Material.NETHER_SPROUTS      => (i => NetherSprouts.tupled(p(i)))
      case Material.NETHER_STAR         => (i => NetherStar.tupled(p(i)))
      case Material.NETHER_WART         => (i => NetherWart.tupled(p(i)))
      case Material.NETHERITE_BLOCK     => (i => NetheriteBlock.tupled(p(i)))
      case Material.NETHERITE_INGOT     => (i => NetheriteIngot.tupled(p(i)))
      case Material.NETHERITE_SCRAP     => (i => NetheriteScrap.tupled(p(i)))
      case Material.NOTE_BLOCK          => (i => NoteBlock.tupled(p(i)))
      case Material.OBSERVER            => (i => Observer.tupled(p(i)))
      case Material.OBSIDIAN            => (i => Obsidian.tupled(p(i)))
      case Material.PACKED_MUD          => (i => PackedMud.tupled(p(i)))
      case Material.PAINTING            => (i => Painting.tupled(p(i)))
      case Material.PAPER               => (i => Paper.tupled(p(i)))
      case Material.PHANTOM_MEMBRANE    => (i => PhantomMembrane.tupled(p(i)))
      case Material.PINK_PETALS         => (i => PinkPetals.tupled(p(i)))
      case Material.PITCHER_PLANT       => (i => PitcherPlant.tupled(p(i)))
      case Material.PITCHER_POD         => (i => PitcherPod.tupled(p(i)))
      case Material.PODZOL              => (i => Podzol.tupled(p(i)))
      case Material.PRISMARINE_CRYSTALS => (i => PrismarineCrystals.tupled(p(i)))
      case Material.PRISMARINE_SHARD    => (i => PrismarineShard.tupled(p(i)))
      case Material.PUFFERFISH          => (i => Pufferfish.tupled(p(i)))
      case Material.PUMPKIN_PIE         => (i => PumpkinPie.tupled(p(i)))
      case Material.PURPUR_BLOCK        => (i => PurpurBlock.tupled(p(i)))
      case Material.QUARTZ              => (i => Quartz.tupled(p(i)))
      case Material.RABBIT_FOOT         => (i => RabbitFoot.tupled(p(i)))
      case Material.RABBIT_HIDE         => (i => RabbitHide.tupled(p(i)))
      case Material.RAW_COPPER          => (i => RawCopper.tupled(p(i)))
      case Material.RAW_COPPER_BLOCK    => (i => RawCopperBlock.tupled(p(i)))
      case Material.RAW_GOLD            => (i => RawGold.tupled(p(i)))
      case Material.RAW_GOLD_BLOCK      => (i => RawGoldBlock.tupled(p(i)))
      case Material.RAW_IRON            => (i => RawIron.tupled(p(i)))
      case Material.RAW_IRON_BLOCK      => (i => RawIronBlock.tupled(p(i)))
      case Material.RECOVERY_COMPASS    => (i => RecoveryCompass.tupled(p(i)))
      case Material.REDSTONE            => (i => Redstone.tupled(p(i)))
      case Material.REDSTONE_BLOCK      => (i => RedstoneBlock.tupled(p(i)))
      case Material.REDSTONE_LAMP       => (i => RedstoneLamp.tupled(p(i)))
      case Material.REDSTONE_TORCH      => (i => RedstoneTorch.tupled(p(i)))
      case Material.REPEATER            => (i => Repeater.tupled(p(i)))
      case Material.RESPAWN_ANCHOR      => (i => RespawnAnchor.tupled(p(i)))
      case Material.ROTTEN_FLESH        => (i => RottenFlesh.tupled(p(i)))
      case Material.SADDLE              => (i => Saddle.tupled(s(i)))
      case Material.SCAFFOLDING         => (i => Scaffolding.tupled(p(i)))
      case Material.SCULK               => (i => Sculk.tupled(p(i)))
      case Material.SCULK_VEIN          => (i => SculkVein.tupled(p(i)))
      case Material.SCUTE               => (i => Scute.tupled(p(i)))
      case Material.SEAGRASS            => (i => Seagrass.tupled(p(i)))
      case Material.SEA_LANTERN         => (i => SeaLantern.tupled(p(i)))
      case Material.SEA_PICKLE          => (i => SeaPickle.tupled(p(i)))
      case Material.SHEARS              => (i => Shears.tupled(d(i)))
      case Material.SHIELD              => (i => Shield.tupled(d(i)))
      case Material.SHROOMLIGHT         => (i => Shroomlight.tupled(p(i)))
      case Material.SHULKER_SHELL       => (i => ShulkerShell.tupled(p(i)))
      case Material.SLIME_BALL          => (i => Slimeball.tupled(p(i)))
      case Material.SLIME_BLOCK         => (i => SlimeBlock.tupled(p(i)))
      case Material.SMALL_DRIPLEAF      => (i => SmallDripleaf.tupled(p(i)))
      case Material.SMITHING_TABLE      => (i => SmithingTable.tupled(p(i)))
      case Material.SMOKER              => (i => Smoker.tupled(p(i)))
      case Material.SMOOTH_BASALT       => (i => SmoothBasalt.tupled(p(i)))
      case Material.SNIFFER_EGG         => (i => SnifferEgg.tupled(p(i)))
      case Material.SNOW                => (i => Snow.tupled(p(i)))
      case Material.SNOWBALL            => (i => Snowball.tupled(p(i)))
      case Material.SNOW_BLOCK          => (i => SnowBlock.tupled(p(i)))
      case Material.SOUL_SAND           => (i => SoulSand.tupled(p(i)))
      case Material.SOUL_SOIL           => (i => SoulSoil.tupled(p(i)))
      case Material.SPAWNER             => (i => Spawner.tupled(p(i)))
      case Material.SPORE_BLOSSOM       => (i => SporeBlossom.tupled(p(i)))
      case Material.SPYGLASS            => (i => Spyglass.tupled(p(i)))
      case Material.STICK               => (i => Stick.tupled(p(i)))
      case Material.STONECUTTER         => (i => Stonecutter.tupled(p(i)))
      case Material.STRING              => (i => PieceOfString.tupled(p(i)))
      case Material.SUGAR               => (i => Sugar.tupled(p(i)))
      case Material.SUGAR_CANE          => (i => SugarCane.tupled(p(i)))
      case Material.SUSPICIOUS_GRAVEL   => (i => SuspiciousGravel.tupled(p(i)))
      case Material.SUSPICIOUS_SAND     => (i => SuspiciousSand.tupled(p(i)))
      case Material.SWEET_BERRIES       => (i => SweetBerries.tupled(p(i)))
      case Material.TARGET              => (i => Target.tupled(p(i)))
      case Material.TINTED_GLASS        => (i => TintedGlass.tupled(p(i)))
      case Material.TNT                 => (i => TNT.tupled(p(i)))
      case Material.TORCHFLOWER_SEEDS   => (i => TorchflowerSeeds.tupled(p(i)))
      case Material.TOTEM_OF_UNDYING    => (i => TotemOfUndying.tupled(s(i)))
      case Material.TRIDENT             => (i => Trident.tupled(d(i)))
      case Material.TRIPWIRE_HOOK       => (i => TripwireHook.tupled(p(i)))
      case Material.TROPICAL_FISH       => (i => TropicalFish.tupled(p(i)))
      case Material.TUFF                => (i => Tuff.tupled(p(i)))
      case Material.TURTLE_EGG          => (i => TurtleEgg.tupled(p(i)))
      case Material.TURTLE_HELMET       => (i => TurtleHelmet.tupled(d(i)))
      case Material.VINE                => (i => Vines.tupled(p(i)))
      case Material.WHEAT               => (i => Wheat.tupled(p(i)))
      case Material.WRITABLE_BOOK       => (i => BookAndQuill.tupled(s(i)))
      case Material.WRITTEN_BOOK        => (i => WrittenBook.tupled(p(i)))

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

      // GOLDEN_MELON_SLICE
      case Material.GLISTERING_MELON_SLICE =>
        (i => GoldenMelonSlice.tupled(p(i)))

      // MUDDY_MANGROVE_ROOTS
      case Material.MUDDY_MANGROVE_ROOTS =>
        (i => MuddyMangroveRoots.tupled(p(i)))

      // PUMPKIN
      case Material.PUMPKIN =>
        (i => Pumpkin(carved = false, name(i), tt(i), count(i), attr(i), hAttr(i)))

      case Material.CARVED_PUMPKIN =>
        (i => Pumpkin(carved = true, name(i), tt(i), count(i), attr(i), hAttr(i)))

      // REINFORCED_DEEPSLATE
      case Material.REINFORCED_DEEPSLATE =>
        (i => ReinforcedDeepslate.tupled(p(i)))

      // WARPED_FUNGUS_ON_A_STICK
      case Material.WARPED_FUNGUS_ON_A_STICK =>
        (i => WarpedFungusOnAStick.tupled(d(i)))

      case m if m.isAndesite   => (i => Andesite.tupled(v[AndesiteVariant](i)))
      case m if m.isAnvil      => (i => Anvil.tupled(v[AnvilVariant](i)))
      case m if m.isArrow      => (i => Arrow.tupled(v[ArrowVariant](i)))
      case m if m.isAxe        => (i => Axe.tupled(e[AxeVariant](i)))
      case m if m.isAzalea     => (i => Azalea.tupled(v[AzaleaVariant](i)))
      case m if m.isBanner     => (i => Banner.tupled(v[BannerVariant](i)))
      case m if m.isBasalt     => (i => Basalt.tupled(v[BasaltVariant](i)))
      case m if m.isBed        => (i => Bed.tupled(v[BedVariant](i)))
      case m if m.isBeef       => (i => Beef.tupled(v[BeefVariant](i)))
      case m if m.isBlackstone => (i => Blackstone.tupled(v[BlackstoneVariant](i)))
      case m if m.isBoat       => (i => Boat.tupled(v[BoatVariant](i)))
      case m if m.isBoots      => (i => Boots.tupled(e[BootsVariant](i)))
      case m if m.isBrick      => (i => Brick.tupled(v[BrickVariant](i)))
      case m if m.isBucket     => (i => Bucket.tupled(v[BucketVariant](i)))
      case m if m.isButton     => (i => Button.tupled(v[ButtonVariant](i)))
      case m if m.isCampfire   => (i => Campfire.tupled(v[CampfireVariant](i)))
      case m if m.isCarpet     => (i => Carpet.tupled(v[CarpetVariant](i)))
      case m if m.isChest      => (i => Chest.tupled(v[ChestVariant](i)))
      case m if m.isChestplate => (i => Chestplate.tupled(e[ChestplateVariant](i)))
      case m if m.isChicken    => (i => Chicken.tupled(v[ChickenVariant](i)))
      case m if m.isCoalOre    => (i => CoalOre.tupled(v[CoalOreVariant](i)))
      case m if m.isCod        => (i => Cod.tupled(v[CodVariant](i)))
      case m if m.isConcrete   => (i => Concrete.tupled(v[ConcreteVariant](i)))
      case m if m.isCopperOre  => (i => CopperOre.tupled(v[CopperOreVariant](i)))
      case m if m.isCoral      => (i => Coral.tupled(v[CoralVariant](i)))
      case m if m.isCoralBlock => (i => CoralBlock.tupled(v[CoralBlockVariant](i)))
      case m if m.isCoralFan   => (i => CoralFan.tupled(v[CoralFanVariant](i)))
      case m if m.isDiamondOre => (i => DiamondOre.tupled(v[DiamondOreVariant](i)))
      case m if m.isDiorite    => (i => Diorite.tupled(v[DioriteVariant](i)))
      case m if m.isDirt       => (i => Dirt.tupled(v[DirtVariant](i)))
      case m if m.isDoor       => (i => Door.tupled(v[DoorVariant](i)))
      case m if m.isDye        => (i => Dye.tupled(v[DyeVariant](i)))
      case m if m.isEmeraldOre => (i => EmeraldOre.tupled(v[EmeraldOreVariant](i)))
      case m if m.isFence      => (i => Fence.tupled(v[FenceVariant](i)))
      case m if m.isFenceGate  => (i => FenceGate.tupled(v[FenceGateVariant](i)))
      case m if m.isFern       => (i => Fern.tupled(v[FernVariant](i)))
      case m if m.isFlower     => (i => Flower.tupled(v[FlowerVariant](i)))
      case m if m.isFroglight  => (i => Froglight.tupled(v[FroglightVariant](i)))
      case m if m.isFungus     => (i => Fungus.tupled(v[FungusVariant](i)))
      case m if m.isGlass      => (i => Glass.tupled(v[GlassVariant](i)))
      case m if m.isGlassPane  => (i => GlassPane.tupled(v[GlassPaneVariant](i)))
      case m if m.isGoldOre    => (i => GoldOre.tupled(v[GoldOreVariant](i)))
      case m if m.isGranite    => (i => Granite.tupled(v[GraniteVariant](i)))
      case m if m.isGrass      => (i => Grass.tupled(v[GrassVariant](i)))
      case m if m.isHelmet     => (i => Helmet.tupled(e[HelmetVariant](i)))
      case m if m.isHoe        => (i => Hoe.tupled(e[HoeVariant](i)))
      case m if m.isIce        => (i => Ice.tupled(v[IceVariant](i)))
      case m if m.isInkSac     => (i => InkSac.tupled(v[InkSacVariant](i)))
      case m if m.isIronOre    => (i => IronOre.tupled(v[IronOreVariant](i)))
      case m if m.isLantern    => (i => Lantern.tupled(v[LanternVariant](i)))
      case m if m.isLapisOre   => (i => LapisOre.tupled(v[LapisOreVariant](i)))
      case m if m.isLeaves     => (i => Leaves.tupled(v[LeavesVariant](i)))
      case m if m.isLeggings   => (i => Leggings.tupled(e[LeggingsVariant](i)))
      case m if m.isLog        => (i => Log.tupled(v[LogVariant](i)))
      case m if m.isMinecart   => (i => Minecart.tupled(v[MinecartVariant](i)))
      case m if m.isMobHead    => (i => MobHead.tupled(v[MobHeadVariant](i)))
      case m if m.isMushroom   => (i => Mushroom.tupled(v[MushroomVariant](i)))
      case m if m.isMusicDisc  => (i => MusicDisc.tupled(v[MusicDiscVariant](i)))
      case m if m.isMutton     => (i => Mutton.tupled(v[MuttonVariant](i)))
      case m if m.isNylium     => (i => Nylium.tupled(v[NyliumVariant](i)))
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
      case m if m.isRoots      => (i => Roots.tupled(v[RootsVariant](i)))
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
      case m if m.isTorch      => (i => Torch.tupled(v[TorchVariant](i)))
      case m if m.isTrapdoor   => (i => Trapdoor.tupled(v[TrapdoorVariant](i)))
      case m if m.isWall       => (i => Wall.tupled(v[WallVariant](i)))
      case m if m.isWood       => (i => Wood.tupled(v[WoodVariant](i)))
      case m if m.isWool       => (i => Wool.tupled(v[WoolVariant](i)))

      // materials that were pushing the alignment above too far to the right
      case m if m.isAmethystCluster =>
        (item => AmethystCluster.tupled(v[AmethystClusterVariant](item)))
      case m if m.isBambooBlock =>
        (i => BambooBlock.tupled(v[BambooBlockVariant](i)))
      case m if m.isBlackstoneBrick =>
        (item => BlackstoneBrick.tupled(v[BlackstoneBrickVariant](item)))
      case m if m.isBricksBlock =>
        (item => BricksBlock.tupled(v[BricksBlockVariant](item)))
      case m if m.isChorusFruit =>
        (item => ChorusFruit.tupled(v[ChorusFruitVariant](item)))
      case m if m.isCobblestone =>
        (item => Cobblestone.tupled(v[CobblestoneVariant](item)))
      case m if m.isCommandBlock =>
        (item => CommandBlock.tupled(v[CommandBlockVariant](item)))
      case m if m.isConcretePowder =>
        (item => ConcretePowder.tupled(v[ConcretePowderVariant](item)))
      case m if m.isCopperBlock =>
        (i => CopperBlock.tupled(v[CopperBlockVariant](i)))
      case m if m.isDeepslateBrick =>
        (item => DeepslateBrick.tupled(v[DeepslateBrickVariant](item)))
      case m if m.isDeepslateStone =>
        (item => DeepslateStone.tupled(v[DeepslateStoneVariant](item)))
      case m if m.isHangingSign =>
        (i => HangingSign.tupled(v[HangingSignVariant](i)))
      case m if m.isInfestedBlock =>
        (item => InfestedBlock.tupled(v[InfestedBlockVariant](item)))
      case m if m.isMushroomBlock =>
        (item => MushroomBlock.tupled(v[MushroomBlockVariant](item)))
      case m if m.isNetherWartBlock =>
        (item => NetherWartBlock.tupled(v[NetherWartBlockVariant](item)))
      case m if m.isNetherVines =>
        (item => NetherVines.tupled(v[NetherVinesVariant](item)))
      case m if m.isPotterySherd =>
        (item => PotterySherd.tupled(v[PotterySherdVariant](item)))
      case m if m.isPressurePlate =>
        (item => PressurePlate.tupled(v[PressurePlateVariant](item)))
      case m if m.isSmithingTemplate =>
        (item => SmithingTemplate.tupled(v[SmithingTemplateVariant](item)))
      case m if m.isQuartzBlock =>
        (item => QuartzBlock.tupled(v[QuartzBlockVariant](item)))
      case m if m.isRedstoneOre =>
        (item => RedstoneOre.tupled(v[RedstoneOreVariant](item)))
      case m if m.isStructureBlock =>
        (item => StructureBlock.tupled(v[StructureBlockVariant](item)))

      // materials that were simply too long all together
      case m if m.isBannerPattern =>
        item =>
          val v = variant[BannerPatternVariant](item)
          BannerPattern(v, name(item), tt(item), attr(item), hAttr(item))

      case m if m.isChestBoat =>
        item =>
          val v = variant[ChestBoatVariant](item)
          ChestBoat(v, name(item), tt(item), attr(item), hAttr(item))

      case m if m.isGlazedTerracotta =>
        (item => GlazedTerracotta.tupled(v[GlazedTerracottaVariant](item)))

      case m if m.isHorseArmor =>
        item =>
          val v = variant[HorseArmorVariant](item)
          HorseArmor(v, name(item), tt(item), attr(item), hAttr(item))

      case m if m.isPotion =>
        i =>
          val v = variant[PotionVariant](i)
          val meta = i._1.getItemMeta
          val hideEffects =
            if (meta != null) meta.hasItemFlag(ItemFlag.HIDE_POTION_EFFECTS)
            else false
          Potion(v, hideEffects, name(i), tt(i), attr(i), hAttr(i))

      case m if m.isWeightedPressurePlate =>
        (item => WeightedPressurePlate.tupled(v[WeightedPressurePlateVariant](item)))
    }

    itemAndVariantOverride: (SpigotItem, Option[ItemVariant]) =>
      Option(builder.apply(itemAndVariantOverride._1, itemAndVariantOverride._2))
  }

  def map(item: Item): SpigotItem = {
    item match { // return early on null and no stack size
      case null =>
        return new SpigotItem(Material.AIR)
      case it: StackableItem if it.count == 0 =>
        return new SpigotItem(Material.AIR)
      case _ =>
    }

    val material = item match {
      case it: GoldenApple =>
        if (it.enchanted) Material.ENCHANTED_GOLDEN_APPLE
        else Material.GOLDEN_APPLE

      case it: Map =>
        if (it.filled) Material.FILLED_MAP
        else Material.MAP

      case it: Pumpkin =>
        if (it.carved) Material.CARVED_PUMPKIN
        else Material.PUMPKIN

      case it: VariableItem[_] => variantMapper.map(it.variant)
      case it                  => typeMapper.map(it.`type`)
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

    // TODO map skull meta
    meta match {
      case leather: LeatherArmorMeta =>
        def setColor(name: String): Unit = {
          val colorName = name.split('_')(0)
          val color = Color.valueOf(colorName)
          val spigotColor = colorMapper.map(color)
          leather.setColor(spigotColor)
        }
        item.asInstanceOf[VariableItem[_]].variant match {
          case it: HelmetVariant     => if (it.name.contains('_')) setColor(it.name)
          case it: ChestplateVariant => if (it.name.contains('_')) setColor(it.name)
          case it: LeggingsVariant   => if (it.name.contains('_')) setColor(it.name)
          case it: BootsVariant      => if (it.name.contains('_')) setColor(it.name)
        }
      case _ =>
    }

    if (item.hideAttributes) meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
    meta.setLore(item.tooltip.asJava)
    item.customModelData.foreach { meta.setCustomModelData(_) }

    spigotItem.setItemMeta(meta)
    spigotItem
  }
}
