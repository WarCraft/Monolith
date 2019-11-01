package gg.warcraft.monolith.spigot.world.item

import java.util

import gg.warcraft.monolith.api.core.Extensions.Regex
import gg.warcraft.monolith.api.world.block.{ BlockColor }
import gg.warcraft.monolith.api.world.block.variant._
import gg.warcraft.monolith.api.world.item._
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

    val name              = item.getItemMeta.getDisplayName
    val tt: Array[String] = Array() // item.getItemMeta.getLore
    val c                 = item.getAmount
    val attr              = Set.empty[String]
    val hAttr             = item.getItemMeta.hasItemFlag(ItemFlag.HIDE_ATTRIBUTES)

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
      case Material.APPLE                  => () => Apple(name, tt, c, attr, hAttr)
      case Material.ARMOR_STAND            => () => ArmorStand(name, tt, c, attr, hAttr)
      case Material.BAKED_POTATO           => () => Potato(cooked = true, name, tt, c, attr, hAttr)
      case Material.BAMBOO                 => () => Bamboo(name, tt, c, attr, hAttr)
      case Material.BARREL                 => () => Barrel(name, tt, c, attr, hAttr)
      case Material.BARRIER                => () => Barrier(name, tt, c, attr, hAttr)
      case Material.BEACON                 => () => Beacon(name, tt, c, attr, hAttr)
      case Material.BEDROCK                => () => Bedrock(name, tt, c, attr, hAttr)
      case Material.BEEF                   => () => Beef(cooked = false, name, tt, c, attr, hAttr)
      case Material.BEETROOT               => () => Beetroot(name, tt, c, attr, hAttr)
      case Material.BELL                   => () => Bell(name, tt, c, attr, hAttr)
      case Material.BLAST_FURNACE          => () => BlastFurnace(name, tt, c, attr, hAttr)
      case Material.BLAZE_POWDER           => () => BlazeRod(name, tt, c, attr, hAttr)
      case Material.BLAZE_ROD              => () => BlazeRod(name, tt, c, attr, hAttr)
      case Material.BONE                   => () => Bone(name, tt, c, attr, hAttr)
      case Material.BONE_BLOCK             => () => BoneBlock(name, tt, c, attr, hAttr)
      case Material.BONE_MEAL              => () => BoneMeal(name, tt, c, attr, hAttr)
      case Material.BOOK                   => () => Book(name, tt, c, attr, hAttr)
      case Material.BOOKSHELF              => () => Bookshelf(name, tt, c, attr, hAttr)
      case Material.BOW                    => () => Bow(name, tt, attr, hAttr, dura, unbreak, hUnbreak)
      case Material.BOWL                   => () => Bowl(name, tt, c, attr, hAttr)
      case Material.BREAD                  => () => Bread(name, tt, c, attr, hAttr)
      case Material.BREWING_STAND          => () => BrewingStand(name, tt, c, attr, hAttr)
      case Material.BRICKS                 => () => BrickBlock(m[BrickMaterial], name, tt, c, attr, hAttr)
      case Material.CACTUS                 => () => Cactus(name, tt, c, attr, hAttr)
      case Material.CAKE                   => () => Cake(name, tt, attr, hAttr)
      case Material.CAMPFIRE               => () => Campfire(name, tt, c, attr, hAttr)
      case Material.CARROT                 => () => Carrot(name, tt, c, attr, hAttr)
      case Material.CARROT_ON_A_STICK      => () => CarrotOnAStick(name, tt, attr, hAttr, dura, unbreak, hUnbreak)
      case Material.CARTOGRAPHY_TABLE      => () => CartographyTable(name, tt, c, attr, hAttr)
      case Material.CARVED_PUMPKIN         => () => Pumpkin(carved = true, name, tt, c, attr, hAttr)
      case Material.CAULDRON               => () => Cauldron(name, tt, c, attr, hAttr)
      case Material.CHARCOAL               => () => Charcoal(name, tt, c, attr, hAttr)
      case Material.CHICKEN                => () => Chicken(cooked = false, name, tt, c, attr, hAttr)
      case Material.CHORUS_FLOWER          => () => ChorusFlower(name, tt, c, attr, hAttr)
      case Material.CHORUS_FRUIT           => () => ChorusFruit(popped = false, name, tt, c, attr, hAttr)
      case Material.CHORUS_PLANT           => () => ChorusPlant(name, tt, c, attr, hAttr)
      case Material.CLAY                   => () => ClayBlock(name, tt, c, attr, hAttr)
      case Material.CLAY_BALL              => () => Clay(name, tt, c, attr, hAttr)
      case Material.CLOCK                  => () => Clock(name, tt, c, attr, hAttr)
      case Material.COAL                   => () => Coal(name, tt, c, attr, hAttr)
      case Material.COAL_BLOCK             => () => CoalBlock(name, tt, c, attr, hAttr)
      case Material.COAL_ORE               => () => CoalOre(name, tt, c, attr, hAttr)
      case Material.COARSE_DIRT            => () => Dirt(coarse = true, name, tt, c, attr, hAttr)
      case Material.COBWEB                 => () => Cobweb(name, tt, c, attr, hAttr)
      case Material.COCOA_BEANS            => () => CocoaBeans(name, tt, c, attr, hAttr)
      case Material.COD                    => () => Cod(cooked = false, name, tt, c, attr, hAttr)
      case Material.COMPARATOR             => () => Comparator(name, tt, c, attr, hAttr)
      case Material.COMPASS                => () => Compass(name, tt, c, attr, hAttr)
      case Material.COMPOSTER              => () => Composter(name, tt, c, attr, hAttr)
      case Material.CONDUIT                => () => Conduit(name, tt, c, attr, hAttr)
      case Material.COOKED_BEEF            => () => Beef(cooked = true, name, tt, c, attr, hAttr)
      case Material.COOKED_CHICKEN         => () => Chicken(cooked = true, name, tt, c, attr, hAttr)
      case Material.COOKED_COD             => () => Cod(cooked = true, name, tt, c, attr, hAttr)
      case Material.COOKED_MUTTON          => () => Mutton(cooked = true, name, tt, c, attr, hAttr)
      case Material.COOKED_PORKCHOP        => () => Porkchop(cooked = true, name, tt, c, attr, hAttr)
      case Material.COOKED_RABBIT          => () => Rabbit(cooked = true, name, tt, c, attr, hAttr)
      case Material.COOKED_SALMON          => () => Salmon(cooked = true, name, tt, c, attr, hAttr)
      case Material.COOKIE                 => () => Cookie(name, tt, c, attr, hAttr)
      case Material.CRAFTING_TABLE         => () => CraftingTable(name, tt, c, attr, hAttr)
      case Material.CROSSBOW               => () => Crossbow(name, tt, attr, hAttr, dura, unbreak, hUnbreak)
      case Material.DARK_PRISMARINE        => () => Prismarine(m[PrismarineMaterial], name, tt, c, attr, hAttr)
      case Material.DAYLIGHT_DETECTOR      => () => DaylightDetector(name, tt, c, attr, hAttr)
      case Material.DEAD_BUSH              => () => DeadBush(name, tt, c, attr, hAttr)
      case Material.DEBUG_STICK            => () => DebugStick(name, tt, attr, hAttr)
      case Material.DIAMOND                => () => Diamond(name, tt, c, attr, hAttr)
      case Material.DIAMOND_BLOCK          => () => DiamondBlock(name, tt, c, attr, hAttr)
      case Material.DIAMOND_ORE            => () => DiamondOre(name, tt, c, attr, hAttr)
      case Material.DIRT                   => () => Dirt(coarse = false, name, tt, c, attr, hAttr)
      case Material.DISPENSER              => () => Dispenser(name, tt, c, attr, hAttr)
      case Material.DRAGON_BREATH          => () => DragonBreath(name, tt, c, attr, hAttr)
      case Material.DRAGON_EGG             => () => DragonEgg(name, tt, c, attr, hAttr)
      case Material.DRIED_KELP             => () => DriedKelp(name, tt, c, attr, hAttr)
      case Material.DRIED_KELP_BLOCK       => () => DriedKelpBlock(name, tt, c, attr, hAttr)
      case Material.DROPPER                => () => Dropper(name, tt, c, attr, hAttr)
      case Material.EGG                    => () => Egg(name, tt, c, attr, hAttr)
      case Material.ELYTRA                 => () => Elytra(name, tt, attr, hAttr, dura, unbreak, hUnbreak)
      case Material.EMERALD                => () => Emerald(name, tt, c, attr, hAttr)
      case Material.EMERALD_BLOCK          => () => EmeraldBlock(name, tt, c, attr, hAttr)
      case Material.EMERALD_ORE            => () => EmeraldOre(name, tt, c, attr, hAttr)
      case Material.ENCHANTED_BOOK         => () => EnchantedBook(name, tt, attr, hAttr)
      case Material.ENCHANTED_GOLDEN_APPLE => () => GoldenApple(enchanted = true, name, tt, c, attr, hAttr)
      case Material.ENCHANTING_TABLE       => () => EnchantingTable(name, tt, c, attr, hAttr)
      case Material.ENDER_EYE              => () => EnderEye(name, tt, c, attr, hAttr)
      case Material.ENDER_PEARL            => () => EnderPearl(name, tt, c, attr, hAttr)
      case Material.END_CRYSTAL            => () => EndCrystal(name, tt, c, attr, hAttr)
      case Material.END_PORTAL_FRAME       => () => EndPortalFrame(name, tt, c, attr, hAttr)
      case Material.END_ROD                => () => EndRod(name, tt, c, attr, hAttr)
      case Material.EXPERIENCE_BOTTLE      => () => BottleOfEnchanting(name, tt, c, attr, hAttr)
      case Material.FARMLAND               => () => Farmland(name, tt, c, attr, hAttr)
      case Material.FEATHER                => () => Feather(name, tt, c, attr, hAttr)
      case Material.FERMENTED_SPIDER_EYE   => () => SpiderEye(fermented = true, name, tt, c, attr, hAttr)
      case Material.FERN                   => () => Fern(tall = false, name, tt, c, attr, hAttr)
      case Material.FILLED_MAP             => () => Map(filled = true, name, tt, c, attr, hAttr)
      case Material.FIREWORK_ROCKET        => () => FireworkRocket(name, tt, c, attr, hAttr)
      case Material.FIREWORK_STAR          => () => FireworkStar(color, name, tt, c, attr, hAttr)
      case Material.FIRE_CHARGE            => () => FireCharge(name, tt, c, attr, hAttr)
      case Material.FISHING_ROD            => () => FishingRod(name, tt, attr, hAttr, dura, unbreak, hUnbreak)
      case Material.FLETCHING_TABLE        => () => FletchingTable(name, tt, c, attr, hAttr)
      case Material.FLINT                  => () => Flint(name, tt, c, attr, hAttr)
      case Material.FLINT_AND_STEEL        => () => FlintAndSteel(name, tt, attr, hAttr, dura, unbreak, hUnbreak)
      case Material.FLOWER_POT             => () => FlowerPot(name, tt, c, attr, hAttr)
      case Material.FURNACE                => () => Furnace(name, tt, c, attr, hAttr)
      case Material.GHAST_TEAR             => () => GhastTear(name, tt, c, attr, hAttr)
      case Material.GLASS_BOTTLE           => () => GlassBottle(name, tt, c, attr, hAttr)
      case Material.GLISTERING_MELON_SLICE => () => GoldenMelonSlice(name, tt, c, attr, hAttr)
      case Material.GLOWSTONE              => () => Glowstone(name, tt, c, attr, hAttr)
      case Material.GLOWSTONE_DUST         => () => GlowstoneDust(name, tt, c, attr, hAttr)
      case Material.GOLDEN_APPLE           => () => GoldenApple(enchanted = false, name, tt, c, attr, hAttr)
      case Material.GOLDEN_CARROT          => () => GoldenCarrot(name, tt, c, attr, hAttr)
      case Material.GOLD_BLOCK             => () => GoldBlock(name, tt, c, attr, hAttr)
      case Material.GOLD_INGOT             => () => GoldIngot(name, tt, c, attr, hAttr)
      case Material.GOLD_NUGGET            => () => GoldNugget(name, tt, c, attr, hAttr)
      case Material.GOLD_ORE               => () => GoldOre(name, tt, c, attr, hAttr)
      case Material.GRASS                  => () => Grass(tall = false, name, tt, c, attr, hAttr)
      case Material.GRASS_BLOCK            => () => GrassBlock(name, tt, c, attr, hAttr)
      case Material.GRASS_PATH             => () => GrassPath(name, tt, c, attr, hAttr)
      case Material.GRAVEL                 => () => Gravel(name, tt, c, attr, hAttr)
      case Material.GRINDSTONE             => () => Grindstone(name, tt, c, attr, hAttr)
      case Material.GUNPOWDER              => () => Gunpowder(name, tt, c, attr, hAttr)
      case Material.HAY_BLOCK              => () => HayBale(name, tt, c, attr, hAttr)
      case Material.HEART_OF_THE_SEA       => () => HeartOfTheSea(name, tt, c, attr, hAttr)
      case Material.HOPPER                 => () => Hopper(name, tt, c, attr, hAttr)
      case Material.INK_SAC                => () => InkSac(name, tt, c, attr, hAttr)
      case Material.IRON_BARS              => () => IronBars(name, tt, c, attr, hAttr)
      case Material.IRON_BLOCK             => () => IronBlock(name, tt, c, attr, hAttr)
      case Material.IRON_INGOT             => () => IronIngot(name, tt, c, attr, hAttr)
      case Material.IRON_NUGGET            => () => IronNugget(name, tt, c, attr, hAttr)
      case Material.IRON_ORE               => () => IronOre(name, tt, c, attr, hAttr)
      case Material.ITEM_FRAME             => () => ItemFrame(name, tt, c, attr, hAttr)
      case Material.JACK_O_LANTERN         => () => JackOfTheLantern(name, tt, c, attr, hAttr)
      case Material.JIGSAW                 => () => JigsawBlock(name, tt, c, attr, hAttr)
      case Material.JUKEBOX                => () => Jukebox(name, tt, c, attr, hAttr)
      case Material.KELP                   => () => Kelp(name, tt, c, attr, hAttr)
      case Material.KNOWLEDGE_BOOK         => () => KnowledgeBook(name, tt, attr, hAttr)
      case Material.LADDER                 => () => Ladder(name, tt, c, attr, hAttr)
      case Material.LANTERN                => () => Lantern(name, tt, c, attr, hAttr)
      case Material.LAPIS_BLOCK            => () => LapisBlock(name, tt, c, attr, hAttr)
      case Material.LAPIS_LAZULI           => () => Lapis(name, tt, c, attr, hAttr)
      case Material.LAPIS_ORE              => () => LapisOre(name, tt, c, attr, hAttr)
      case Material.LARGE_FERN             => () => Fern(tall = true, name, tt, c, attr, hAttr)
      case Material.LEAD                   => () => Lead(name, tt, c, attr, hAttr)
      case Material.LEATHER                => () => Leather(name, tt, c, attr, hAttr)
      case Material.LECTERN                => () => Lectern(name, tt, c, attr, hAttr)
      case Material.LEVER                  => () => Lever(name, tt, c, attr, hAttr)
      case Material.LILY_PAD               => () => LilyPad(name, tt, c, attr, hAttr)
      case Material.LOOM                   => () => Loom(name, tt, c, attr, hAttr)
      case Material.MAGMA_BLOCK            => () => MagmaBlock(name, tt, c, attr, hAttr)
      case Material.MAGMA_CREAM            => () => MagmaCream(name, tt, c, attr, hAttr)
      case Material.MAP                    => () => Map(filled = false, name, tt, c, attr, hAttr)
      case Material.MELON                  => () => Melon(name, tt, c, attr, hAttr)
      case Material.MELON_SLICE            => () => MelonSlice(name, tt, c, attr, hAttr)
      case Material.MUTTON                 => () => Mutton(cooked = false, name, tt, c, attr, hAttr)
      case Material.MYCELIUM               => () => Mycelium(name, tt, c, attr, hAttr)
      case Material.NAME_TAG               => () => NameTag(name, tt, c, attr, hAttr)
      case Material.NAUTILUS_SHELL         => () => NautilusShell(name, tt, c, attr, hAttr)
      case Material.NETHERRACK             => () => Netherrack(name, tt, c, attr, hAttr)
      case Material.NETHER_BRICKS          => () => BrickBlock(m[BrickMaterial], name, tt, c, attr, hAttr)
      case Material.NETHER_QUARTZ_ORE      => () => QuartzOre(name, tt, c, attr, hAttr)
      case Material.NETHER_STAR            => () => NetherStar(name, tt, c, attr, hAttr)
      case Material.NETHER_WART            => () => NetherWart(name, tt, c, attr, hAttr)
      case Material.NETHER_WART_BLOCK      => () => NetherWartBlock(name, tt, c, attr, hAttr)
      case Material.NOTE_BLOCK             => () => NoteBlock(name, tt, c, attr, hAttr)
      case Material.OBSERVER               => () => Observer(name, tt, c, attr, hAttr)
      case Material.OBSIDIAN               => () => Obsidian(name, tt, c, attr, hAttr)
      case Material.PAINTING               => () => Painting(name, tt, c, attr, hAttr)
      case Material.PAPER                  => () => Paper(name, tt, c, attr, hAttr)
      case Material.PHANTOM_MEMBRANE       => () => PhantomMembrane(name, tt, c, attr, hAttr)
      case Material.PISTON                 => () => Piston(sticky = false, name, tt, c, attr, hAttr)
      case Material.PODZOL                 => () => Podzol(name, tt, c, attr, hAttr)
      case Material.POISONOUS_POTATO       => () => PoisonousPotato(name, tt, c, attr, hAttr)
      case Material.POPPED_CHORUS_FRUIT    => () => ChorusFruit(popped = true, name, tt, c, attr, hAttr)
      case Material.PORKCHOP               => () => Porkchop(cooked = false, name, tt, c, attr, hAttr)
      case Material.POTATO                 => () => Potato(cooked = false, name, tt, c, attr, hAttr)
      case Material.PRISMARINE             => () => Prismarine(m[PrismarineMaterial], name, tt, c, attr, hAttr)
      case Material.PRISMARINE_BRICKS      => () => Prismarine(m[PrismarineMaterial], name, tt, c, attr, hAttr)
      case Material.PRISMARINE_CRYSTALS    => () => PrismarineCrystals(name, tt, c, attr, hAttr)
      case Material.PRISMARINE_SHARD       => () => PrismarineShard(name, tt, c, attr, hAttr)
      case Material.PUFFERFISH             => () => Pufferfish(name, tt, c, attr, hAttr)
      case Material.PUMPKIN                => () => Pumpkin(carved = false, name, tt, c, attr, hAttr)
      case Material.PUMPKIN_PIE            => () => PumpkinPie(name, tt, c, attr, hAttr)
      case Material.PURPUR_BLOCK           => () => PurpurBlock(name, tt, c, attr, hAttr)
      case Material.QUARTZ                 => () => Quartz(name, tt, c, attr, hAttr)
      case Material.RABBIT                 => () => Rabbit(cooked = false, name, tt, c, attr, hAttr)
      case Material.RABBIT_FOOT            => () => RabbitFoot(name, tt, c, attr, hAttr)
      case Material.RABBIT_HIDE            => () => RabbitHide(name, tt, c, attr, hAttr)
      case Material.REDSTONE               => () => Redstone(name, tt, c, attr, hAttr)
      case Material.REDSTONE_BLOCK         => () => RedstoneBlock(name, tt, c, attr, hAttr)
      case Material.REDSTONE_LAMP          => () => RedstoneLamp(name, tt, c, attr, hAttr)
      case Material.REDSTONE_ORE           => () => RedstoneOre(name, tt, c, attr, hAttr)
      case Material.REDSTONE_TORCH         => () => RedstoneTorch(name, tt, c, attr, hAttr)
      case Material.RED_NETHER_BRICKS      => () => BrickBlock(m[BrickMaterial], name, tt, c, attr, hAttr)
      case Material.REPEATER               => () => Repeater(name, tt, c, attr, hAttr)
      case Material.ROTTEN_FLESH           => () => RottenFlesh(name, tt, c, attr, hAttr)
      case Material.SADDLE                 => () => Saddle(name, tt, attr, hAttr)
      case Material.SALMON                 => () => Salmon(cooked = false, name, tt, c, attr, hAttr)
      case Material.SCAFFOLDING            => () => Scaffolding(name, tt, c, attr, hAttr)
      case Material.SCUTE                  => () => Scute(name, tt, c, attr, hAttr)
      case Material.SEAGRASS               => () => Seagrass(name, tt, c, attr, hAttr)
      case Material.SEA_LANTERN            => () => SeaLantern(name, tt, c, attr, hAttr)
      case Material.SEA_PICKLE             => () => SeaPickle(name, tt, c, attr, hAttr)
      case Material.SHEARS                 => () => Shears(name, tt, attr, hAttr, dura, unbreak, hUnbreak)
      case Material.SHIELD                 => () => Shield(name, tt, attr, hAttr, dura, unbreak, hUnbreak)
      case Material.SHULKER_SHELL          => () => ShulkerShell(name, tt, c, attr, hAttr)
      case Material.SLIME_BALL             => () => Slimeball(name, tt, c, attr, hAttr)
      case Material.SLIME_BLOCK            => () => SlimeBlock(name, tt, c, attr, hAttr)
      case Material.SMITHING_TABLE         => () => SmithingTable(name, tt, c, attr, hAttr)
      case Material.SMOKER                 => () => Smoker(name, tt, c, attr, hAttr)
      case Material.SNOW                   => () => Snow(name, tt, c, attr, hAttr)
      case Material.SNOWBALL               => () => Snowball(name, tt, c, attr, hAttr)
      case Material.SNOW_BLOCK             => () => SnowBlock(name, tt, c, attr, hAttr)
      case Material.SOUL_SAND              => () => SoulSand(name, tt, c, attr, hAttr)
      case Material.SPAWNER                => () => Spawner(name, tt, c, attr, hAttr)
      case Material.SPIDER_EYE             => () => SpiderEye(fermented = false, name, tt, c, attr, hAttr)
      case Material.SPONGE                 => () => Sponge(wet = false, name, tt, c, attr, hAttr)
      case Material.STICK                  => () => Stick(name, tt, c, attr, hAttr)
      case Material.STICKY_PISTON          => () => Piston(sticky = true, name, tt, c, attr, hAttr)
      case Material.STONECUTTER            => () => Stonecutter(name, tt, c, attr, hAttr)
      case Material.STRING                 => () => PieceOfString(name, tt, c, attr, hAttr)
      case Material.SUGAR                  => () => Sugar(name, tt, c, attr, hAttr)
      case Material.SUGAR_CANE             => () => SugarCane(name, tt, c, attr, hAttr)
      case Material.SWEET_BERRIES          => () => SweetBerries(name, tt, c, attr, hAttr)
      case Material.TALL_GRASS             => () => Grass(tall = true, name, tt, c, attr, hAttr)
      case Material.TNT                    => () => TNT(name, tt, c, attr, hAttr)
      case Material.TORCH                  => () => Torch(name, tt, c, attr, hAttr)
      case Material.TOTEM_OF_UNDYING       => () => TotemOfUndying(name, tt, attr, hAttr)
      case Material.TRIDENT                => () => Trident(name, tt, attr, hAttr, dura, unbreak, hUnbreak)
      case Material.TRIPWIRE_HOOK          => () => TripwireHook(name, tt, c, attr, hAttr)
      case Material.TROPICAL_FISH          => () => TropicalFish(name, tt, c, attr, hAttr)
      case Material.TURTLE_EGG             => () => TurtleEgg(name, tt, c, attr, hAttr)
      case Material.TURTLE_HELMET          => () => TurtleHelmet(name, tt, attr, hAttr, dura, unbreak, hUnbreak)
      case Material.VINE                   => () => Vine(name, tt, c, attr, hAttr)
      case Material.WET_SPONGE             => () => Sponge(wet = true, name, tt, c, attr, hAttr)
      case Material.WHEAT                  => () => Wheat(name, tt, c, attr, hAttr)
      case Material.WRITABLE_BOOK          => () => BookAndQuill(name, tt, attr, hAttr)
      case Material.WRITTEN_BOOK           => () => WrittenBook(name, tt, c, attr, hAttr)

      // FLOWER
      case Material.ALLIUM | Material.AZURE_BLUET | Material.BLUE_ORCHID |
           Material.CORNFLOWER | Material.DANDELION | Material.LILY_OF_THE_VALLEY |
           Material.ORANGE_TULIP | Material.OXEYE_DAISY | Material.PINK_TULIP |
           Material.POPPY | Material.RED_TULIP | Material.WHITE_TULIP |
           Material.WITHER_ROSE => () =>
        Flower(v[FlowerVariant], name, tt, c, attr, hAttr)

      // PLANT TODO split these up into 4 items?
      case Material.LILAC | Material.PEONY | Material.ROSE_BUSH |
           Material.SUNFLOWER => () =>
        Plant(v[PlantVariant], name, tt, c, attr, hAttr)

      // QUARTZ_BLOCK
      case Material.QUARTZ_BLOCK | Material.CHISELED_QUARTZ_BLOCK |
           Material.SMOOTH_QUARTZ => () =>
        QuartzBlock(v[QuartzBlockVariant], name, tt, c, attr, hAttr)

      case _ => item.getType.name match {

        // matched first to avoid name overlap when sorted alphabetically
        case r".*PICKAXE"      => () => Pickaxe(m[ToolMaterial], name, tt, c, attr, hAttr)
        case r"STRIPPED.*LOG"  => () => Log(m[WoodMaterial], stripped = true, name, tt, c, attr, hAttr)
        case r"STRIPPED.*WOOD" => () => Wood(m[WoodMaterial], stripped = true, name, tt, c, attr, hAttr)

        case r".*SANDSTONE" => () =>
          Sandstone(m[SandstoneMaterial], v[SandstoneVariant], name, tt, c, attr, hAttr)

        // TODO SaplingVariant.BAMBOO is illegal on an item, make separate SaplingVariant for items?
        // TODO merge GlazedTerracotta and Terracotta like Log and Wood with stripped?
        // TODO or split Log and Wood into StrippedLog and StrippedWood instead?
        case r".*ANDESITE"          => () => Andesite(v[StoniteVariant], name, tt, c, attr, hAttr)
        case r".*ANVIL"             => () => Anvil(v[AnvilVariant], name, tt, c, attr, hAttr)
        case r".*ARROW"             => () => Arrow(v[ArrowVariant], name, tt, c, attr, hAttr)
        case r".*AXE"               => () => Axe(m[ToolMaterial], name, tt, c, attr, hAttr)
        case r".*BANNER"            => () => Banner(color, name, tt, c, attr, hAttr)
        case r".*BANNER_PATTERN"    => () => BannerPattern(v[BannerPatternVariant], name, tt, attr, hAttr)
        case r".*BED"               => () => Bed(color, name, tt, c, attr, hAttr)
        case r".*BOAT"              => () => Boat(m[WoodMaterial], name, tt, c, attr, hAttr)
        case r".*BOOTS"             => () => Boots(m[ArmorMaterial], name, tt, c, attr, hAttr)
        case r".*BRICK"             => () => Brick(m[BrickMaterial], name, tt, c, attr, hAttr)
        case r".*BUCKET"            => () => Bucket(v[BucketVariant], name, tt, c, attr, hAttr)
        case r".*BUTTON"            => () => Button(m[ButtonMaterial], name, tt, c, attr, hAttr)
        case r".*CARPET"            => () => Carpet(color, name, tt, c, attr, hAttr)
        case r".*CHEST"             => () => Chest(v[ChestVariant], name, tt, c, attr, hAttr)
        case r".*CHESTPLATE"        => () => Chestplate(m[ArmorMaterial], name, tt, c, attr, hAttr)
        case r".*COBBLESTONE"       => () => Cobblestone(v[CobblestoneVariant], name, tt, c, attr, hAttr)
        case r".*COMMAND_BLOCK"     => () => CommandBlock(v[CommandBlockVariant], name, tt, c, attr, hAttr)
        case r".*CONCRETE"          => () => Concrete(color, name, tt, c, attr, hAttr)
        case r".*CONCRETE_POWDER"   => () => ConcretePowder(color, name, tt, c, attr, hAttr)
        case r".*CORAL"             => () => Coral(v[CoralVariant], name, tt, c, attr, hAttr)
        case r".*CORAL_BLOCK"       => () => CoralBlock(v[CoralVariant], name, tt, c, attr, hAttr)
        case r".*CORAL_FAN"         => () => CoralFan(v[CoralVariant], name, tt, c, attr, hAttr)
        case r".*DIORITE"           => () => Diorite(v[StoniteVariant], name, tt, c, attr, hAttr)
        case r".*DOOR"              => () => Door(m[DoorMaterial], name, tt, c, attr, hAttr)
        case r".*DYE"               => () => Dye(color, name, tt, c, attr, hAttr)
        case r"END_STONE.*"         => () => EndStone(m[EndStoneMaterial], name, tt, c, attr, hAttr)
        case r".*FENCE"             => () => Fence(m[FenceMaterial], name, tt, c, attr, hAttr)
        case r".*GATE"              => () => Gate(m[WoodMaterial], name, tt, c, attr, hAttr)
        case r".*GLASS"             => () => Glass(Some(color), name, tt, c, attr, hAttr)
        case r".*GLASS_PANE"        => () => GlassPane(Some(color), name, tt, c, attr, hAttr)
        case r".*GLAZED_TERRACOTTA" => () => GlazedTerracotta(color, name, tt, c, attr, hAttr)
        case r".*GRANITE"           => () => Granite(v[StoniteVariant], name, tt, c, attr, hAttr)
        case r".*HEAD"              => () => MobHead(v[MobHeadVariant], name, tt, c, attr, hAttr)
        case r".*HELMET"            => () => Helmet(m[ArmorMaterial], name, tt, c, attr, hAttr)
        case r".*HOE"               => () => Hoe(m[ToolMaterial], name, tt, c, attr, hAttr)
        case r".*HORSE_ARMOR"       => () => HorseArmor(v[HorseArmorVariant], name, tt, c, attr, hAttr)
        case r".*ICE"               => () => Ice(v[IceVariant], name, tt, c, attr, hAttr)
        case r".*LEAVES"            => () => Leaves(m[WoodMaterial], name, tt, c, attr, hAttr)
        case r".*LEGGINGS"          => () => Leggings(m[ArmorMaterial], name, tt, c, attr, hAttr)
        case r".*LOG"               => () => Log(m[WoodMaterial], stripped = false, name, tt, c, attr, hAttr)
        case r".*MINECART"          => () => Minecart(v[MinecartVariant], name, tt, c, attr, hAttr)
        case r".*MUSHROOM"          => () => Mushroom(v[MushroomVariant], name, tt, c, attr, hAttr)
        case r".*MUSHROOM_BLOCK"    => () => MushroomBlock(v[MushroomBlockVariant], name, tt, c, attr, hAttr)
        case r"MUSIC_DISC.*"        => () => MusicDisc(v[MusicDiscVariant], name, tt, c, attr, hAttr)
        case r".*PILLAR"            => () => Pillar(m[PillarMaterial], name, tt, c, attr, hAttr)
        case r".*PLANKS"            => () => Planks(m[WoodMaterial], name, tt, c, attr, hAttr)
        case r".*POTION"            => () => Potion(v[PotionVariant], hEffects, name, tt, attr, hAttr)
        case r".*PRESSURE_PLATE"    => () => PressurePlate(m[PressurePlateMaterial], name, tt, c, attr, hAttr)
        case r".*RAIL"              => () => Rail(v[RailVariant], name, tt, c, attr, hAttr) // TODO rename
        case r".*SAND"              => () => Sand(m[SandMaterial], name, tt, c, attr, hAttr)
        case r".*SAPLING"           => () => Sapling(v[SaplingVariant], name, tt, c, attr, hAttr)
        case r".*SEEDS"             => () => Seeds(v[SeedsVariant], name, tt, c, attr, hAttr)
        case r".*SHOVEL"            => () => Shovel(m[ToolMaterial], name, tt, c, attr, hAttr)
        case r".*SHULKER_BOX"       => () => ShulkerBox(Some(color), name, tt, c, attr, hAttr)
        case r".*SIGN"              => () => Sign(m[WoodMaterial], name, tt, c, attr, hAttr)
        case r".*SKULL"             => () => MobHead(v[MobHeadVariant], name, tt, c, attr, hAttr)
        case r".*SLAB"              => () => Slab(m[SlabMaterial], Some(v[SlabVariant]), name, tt, c, attr, hAttr)
        case r".*SPAWN_EGG"         => () => SpawnEgg(v[SpawnEggVariant], name, tt, c, attr, hAttr)
        case r".*STEW"              => () => Stew(v[StewVariant], name, tt, c, attr, hAttr)
        case r".*STONE"             => () => Stone(m[StoneMaterial], v[StoneVariant], name, tt, c, attr, hAttr)
        case r"STRUCTURE.*"         => () => StructureBlock(v[StructureBlockVariant], name, tt, c, attr, hAttr)
        case r".*SWORD"             => () => Sword(m[ToolMaterial], name, tt, c, attr, hAttr)
        case r".*TERRACOTTA"        => () => Terracotta(Some(color), name, tt, c, attr, hAttr)
        case r".*TRAPDOOR"          => () => Trapdoor(m[TrapdoorMaterial], name, tt, c, attr, hAttr)
        case r".*WALL"              => () => Wall(m[WallMaterial], Some(v[WallVariant]), name, tt, c, attr, hAttr)
        case r".*WOOD"              => () => Wood(m[WoodMaterial], stripped = false, name, tt, c, attr, hAttr)
        case r".*WOOL"              => () => Wool(color, name, tt, c, attr, hAttr)

        case r"INFESTED.*" => () =>
          InfestedBlock(m[InfestedMaterial], Some(v[InfestedVariant]), name, tt, c, attr, hAttr)

        case r".*STAIRS" => () =>
          Stairs(m[StairsMaterial], Some(v[StairsVariant]), name, tt, c, attr, hAttr)

        case r".*WEIGHTED_PRESSURE_PLATE" => () =>
          WeightedPressurePlate(v[WeightedPressurePlateVariant], name, tt, c, attr, hAttr)

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
