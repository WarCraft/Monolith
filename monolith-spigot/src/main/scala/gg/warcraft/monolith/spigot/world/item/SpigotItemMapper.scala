package gg.warcraft.monolith.spigot.world.item

import java.util

import gg.warcraft.monolith.api.core.Extensions.Regex
import gg.warcraft.monolith.api.world.block.{ BlockColor, ButtonMaterial, DoorMaterial, FenceMaterial,
  InfestedMaterial, InfestedVariant, PillarMaterial, PressurePlateMaterial, SlabMaterial, SlabVariant,
  StairsMaterial, StairsVariant, TrapdoorMaterial, WallMaterial, WallVariant }
import gg.warcraft.monolith.api.world.block.material.{ BrickMaterial, EndStoneMaterial, PrismarineMaterial,
  SandMaterial, SandstoneMaterial, StoneMaterial, WoodMaterial }
import gg.warcraft.monolith.api.world.block.variant._
import gg.warcraft.monolith.api.world.item._
import gg.warcraft.monolith.api.world.item.material.{ ArmorMaterial, ToolMaterial }
import gg.warcraft.monolith.api.world.item.variant._
import javax.inject.Inject
import org.bukkit.Material
import org.bukkit.inventory.ItemFlag

class SpigotItemMapper @Inject()(
    private val colorMapper: SpigotItemColorMapper,
    private val materialMapper: SpigotItemMaterialMapper,
    private val variantMapper: SpigotItemVariantMapper
) {
  private val cache =
    new util.EnumMap[Material, () => Item](classOf[Material])

  def map(item: SpigotItemStack): Option[Item] = {
    if (item.getType.name.endsWith("AIR")) return None

    val name  = item.getItemMeta.getDisplayName
    val lore  = Array()[String] // item.getItemMeta.getLore
    val count = item.getAmount
    val attr  = Set.empty[String]
    val hAttr = item.getItemMeta.hasItemFlag(ItemFlag.HIDE_ATTRIBUTES)

    lazy val dura     = item.getDurability
    lazy val unbreak  = item.getItemMeta.isUnbreakable
    lazy val hUnbreak = item.getItemMeta.hasItemFlag(ItemFlag.HIDE_UNBREAKABLE)
    lazy val hEffects = item.getItemMeta.hasItemFlag(ItemFlag.HIDE_POTION_EFFECTS)

    // functions to implicitly resolve and cast color, material, and variant
    implicit val implicitItem: SpigotItemStack = item
    def color(implicit itemStack: SpigotItemStack): BlockColor =
      colorMapper.map(itemStack.getType)
    def m[T <: ItemMaterial](implicit itemStack: SpigotItemStack): T =
      materialMapper.map(itemStack.getType).asInstanceOf[T]
    def v[T <: ItemVariant](implicit itemStack: SpigotItemStack): T =
      variantMapper.map(itemStack).asInstanceOf[T]

    val builder = cache.computeIfAbsent(item.getType, {
      case Material.APPLE                  => () => Apple(name, lore, count, attr, hAttr)
      case Material.ARMOR_STAND            => () => ArmorStand(name, lore, count, attr, hAttr)
      case Material.BAKED_POTATO           => () => Potato(cooked = true, name, lore, count, attr, hAttr)
      case Material.BAMBOO                 => () => Bamboo(name, lore, count, attr, hAttr)
      case Material.BARREL                 => () => Barrel(name, lore, count, attr, hAttr)
      case Material.BARRIER                => () => Barrier(name, lore, count, attr, hAttr)
      case Material.BEACON                 => () => Beacon(name, lore, count, attr, hAttr)
      case Material.BEDROCK                => () => Bedrock(name, lore, count, attr, hAttr)
      case Material.BEEF                   => () => Beef(cooked = false, name, lore, count, attr, hAttr)
      case Material.BEETROOT               => () => Beetroot(name, lore, count, attr, hAttr)
      case Material.BELL                   => () => Bell(name, lore, count, attr, hAttr)
      case Material.BLAST_FURNACE          => () => BlastFurnace(name, lore, count, attr, hAttr)
      case Material.BLAZE_POWDER           => () => BlazeRod(name, lore, count, attr, hAttr)
      case Material.BLAZE_ROD              => () => BlazeRod(name, lore, count, attr, hAttr)
      case Material.BONE                   => () => Bone(name, lore, count, attr, hAttr)
      case Material.BONE_BLOCK             => () => BoneBlock(name, lore, count, attr, hAttr)
      case Material.BONE_MEAL              => () => BoneMeal(name, lore, count, attr, hAttr)
      case Material.BOOK                   => () => Book(name, lore, count, attr, hAttr)
      case Material.BOOKSHELF              => () => Bookshelf(name, lore, count, attr, hAttr)
      case Material.BOW                    => () => Bow(name, lore, attr, hAttr, dura, unbreak, hUnbreak)
      case Material.BOWL                   => () => Bowl(name, lore, count, attr, hAttr)
      case Material.BREAD                  => () => Bread(name, lore, count, attr, hAttr)
      case Material.BREWING_STAND          => () => BrewingStand(name, lore, count, attr, hAttr)
      case Material.BRICKS                 => () => BrickBlock(m[BrickMaterial], name, lore, count, attr, hAttr)
      case Material.CACTUS                 => () => Cactus(name, lore, count, attr, hAttr)
      case Material.CAKE                   => () => Cake(name, lore, attr, hAttr)
      case Material.CAMPFIRE               => () => Campfire(name, lore, count, attr, hAttr)
      case Material.CARROT                 => () => Carrot(name, lore, count, attr, hAttr)
      case Material.CARROT_ON_A_STICK      => () => CarrotOnAStick(name, lore, attr, hAttr, dura, unbreak, hUnbreak)
      case Material.CARTOGRAPHY_TABLE      => () => CartographyTable(name, lore, count, attr, hAttr)
      case Material.CARVED_PUMPKIN         => () => Pumpkin(carved = true, name, lore, count, attr, hAttr)
      case Material.CAULDRON               => () => Cauldron(name, lore, count, attr, hAttr)
      case Material.CHARCOAL               => () => Charcoal(name, lore, count, attr, hAttr)
      case Material.CHICKEN                => () => Chicken(cooked = false, name, lore, count, attr, hAttr)
      case Material.CHORUS_FLOWER          => () => ChorusFlower(name, lore, count, attr, hAttr)
      case Material.CHORUS_FRUIT           => () => ChorusFruit(popped = false, name, lore, count, attr, hAttr)
      case Material.CHORUS_PLANT           => () => ChorusPlant(name, lore, count, attr, hAttr)
      case Material.CLAY                   => () => ClayBlock(name, lore, count, attr, hAttr)
      case Material.CLAY_BALL              => () => Clay(name, lore, count, attr, hAttr)
      case Material.CLOCK                  => () => Clock(name, lore, count, attr, hAttr)
      case Material.COAL                   => () => Coal(name, lore, count, attr, hAttr)
      case Material.COAL_BLOCK             => () => CoalBlock(name, lore, count, attr, hAttr)
      case Material.COAL_ORE               => () => CoalOre(name, lore, count, attr, hAttr)
      case Material.COARSE_DIRT            => () => Dirt(coarse = true, name, lore, count, attr, hAttr)
      case Material.COBWEB                 => () => Cobweb(name, lore, count, attr, hAttr)
      case Material.COCOA_BEANS            => () => CocoaBeans(name, lore, count, attr, hAttr)
      case Material.COD                    => () => Cod(cooked = false, name, lore, count, attr, hAttr)
      case Material.COMPARATOR             => () => Comparator(name, lore, count, attr, hAttr)
      case Material.COMPASS                => () => Compass(name, lore, count, attr, hAttr)
      case Material.COMPOSTER              => () => Composter(name, lore, count, attr, hAttr)
      case Material.CONDUIT                => () => Conduit(name, lore, count, attr, hAttr)
      case Material.COOKED_BEEF            => () => Beef(cooked = true, name, lore, count, attr, hAttr)
      case Material.COOKED_CHICKEN         => () => Chicken(cooked = true, name, lore, count, attr, hAttr)
      case Material.COOKED_COD             => () => Cod(cooked = true, name, lore, count, attr, hAttr)
      case Material.COOKED_MUTTON          => () => Mutton(cooked = true, name, lore, count, attr, hAttr)
      case Material.COOKED_PORKCHOP        => () => Porkchop(cooked = true, name, lore, count, attr, hAttr)
      case Material.COOKED_RABBIT          => () => Rabbit(cooked = true, name, lore, count, attr, hAttr)
      case Material.COOKED_SALMON          => () => Salmon(cooked = true, name, lore, count, attr, hAttr)
      case Material.COOKIE                 => () => Cookie(name, lore, count, attr, hAttr)
      case Material.CRAFTING_TABLE         => () => CraftingTable(name, lore, count, attr, hAttr)
      case Material.CROSSBOW               => () => Crossbow(name, lore, attr, hAttr, dura, unbreak, hUnbreak)
      case Material.DARK_PRISMARINE        => () => Prismarine(m[PrismarineMaterial], name, lore, count, attr, hAttr)
      case Material.DAYLIGHT_DETECTOR      => () => DaylightDetector(name, lore, count, attr, hAttr)
      case Material.DEAD_BUSH              => () => DeadBush(name, lore, count, attr, hAttr)
      case Material.DEBUG_STICK            => () => DebugStick(name, lore, attr, hAttr)
      case Material.DIAMOND                => () => Diamond(name, lore, count, attr, hAttr)
      case Material.DIAMOND_BLOCK          => () => DiamondBlock(name, lore, count, attr, hAttr)
      case Material.DIAMOND_ORE            => () => DiamondOre(name, lore, count, attr, hAttr)
      case Material.DIRT                   => () => Dirt(coarse = false, name, lore, count, attr, hAttr)
      case Material.DISPENSER              => () => Dispenser(name, lore, count, attr, hAttr)
      case Material.DRAGON_BREATH          => () => DragonBreath(name, lore, count, attr, hAttr)
      case Material.DRAGON_EGG             => () => DragonEgg(name, lore, count, attr, hAttr)
      case Material.DRIED_KELP             => () => DriedKelp(name, lore, count, attr, hAttr)
      case Material.DRIED_KELP_BLOCK       => () => DriedKelpBlock(name, lore, count, attr, hAttr)
      case Material.DROPPER                => () => Dropper(name, lore, count, attr, hAttr)
      case Material.EGG                    => () => Egg(name, lore, count, attr, hAttr)
      case Material.ELYTRA                 => () => Elytra(name, lore, attr, hAttr, dura, unbreak, hUnbreak)
      case Material.EMERALD                => () => Emerald(name, lore, count, attr, hAttr)
      case Material.EMERALD_BLOCK          => () => EmeraldBlock(name, lore, count, attr, hAttr)
      case Material.EMERALD_ORE            => () => EmeraldOre(name, lore, count, attr, hAttr)
      case Material.ENCHANTED_BOOK         => () => EnchantedBook(name, lore, attr, hAttr)
      case Material.ENCHANTED_GOLDEN_APPLE => () => GoldenApple(enchanted = true, name, lore, count, attr, hAttr)
      case Material.ENCHANTING_TABLE       => () => EnchantingTable(name, lore, count, attr, hAttr)
      case Material.ENDER_EYE              => () => EnderEye(name, lore, count, attr, hAttr)
      case Material.ENDER_PEARL            => () => EnderPearl(name, lore, count, attr, hAttr)
      case Material.END_CRYSTAL            => () => EndCrystal(name, lore, count, attr, hAttr)
      case Material.END_PORTAL_FRAME       => () => EndPortalFrame(name, lore, count, attr, hAttr)
      case Material.END_ROD                => () => EndRod(name, lore, count, attr, hAttr)
      case Material.EXPERIENCE_BOTTLE      => () => BottleOfEnchanting(name, lore, count, attr, hAttr)
      case Material.FARMLAND               => () => Farmland(name, lore, count, attr, hAttr)
      case Material.FEATHER                => () => Feather(name, lore, count, attr, hAttr)
      case Material.FERMENTED_SPIDER_EYE   => () => SpiderEye(fermented = true, name, lore, count, attr, hAttr)
      case Material.FERN                   => () => Fern(tall = false, name, lore, count, attr, hAttr)
      case Material.FILLED_MAP             => () => Map(filled = true, name, lore, count, attr, hAttr)
      case Material.FIREWORK_ROCKET        => () => FireworkRocket(name, lore, count, attr, hAttr)
      case Material.FIREWORK_STAR          => () => FireworkStar(color, name, lore, count, attr, hAttr)
      case Material.FIRE_CHARGE            => () => FireCharge(name, lore, count, attr, hAttr)
      case Material.FISHING_ROD            => () => FishingRod(name, lore, attr, hAttr, dura, unbreak, hUnbreak)
      case Material.FLETCHING_TABLE        => () => FletchingTable(name, lore, count, attr, hAttr)
      case Material.FLINT                  => () => Flint(name, lore, count, attr, hAttr)
      case Material.FLINT_AND_STEEL        => () => FlintAndSteel(name, lore, attr, hAttr, dura, unbreak, hUnbreak)
      case Material.FLOWER_POT             => () => FlowerPot(name, lore, count, attr, hAttr)
      case Material.FURNACE                => () => Furnace(name, lore, count, attr, hAttr)
      case Material.GHAST_TEAR             => () => GhastTear(name, lore, count, attr, hAttr)
      case Material.GLASS_BOTTLE           => () => GlassBottle(name, lore, count, attr, hAttr)
      case Material.GLISTERING_MELON_SLICE => () => GoldenMelonSlice(name, lore, count, attr, hAttr)
      case Material.GLOWSTONE              => () => Glowstone(name, lore, count, attr, hAttr)
      case Material.GLOWSTONE_DUST         => () => GlowstoneDust(name, lore, count, attr, hAttr)
      case Material.GOLDEN_APPLE           => () => GoldenApple(enchanted = false, name, lore, count, attr, hAttr)
      case Material.GOLDEN_CARROT          => () => GoldenCarrot(name, lore, count, attr, hAttr)
      case Material.GOLD_BLOCK             => () => GoldBlock(name, lore, count, attr, hAttr)
      case Material.GOLD_INGOT             => () => GoldIngot(name, lore, count, attr, hAttr)
      case Material.GOLD_NUGGET            => () => GoldNugget(name, lore, count, attr, hAttr)
      case Material.GOLD_ORE               => () => GoldOre(name, lore, count, attr, hAttr)
      case Material.GRASS                  => () => Grass(tall = false, name, lore, count, attr, hAttr)
      case Material.GRASS_BLOCK            => () => GrassBlock(name, lore, count, attr, hAttr)
      case Material.GRASS_PATH             => () => GrassPath(name, lore, count, attr, hAttr)
      case Material.GRAVEL                 => () => Gravel(name, lore, count, attr, hAttr)
      case Material.GRINDSTONE             => () => Grindstone(name, lore, count, attr, hAttr)
      case Material.GUNPOWDER              => () => Gunpowder(name, lore, count, attr, hAttr)
      case Material.HAY_BLOCK              => () => HayBale(name, lore, count, attr, hAttr)
      case Material.HEART_OF_THE_SEA       => () => HeartOfTheSea(name, lore, count, attr, hAttr)
      case Material.HOPPER                 => () => Hopper(name, lore, count, attr, hAttr)
      case Material.INK_SAC                => () => InkSac(name, lore, count, attr, hAttr)
      case Material.IRON_BARS              => () => IronBars(name, lore, count, attr, hAttr)
      case Material.IRON_BLOCK             => () => IronBlock(name, lore, count, attr, hAttr)
      case Material.IRON_INGOT             => () => IronIngot(name, lore, count, attr, hAttr)
      case Material.IRON_NUGGET            => () => IronNugget(name, lore, count, attr, hAttr)
      case Material.IRON_ORE               => () => IronOre(name, lore, count, attr, hAttr)
      case Material.ITEM_FRAME             => () => ItemFrame(name, lore, count, attr, hAttr)
      case Material.JACK_O_LANTERN         => () => JackOfTheLantern(name, lore, count, attr, hAttr)
      case Material.JIGSAW                 => () => JigsawBlock(name, lore, count, attr, hAttr)
      case Material.JUKEBOX                => () => Jukebox(name, lore, count, attr, hAttr)
      case Material.KELP                   => () => Kelp(name, lore, count, attr, hAttr)
      case Material.KNOWLEDGE_BOOK         => () => KnowledgeBook(name, lore, attr, hAttr)
      case Material.LADDER                 => () => Ladder(name, lore, count, attr, hAttr)
      case Material.LANTERN                => () => Lantern(name, lore, count, attr, hAttr)
      case Material.LAPIS_BLOCK            => () => LapisBlock(name, lore, count, attr, hAttr)
      case Material.LAPIS_LAZULI           => () => Lapis(name, lore, count, attr, hAttr)
      case Material.LAPIS_ORE              => () => LapisOre(name, lore, count, attr, hAttr)
      case Material.LARGE_FERN             => () => Fern(tall = true, name, lore, count, attr, hAttr)
      case Material.LEAD                   => () => Lead(name, lore, count, attr, hAttr)
      case Material.LEATHER                => () => Leather(name, lore, count, attr, hAttr)
      case Material.LECTERN                => () => Lectern(name, lore, count, attr, hAttr)
      case Material.LEVER                  => () => Lever(name, lore, count, attr, hAttr)
      case Material.LILY_PAD               => () => LilyPad(name, lore, count, attr, hAttr)
      case Material.LOOM                   => () => Loom(name, lore, count, attr, hAttr)
      case Material.MAGMA_BLOCK            => () => MagmaBlock(name, lore, count, attr, hAttr)
      case Material.MAGMA_CREAM            => () => MagmaCream(name, lore, count, attr, hAttr)
      case Material.MAP                    => () => Map(filled = false, name, lore, count, attr, hAttr)
      case Material.MELON                  => () => Melon(name, lore, count, attr, hAttr)
      case Material.MELON_SLICE            => () => MelonSlice(name, lore, count, attr, hAttr)
      case Material.MUTTON                 => () => Mutton(cooked = false, name, lore, count, attr, hAttr)
      case Material.MYCELIUM               => () => Mycelium(name, lore, count, attr, hAttr)
      case Material.NAME_TAG               => () => NameTag(name, lore, count, attr, hAttr)
      case Material.NAUTILUS_SHELL         => () => NautilusShell(name, lore, count, attr, hAttr)
      case Material.NETHERRACK             => () => Netherrack(name, lore, count, attr, hAttr)
      case Material.NETHER_BRICKS          => () => BrickBlock(m[BrickMaterial], name, lore, count, attr, hAttr)
      case Material.NETHER_QUARTZ_ORE      => () => QuartzOre(name, lore, count, attr, hAttr)
      case Material.NETHER_STAR            => () => NetherStar(name, lore, count, attr, hAttr)
      case Material.NETHER_WART            => () => NetherWart(name, lore, count, attr, hAttr)
      case Material.NETHER_WART_BLOCK      => () => NetherWartBlock(name, lore, count, attr, hAttr)
      case Material.NOTE_BLOCK             => () => NoteBlock(name, lore, count, attr, hAttr)
      case Material.OBSERVER               => () => Observer(name, lore, count, attr, hAttr)
      case Material.OBSIDIAN               => () => Obsidian(name, lore, count, attr, hAttr)
      case Material.PAINTING               => () => Painting(name, lore, count, attr, hAttr)
      case Material.PAPER                  => () => Paper(name, lore, count, attr, hAttr)
      case Material.PHANTOM_MEMBRANE       => () => PhantomMembrane(name, lore, count, attr, hAttr)
      case Material.PISTON                 => () => Piston(sticky = false, name, lore, count, attr, hAttr)
      case Material.PODZOL                 => () => Podzol(name, lore, count, attr, hAttr)
      case Material.POISONOUS_POTATO       => () => PoisonousPotato(name, lore, count, attr, hAttr)
      case Material.POPPED_CHORUS_FRUIT    => () => ChorusFruit(popped = true, name, lore, count, attr, hAttr)
      case Material.PORKCHOP               => () => Porkchop(cooked = false, name, lore, count, attr, hAttr)
      case Material.POTATO                 => () => Potato(cooked = false, name, lore, count, attr, hAttr)
      case Material.PRISMARINE             => () => Prismarine(m[PrismarineMaterial], name, lore, count, attr, hAttr)
      case Material.PRISMARINE_BRICKS      => () => Prismarine(m[PrismarineMaterial], name, lore, count, attr, hAttr)
      case Material.PRISMARINE_CRYSTALS    => () => PrismarineCrystals(name, lore, count, attr, hAttr)
      case Material.PRISMARINE_SHARD       => () => PrismarineShard(name, lore, count, attr, hAttr)
      case Material.PUFFERFISH             => () => Pufferfish(name, lore, count, attr, hAttr)
      case Material.PUMPKIN                => () => Pumpkin(carved = false, name, lore, count, attr, hAttr)
      case Material.PUMPKIN_PIE            => () => PumpkinPie(name, lore, count, attr, hAttr)
      case Material.PURPUR_BLOCK           => () => PurpurBlock(name, lore, count, attr, hAttr)
      case Material.QUARTZ                 => () => Quartz(name, lore, count, attr, hAttr)
      case Material.RABBIT                 => () => Rabbit(cooked = false, name, lore, count, attr, hAttr)
      case Material.RABBIT_FOOT            => () => RabbitFoot(name, lore, count, attr, hAttr)
      case Material.RABBIT_HIDE            => () => RabbitHide(name, lore, count, attr, hAttr)
      case Material.REDSTONE               => () => Redstone(name, lore, count, attr, hAttr)
      case Material.REDSTONE_BLOCK         => () => RedstoneBlock(name, lore, count, attr, hAttr)
      case Material.REDSTONE_LAMP          => () => RedstoneLamp(name, lore, count, attr, hAttr)
      case Material.REDSTONE_ORE           => () => RedstoneOre(name, lore, count, attr, hAttr)
      case Material.REDSTONE_TORCH         => () => RedstoneTorch(name, lore, count, attr, hAttr)
      case Material.RED_NETHER_BRICKS      => () => BrickBlock(m[BrickMaterial], name, lore, count, attr, hAttr)
      case Material.REPEATER               => () => Repeater(name, lore, count, attr, hAttr)
      case Material.ROTTEN_FLESH           => () => RottenFlesh(name, lore, count, attr, hAttr)
      case Material.SADDLE                 => () => Saddle(name, lore, attr, hAttr)
      case Material.SALMON                 => () => Salmon(cooked = false, name, lore, count, attr, hAttr)
      case Material.SCAFFOLDING            => () => Scaffolding(name, lore, count, attr, hAttr)
      case Material.SCUTE                  => () => Scute(name, lore, count, attr, hAttr)
      case Material.SEAGRASS               => () => Seagrass(name, lore, count, attr, hAttr)
      case Material.SEA_LANTERN            => () => SeaLantern(name, lore, count, attr, hAttr)
      case Material.SEA_PICKLE             => () => SeaPickle(name, lore, count, attr, hAttr)
      case Material.SHEARS                 => () => Shears(name, lore, attr, hAttr, dura, unbreak, hUnbreak)
      case Material.SHIELD                 => () => Shield(name, lore, attr, hAttr, dura, unbreak, hUnbreak)
      case Material.SHULKER_SHELL          => () => ShulkerShell(name, lore, count, attr, hAttr)
      case Material.SLIME_BALL             => () => Slimeball(name, lore, count, attr, hAttr)
      case Material.SLIME_BLOCK            => () => SlimeBlock(name, lore, count, attr, hAttr)
      case Material.SMITHING_TABLE         => () => SmithingTable(name, lore, count, attr, hAttr)
      case Material.SMOKER                 => () => Smoker(name, lore, count, attr, hAttr)
      case Material.SNOW                   => () => Snow(name, lore, count, attr, hAttr)
      case Material.SNOWBALL               => () => Snowball(name, lore, count, attr, hAttr)
      case Material.SNOW_BLOCK             => () => SnowBlock(name, lore, count, attr, hAttr)
      case Material.SOUL_SAND              => () => SoulSand(name, lore, count, attr, hAttr)
      case Material.SPAWNER                => () => Spawner(name, lore, count, attr, hAttr)
      case Material.SPIDER_EYE             => () => SpiderEye(fermented = false, name, lore, count, attr, hAttr)
      case Material.SPONGE                 => () => Sponge(wet = false, name, lore, count, attr, hAttr)
      case Material.STICK                  => () => Stick(name, lore, count, attr, hAttr)
      case Material.STICKY_PISTON          => () => Piston(sticky = true, name, lore, count, attr, hAttr)
      case Material.STONECUTTER            => () => Stonecutter(name, lore, count, attr, hAttr)
      case Material.STRING                 => () => PieceOfString(name, lore, count, attr, hAttr)
      case Material.SUGAR                  => () => Sugar(name, lore, count, attr, hAttr)
      case Material.SUGAR_CANE             => () => SugarCane(name, lore, count, attr, hAttr)
      case Material.SWEET_BERRIES          => () => SweetBerries(name, lore, count, attr, hAttr)
      case Material.TALL_GRASS             => () => Grass(tall = true, name, lore, count, attr, hAttr)
      case Material.TNT                    => () => TNT(name, lore, count, attr, hAttr)
      case Material.TORCH                  => () => Torch(name, lore, count, attr, hAttr)
      case Material.TOTEM_OF_UNDYING       => () => TotemOfUndying(name, lore, attr, hAttr)
      case Material.TRIDENT                => () => Trident(name, lore, attr, hAttr, dura, unbreak, hUnbreak)
      case Material.TRIPWIRE_HOOK          => () => TripwireHook(name, lore, count, attr, hAttr)
      case Material.TROPICAL_FISH          => () => TropicalFish(name, lore, count, attr, hAttr)
      case Material.TURTLE_EGG             => () => TurtleEgg(name, lore, count, attr, hAttr)
      case Material.TURTLE_HELMET          => () => TurtleHelmet(name, lore, attr, hAttr, dura, unbreak, hUnbreak)
      case Material.VINE                   => () => Vine(name, lore, count, attr, hAttr)
      case Material.WET_SPONGE             => () => Sponge(wet = true, name, lore, count, attr, hAttr)
      case Material.WHEAT                  => () => Wheat(name, lore, count, attr, hAttr)
      case Material.WRITABLE_BOOK          => () => BookAndQuill(name, lore, attr, hAttr)
      case Material.WRITTEN_BOOK           => () => WrittenBook(name, lore, count, attr, hAttr)

      // FLOWER
      case Material.ALLIUM | Material.AZURE_BLUET | Material.BLUE_ORCHID |
           Material.CORNFLOWER | Material.DANDELION | Material.LILY_OF_THE_VALLEY |
           Material.ORANGE_TULIP | Material.OXEYE_DAISY | Material.PINK_TULIP |
           Material.POPPY | Material.RED_TULIP | Material.WHITE_TULIP |
           Material.WITHER_ROSE => () =>
        Flower(v[FlowerVariant], name, lore, count, attr, hAttr)

      // PLANT TODO split these up into 4 items?
      case Material.LILAC | Material.PEONY | Material.ROSE_BUSH |
           Material.SUNFLOWER => () =>
        Plant(v[PlantVariant], name, lore, count, attr, hAttr)

      // QUARTZ_BLOCK
      case Material.QUARTZ_BLOCK | Material.CHISELED_QUARTZ_BLOCK |
           Material.SMOOTH_QUARTZ => () =>
        QuartzBlock(v[QuartzVariant], name, lore, count, attr, hAttr)

      case _ => item.getType.name match {

        // matched first to avoid name overlap when sorted alphabetically
        case r".*PICKAXE"      => () => Pickaxe(m[ToolMaterial], name, lore, count, attr, hAttr)
        case r"STRIPPED.*LOG"  => () => Log(m[WoodMaterial], stripped = true, name, lore, count, attr, hAttr)
        case r"STRIPPED.*WOOD" => () => Wood(m[WoodMaterial], stripped = true, name, lore, count, attr, hAttr)

        case r".*SANDSTONE" => () =>
          Sandstone(m[SandstoneMaterial], v[SandstoneVariant], name, lore, count, attr, hAttr)

        // TODO SaplingVariant.BAMBOO is illegal on an item, make separate SaplingVariant for items?
        // TODO merge GlazedTerracotta and Terracotta like Log and Wood with stripped?
        // TODO or split Log and Wood into StrippedLog and StrippedWood instead?
        case r".*ANDESITE"          => () => Andesite(v[StoniteVariant], name, lore, count, attr, hAttr)
        case r".*ANVIL"             => () => Anvil(v[AnvilVariant], name, lore, count, attr, hAttr)
        case r".*ARROW"             => () => Arrow(v[ArrowVariant], name, lore, count, attr, hAttr)
        case r".*AXE"               => () => Axe(m[ToolMaterial], name, lore, count, attr, hAttr)
        case r".*BANNER"            => () => Banner(color, name, lore, count, attr, hAttr)
        case r".*BANNER_PATTERN"    => () => BannerPattern(v[BannerPatternVariant], name, lore, attr, hAttr)
        case r".*BED"               => () => Bed(color, name, lore, count, attr, hAttr)
        case r".*BOAT"              => () => Boat(m[WoodMaterial], name, lore, count, attr, hAttr)
        case r".*BOOTS"             => () => Boots(m[ArmorMaterial], name, lore, count, attr, hAttr)
        case r".*BRICK"             => () => Brick(m[BrickMaterial], name, lore, count, attr, hAttr)
        case r".*BUCKET"            => () => Bucket(v[BucketVariant], name, lore, count, attr, hAttr)
        case r".*BUTTON"            => () => Button(m[ButtonMaterial], name, lore, count, attr, hAttr)
        case r".*CARPET"            => () => Carpet(color, name, lore, count, attr, hAttr)
        case r".*CHEST"             => () => Chest(v[ChestVariant], name, lore, count, attr, hAttr)
        case r".*CHESTPLATE"        => () => Chestplate(m[ArmorMaterial], name, lore, count, attr, hAttr)
        case r".*COBBLESTONE"       => () => Cobblestone(v[CobblestoneVariant], name, lore, count, attr, hAttr)
        case r".*COMMAND_BLOCK"     => () => CommandBlock(v[CommandBlockVariant], name, lore, count, attr, hAttr)
        case r".*CONCRETE"          => () => Concrete(color, name, lore, count, attr, hAttr)
        case r".*CONCRETE_POWDER"   => () => ConcretePowder(color, name, lore, count, attr, hAttr)
        case r".*CORAL"             => () => Coral(v[CoralVariant], name, lore, count, attr, hAttr)
        case r".*CORAL_BLOCK"       => () => CoralBlock(v[CoralVariant], name, lore, count, attr, hAttr)
        case r".*CORAL_FAN"         => () => CoralFan(v[CoralVariant], name, lore, count, attr, hAttr)
        case r".*DIORITE"           => () => Diorite(v[StoniteVariant], name, lore, count, attr, hAttr)
        case r".*DOOR"              => () => Door(m[DoorMaterial], name, lore, count, attr, hAttr)
        case r".*DYE"               => () => Dye(color, name, lore, count, attr, hAttr)
        case r"END_STONE.*"         => () => EndStone(m[EndStoneMaterial], name, lore, count, attr, hAttr)
        case r".*FENCE"             => () => Fence(m[FenceMaterial], name, lore, count, attr, hAttr)
        case r".*GATE"              => () => Gate(m[WoodMaterial], name, lore, count, attr, hAttr)
        case r".*GLASS"             => () => Glass(Some(color), name, lore, count, attr, hAttr)
        case r".*GLASS_PANE"        => () => GlassPane(Some(color), name, lore, count, attr, hAttr)
        case r".*GLAZED_TERRACOTTA" => () => GlazedTerracotta(color, name, lore, count, attr, hAttr)
        case r".*GRANITE"           => () => Granite(v[StoniteVariant], name, lore, count, attr, hAttr)
        case r".*HEAD"              => () => MobHead(v[MobHeadVariant], name, lore, count, attr, hAttr)
        case r".*HELMET"            => () => Helmet(m[ArmorMaterial], name, lore, count, attr, hAttr)
        case r".*HOE"               => () => Hoe(m[ToolMaterial], name, lore, count, attr, hAttr)
        case r".*HORSE_ARMOR"       => () => HorseArmor(v[HorseArmorVariant], name, lore, count, attr, hAttr)
        case r".*ICE"               => () => Ice(v[IceVariant], name, lore, count, attr, hAttr)
        case r".*LEAVES"            => () => Leaves(m[WoodMaterial], name, lore, count, attr, hAttr)
        case r".*LEGGINGS"          => () => Leggings(m[ArmorMaterial], name, lore, count, attr, hAttr)
        case r".*LOG"               => () => Log(m[WoodMaterial], stripped = false, name, lore, count, attr, hAttr)
        case r".*MINECART"          => () => Minecart(v[MinecartVariant], name, lore, count, attr, hAttr)
        case r".*MUSHROOM"          => () => Mushroom(v[MushroomVariant], name, lore, count, attr, hAttr)
        case r".*MUSHROOM_BLOCK"    => () => MushroomBlock(v[MushroomBlockVariant], name, lore, count, attr, hAttr)
        case r"MUSIC_DISC.*"        => () => MusicDisc(v[MusicDiscVariant], name, lore, count, attr, hAttr)
        case r".*PILLAR"            => () => Pillar(m[PillarMaterial], name, lore, count, attr, hAttr)
        case r".*PLANKS"            => () => Planks(m[WoodMaterial], name, lore, count, attr, hAttr)
        case r".*POTION"            => () => Potion(v[PotionVariant], hEffects, name, lore, attr, hAttr)
        case r".*PRESSURE_PLATE"    => () => PressurePlate(m[PressurePlateMaterial], name, lore, count, attr, hAttr)
        case r".*RAIL"              => () => Rails(v[RailsVariant], name, lore, count, attr, hAttr) // TODO rename
        case r".*SAND"              => () => Sand(m[SandMaterial], name, lore, count, attr, hAttr)
        case r".*SAPLING"           => () => Sapling(v[SaplingVariant], name, lore, count, attr, hAttr)
        case r".*SEEDS"             => () => Seeds(v[SeedsVariant], name, lore, count, attr, hAttr)
        case r".*SHOVEL"            => () => Shovel(m[ToolMaterial], name, lore, count, attr, hAttr)
        case r".*SHULKER_BOX"       => () => ShulkerBox(Some(color), name, lore, count, attr, hAttr)
        case r".*SIGN"              => () => Sign(m[WoodMaterial], name, lore, count, attr, hAttr)
        case r".*SKULL"             => () => MobHead(v[MobHeadVariant], name, lore, count, attr, hAttr)
        case r".*SLAB"              => () => Slab(m[SlabMaterial], Some(v[SlabVariant]), name, lore, count, attr, hAttr)
        case r".*SPAWN_EGG"         => () => SpawnEgg(v[SpawnEggVariant], name, lore, count, attr, hAttr)
        case r".*STEW"              => () => Stew(v[StewVariant], name, lore, count, attr, hAttr)
        case r".*STONE"             => () => Stone(m[StoneMaterial], v[StoneVariant], name, lore, count, attr, hAttr)
        case r"STRUCTURE.*"         => () => StructureBlock(v[StructureBlockVariant], name, lore, count, attr, hAttr)
        case r".*SWORD"             => () => Sword(m[ToolMaterial], name, lore, count, attr, hAttr)
        case r".*TERRACOTTA"        => () => Terracotta(Some(color), name, lore, count, attr, hAttr)
        case r".*TRAPDOOR"          => () => TrapDoor(m[TrapdoorMaterial], name, lore, count, attr, hAttr)
        case r".*WALL"              => () => Wall(m[WallMaterial], Some(v[WallVariant]), name, lore, count, attr, hAttr)
        case r".*WOOD"              => () => Wood(m[WoodMaterial], stripped = false, name, lore, count, attr, hAttr)
        case r".*WOOL"              => () => Wool(color, name, lore, count, attr, hAttr)

        case r"INFESTED.*" => () =>
          InfestedBlock(m[InfestedMaterial], Some(v[InfestedVariant]), name, lore, count, attr, hAttr)

        case r".*STAIRS" => () =>
          Stairs(m[StairsMaterial], Some(v[StairsVariant]), name, lore, count, attr, hAttr)

        case r".*WEIGHTED_PRESSURE_PLATE" => () =>
          WeightedPressurePlate(v[WeightedPressurePlateVariant], name, lore, count, attr, hAttr)

        case _ => throw new IllegalArgumentException(s"${item.getType}")
      }
    })

    Some(builder())
  }

  def map(item: Item): SpigotItemStack = {
    val spigotItem: SpigotItemStack = item match {
      case _ => null
    }

    spigotItem
  }
}
