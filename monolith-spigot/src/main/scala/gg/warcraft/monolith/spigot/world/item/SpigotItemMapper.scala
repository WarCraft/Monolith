package gg.warcraft.monolith.spigot.world.item

import java.util

import gg.warcraft.monolith.api.core.Extensions.Regex
import gg.warcraft.monolith.api.world.block.{ BlockColor, ButtonMaterial, DoorMaterial, FenceMaterial,
  InfestedMaterial, InfestedVariant, PillarMaterial, PressurePlateMaterial, SlabMaterial, SlabVariant,
  StairsMaterial, StairsVariant, TrapdoorMaterial, WallMaterial, WallVariant }
import gg.warcraft.monolith.api.world.block.material.{ BrickMaterial, EndStoneMaterial, SandMaterial,
  SandstoneMaterial, WoodMaterial }
import gg.warcraft.monolith.api.world.block.variant._
import gg.warcraft.monolith.api.world.item._
import gg.warcraft.monolith.api.world.item.material.{ ArmorMaterial, ToolMaterial }
import gg.warcraft.monolith.api.world.item.variant._
import javax.inject.Inject
import org.bukkit.Material

class SpigotItemMapper @Inject()(
    private val colorMapper: SpigotItemColorMapper,
    private val materialMapper: SpigotItemMaterialMapper,
    private val variantMapper: SpigotItemVariantMapper
) {
  private val cache =
    new util.EnumMap[Material, () => Item](classOf[Material])

  def map(item: SpigotItemStack): Item = {
    implicit val implicitItem: SpigotItemStack = item

    def data(implicit itemStack: SpigotItemStack): ItemData = {
      ItemData(itemStack.getItemMeta.getDisplayName)
      // TODO map other vals
    }

    def color(implicit itemStack: SpigotItemStack): BlockColor =
      colorMapper.map(itemStack.getType)

    def m[T <: ItemMaterial](implicit itemStack: SpigotItemStack): T =
      materialMapper.map(itemStack.getType).asInstanceOf[T]

    def v[T <: ItemVariant](implicit itemStack: SpigotItemStack): T =
      variantMapper.map(itemStack).asInstanceOf[T]

    val itemBuilder = cache.computeIfAbsent(item.getType, {
      case Material.AIR                    => () => null
      case Material.APPLE                  => () => Apple(data)
      case Material.ARMOR_STAND            => () => ArmorStand(data)
      case Material.BAKED_POTATO           => () => Potato(cooked = true, data)
      case Material.BAMBOO                 => () => Bamboo(data)
      case Material.BARREL                 => () => Barrel(data)
      case Material.BARRIER                => () => Barrier(data)
      case Material.BEACON                 => () => Beacon(data)
      case Material.BEDROCK                => () => Bedrock(data)
      case Material.BEEF                   => () => Beef(cooked = false, data)
      case Material.BEETROOT               => () => Beetroot(data)
      case Material.BEETROOT_SEEDS         => () => null
      case Material.BEETROOT_SOUP          => () => null
      case Material.BELL                   => () => Bell(data)
      case Material.BLAST_FURNACE          => () => BlastFurnace(data)
      case Material.BLAZE_POWDER           => () => BlazeRod(data)
      case Material.BLAZE_ROD              => () => BlazeRod(data)
      case Material.BONE                   => () => Bone(data)
      case Material.BONE_BLOCK             => () => BoneBlock(data)
      case Material.BONE_MEAL              => () => BoneMeal(data)
      case Material.BOOK                   => () => null
      case Material.BOOKSHELF              => () => Bookshelf(data)
      case Material.BOW                    => () => Bow(data)
      case Material.BOWL                   => () => Bowl(data)
      case Material.BREAD                  => () => Bread(data)
      case Material.BREWING_STAND          => () => BrewingStand(data)
      case Material.BRICKS                 => () => null
      case Material.CACTUS                 => () => Cactus(data)
      case Material.CAKE                   => () => Cake(data)
      case Material.CAMPFIRE               => () => Campfire(data)
      case Material.CARROT                 => () => Carrot(data)
      case Material.CARROT_ON_A_STICK      => () => CarrotOnAStick(data)
      case Material.CARTOGRAPHY_TABLE      => () => CartographyTable(data)
      case Material.CARVED_PUMPKIN         => () => Pumpkin(carved = true, data)
      case Material.CAULDRON               => () => Cauldron(data)
      case Material.CHARCOAL               => () => Charcoal(data)
      case Material.CHICKEN                => () => Chicken(cooked = false, data)
      case Material.CHISELED_STONE_BRICKS  => () => null
      case Material.CHORUS_FLOWER          => () => ChorusFlower(data)
      case Material.CHORUS_FRUIT           => () => ChorusFruit(popped = false, data)
      case Material.CHORUS_PLANT           => () => ChorusPlant(data)
      case Material.CLAY                   => () => ClayBlock(data)
      case Material.CLAY_BALL              => () => Clay(data)
      case Material.CLOCK                  => () => Clock(data)
      case Material.COAL                   => () => Coal(data)
      case Material.COAL_BLOCK             => () => CoalBlock(data)
      case Material.COAL_ORE               => () => CoalOre(data)
      case Material.COARSE_DIRT            => () => Dirt(coarse = true, data)
      case Material.COBBLESTONE            => () => null
      case Material.COBWEB                 => () => Cobweb(data)
      case Material.COCOA_BEANS            => () => CocoaBeans(data)
      case Material.COD                    => () => Cod(cooked = false, data)
      case Material.COMPARATOR             => () => Comparator(data)
      case Material.COMPASS                => () => Compass(data)
      case Material.COMPOSTER              => () => Composter(data)
      case Material.CONDUIT                => () => Conduit(data)
      case Material.COOKED_BEEF            => () => Beef(cooked = true, data)
      case Material.COOKED_CHICKEN         => () => Chicken(cooked = true, data)
      case Material.COOKED_COD             => () => Cod(cooked = true, data)
      case Material.COOKED_MUTTON          => () => Mutton(cooked = true, data)
      case Material.COOKED_PORKCHOP        => () => Porkchop(cooked = true, data)
      case Material.COOKED_RABBIT          => () => Rabbit(cooked = true, data)
      case Material.COOKED_SALMON          => () => Salmon(cooked = true, data)
      case Material.COOKIE                 => () => Cookie(data)
      case Material.CRACKED_STONE_BRICKS   => () => null
      case Material.CRAFTING_TABLE         => () => CraftingTable(data)
      case Material.CROSSBOW               => () => Crossbow(data)
      case Material.DARK_PRISMARINE        => () => null
      case Material.DAYLIGHT_DETECTOR      => () => DaylightDetector(data)
      case Material.DEAD_BUSH              => () => null
      case Material.DEBUG_STICK            => () => DebugStick(data)
      case Material.DIAMOND                => () => Diamond(data)
      case Material.DIAMOND_BLOCK          => () => DiamondBlock(data)
      case Material.DIAMOND_ORE            => () => DiamondOre(data)
      case Material.DIRT                   => () => Dirt(coarse = false, data)
      case Material.DISPENSER              => () => Dispenser(data)
      case Material.DRAGON_BREATH          => () => DragonBreath(data)
      case Material.DRAGON_EGG             => () => DragonEgg(data)
      case Material.DRIED_KELP             => () => DriedKelp(data)
      case Material.DRIED_KELP_BLOCK       => () => DriedKelpBlock(data)
      case Material.DROPPER                => () => Dropper(data)
      case Material.EGG                    => () => Egg(data)
      case Material.ELYTRA                 => () => Elytra(data)
      case Material.EMERALD                => () => Emerald(data)
      case Material.EMERALD_BLOCK          => () => EmeraldBlock(data)
      case Material.EMERALD_ORE            => () => EmeraldOre(data)
      case Material.ENCHANTED_BOOK         => () => null
      case Material.ENCHANTED_GOLDEN_APPLE => () => null
      case Material.ENCHANTING_TABLE       => () => EnchantingTable(data)
      case Material.ENDER_EYE              => () => EnderEye(data)
      case Material.ENDER_PEARL            => () => EnderPearl(data)
      case Material.END_CRYSTAL            => () => EndCrystal(data)
      case Material.END_PORTAL_FRAME       => () => EndPortalFrame(data)
      case Material.END_ROD                => () => EndRod(data)
      case Material.EXPERIENCE_BOTTLE      => () => null
      case Material.FARMLAND               => () => Farmland(data)
      case Material.FEATHER                => () => Feather(data)
      case Material.FERMENTED_SPIDER_EYE   => () => SpiderEye(fermented = true, data)
      case Material.FERN                   => () => null
      case Material.FILLED_MAP             => () => null
      case Material.FIREWORK_ROCKET        => () => null
      case Material.FIREWORK_STAR          => () => null
      case Material.FIRE_CHARGE            => () => null
      case Material.FISHING_ROD            => () => FishingRod(data)
      case Material.FLETCHING_TABLE        => () => FletchingTable(data)
      case Material.FLINT                  => () => Flint(data)
      case Material.FLINT_AND_STEEL        => () => FlintAndSteel(data)
      case Material.FLOWER_POT             => () => FlowerPot(data)
      case Material.FURNACE                => () => Furnace(data)
      case Material.GHAST_TEAR             => () => GhastTear(data)
      case Material.GLASS_BOTTLE           => () => GlassBottle(data)
      case Material.GLISTERING_MELON_SLICE => () => GoldenMelonSlice(data)
      case Material.GLOWSTONE              => () => Glowstone(data)
      case Material.GLOWSTONE_DUST         => () => GlowstoneDust(data)
      case Material.GOLDEN_APPLE           => () => GoldenApple(enchanted = false, data)
      case Material.GOLDEN_CARROT          => () => GoldenCarrot(data)
      case Material.GOLD_BLOCK             => () => GoldBlock(data)
      case Material.GOLD_INGOT             => () => GoldIngot(data)
      case Material.GOLD_NUGGET            => () => GoldNugget(data)
      case Material.GOLD_ORE               => () => GoldOre(data)
      case Material.GRASS                  => () => Grass(tall = false, data)
      case Material.GRASS_BLOCK            => () => GrassBlock(data)
      case Material.GRASS_PATH             => () => GrassPath(data)
      case Material.GRAVEL                 => () => Gravel(data)
      case Material.GRINDSTONE             => () => Grindstone(data)
      case Material.GUNPOWDER              => () => Gunpowder(data)
      case Material.HAY_BLOCK              => () => HayBale(data)
      case Material.HEART_OF_THE_SEA       => () => HeartOfTheSea(data)
      case Material.HOPPER                 => () => Hopper(data)
      case Material.INK_SAC                => () => InkSac(data)
      case Material.IRON_BARS              => () => IronBars(data)
      case Material.IRON_BLOCK             => () => IronBlock(data)
      case Material.IRON_INGOT             => () => IronIngot(data)
      case Material.IRON_NUGGET            => () => IronNugget(data)
      case Material.IRON_ORE               => () => IronOre(data)
      case Material.ITEM_FRAME             => () => ItemFrame(data)
      case Material.JACK_O_LANTERN         => () => JackOfTheLantern(data)
      case Material.JIGSAW                 => () => JigsawBlock(data)
      case Material.JUKEBOX                => () => Jukebox(data)
      case Material.KELP                   => () => Kelp(data)
      case Material.KNOWLEDGE_BOOK         => () => null
      case Material.LADDER                 => () => Ladder(data)
      case Material.LANTERN                => () => Lantern(data)
      case Material.LAPIS_BLOCK            => () => LapisBlock(data)
      case Material.LAPIS_LAZULI           => () => Lapis(data)
      case Material.LAPIS_ORE              => () => LapisOre(data)
      case Material.LARGE_FERN             => () => null
      case Material.LEAD                   => () => Lead(data)
      case Material.LEATHER                => () => Leather(data)
      case Material.LECTERN                => () => Lectern(data)
      case Material.LEVER                  => () => Lever(data)
      case Material.LILY_PAD               => () => LilyPad(data)
      case Material.LINGERING_POTION       => () => null
      case Material.LOOM                   => () => Loom(data)
      case Material.MAGMA_BLOCK            => () => MagmaBlock(data)
      case Material.MAGMA_CREAM            => () => MagmaCream(data)
      case Material.MAP                    => () => null
      case Material.MELON                  => () => Melon(data)
      case Material.MELON_SEEDS            => () => null
      case Material.MELON_SLICE            => () => MelonSlice(data)
      case Material.MOSSY_COBBLESTONE      => () => null
      case Material.MOSSY_STONE_BRICKS     => () => null
      case Material.MUSHROOM_STEW          => () => null
      case Material.MUTTON                 => () => Mutton(cooked = false, data)
      case Material.MYCELIUM               => () => Mycelium(data)
      case Material.NAME_TAG               => () => NameTag(data)
      case Material.NAUTILUS_SHELL         => () => NautilusShell(data)
      case Material.NETHERRACK             => () => Netherrack(data)
      case Material.NETHER_BRICKS          => () => null
      case Material.NETHER_QUARTZ_ORE      => () => QuartzOre(data)
      case Material.NETHER_STAR            => () => NetherStar(data)
      case Material.NETHER_WART            => () => NetherWart(data)
      case Material.NETHER_WART_BLOCK      => () => NetherWartBlock(data)
      case Material.NOTE_BLOCK             => () => NoteBlock(data)
      case Material.OBSERVER               => () => Observer(data)
      case Material.OBSIDIAN               => () => Obsidian(data)
      case Material.PAINTING               => () => Painting(data)
      case Material.PAPER                  => () => Paper(data)
      case Material.PETRIFIED_OAK_SLAB     => () => null
      case Material.PHANTOM_MEMBRANE       => () => PhantomMembrane(data)
      case Material.PISTON                 => () => Piston(sticky = false, data)
      case Material.PODZOL                 => () => Podzol(data)
      case Material.POISONOUS_POTATO       => () => PoisonousPotato(data)
      case Material.POPPED_CHORUS_FRUIT    => () => ChorusFruit(popped = true, data)
      case Material.PORKCHOP               => () => Porkchop(cooked = false, data)
      case Material.POTATO                 => () => Potato(cooked = false, data)
      case Material.POTION                 => () => null
      case Material.PRISMARINE             => () => null
      case Material.PRISMARINE_BRICKS      => () => null
      case Material.PRISMARINE_CRYSTALS    => () => PrismarineCrystals(data)
      case Material.PRISMARINE_SHARD       => () => PrismarineShard(data)
      case Material.PUFFERFISH             => () => Pufferfish(data)
      case Material.PUMPKIN                => () => Pumpkin(carved = false, data)
      case Material.PUMPKIN_PIE            => () => PumpkinPie(data)
      case Material.PUMPKIN_SEEDS          => () => null
      case Material.PURPUR_BLOCK           => () => Purpur(data) // TODO name PurpurBlock?
      case Material.QUARTZ                 => () => Quartz(data)
      case Material.RABBIT                 => () => Rabbit(cooked = false, data)
      case Material.RABBIT_FOOT            => () => RabbitFoot(data)
      case Material.RABBIT_HIDE            => () => RabbitHide(data)
      case Material.RABBIT_STEW            => () => null
      case Material.REDSTONE               => () => Redstone(data)
      case Material.REDSTONE_BLOCK         => () => RedstoneBlock(data)
      case Material.REDSTONE_LAMP          => () => RedstoneLamp(data)
      case Material.REDSTONE_ORE           => () => RedstoneOre(data)
      case Material.REDSTONE_TORCH         => () => RedstoneTorch(data)
      case Material.RED_NETHER_BRICKS      => () => null
      case Material.REPEATER               => () => Repeater(data)
      case Material.ROTTEN_FLESH           => () => RottenFlesh(data)
      case Material.SADDLE                 => () => Saddle(data)
      case Material.SALMON                 => () => Salmon(cooked = false, data)
      case Material.SCAFFOLDING            => () => Scaffolding(data)
      case Material.SCUTE                  => () => Scute(data)
      case Material.SEAGRASS               => () => Seagrass(data)
      case Material.SEA_LANTERN            => () => SeaLantern(data)
      case Material.SEA_PICKLE             => () => SeaPickle(data)
      case Material.SHEARS                 => () => Shears(data)
      case Material.SHIELD                 => () => Shield(data)
      case Material.SHULKER_SHELL          => () => ShulkerShell(data)
      case Material.SLIME_BALL             => () => Slimeball(data)
      case Material.SLIME_BLOCK            => () => SlimeBlock(data)
      case Material.SMITHING_TABLE         => () => SmithingTable(data)
      case Material.SMOKER                 => () => Smoker(data)
      case Material.SMOOTH_STONE           => () => null
      case Material.SNOW                   => () => Snow(data)
      case Material.SNOWBALL               => () => Snowball(data)
      case Material.SNOW_BLOCK             => () => SnowBlock(data)
      case Material.SOUL_SAND              => () => SoulSand(data)
      case Material.SPAWNER                => () => Spawner(data)
      case Material.SPIDER_EYE             => () => SpiderEye(fermented = false, data)
      case Material.SPLASH_POTION          => () => null
      case Material.SPONGE                 => () => Sponge(wet = false, data)
      case Material.STICK                  => () => Stick(data)
      case Material.STICKY_PISTON          => () => Piston(sticky = true, data)
      case Material.STONE                  => () => null
      case Material.STONECUTTER            => () => Stonecutter(data)
      case Material.STONE_BRICKS           => () => null
      case Material.STRING                 => () => PieceOfString(data)
      case Material.STRUCTURE_BLOCK        => () => null
      case Material.STRUCTURE_VOID         => () => null
      case Material.SUGAR                  => () => Sugar(data)
      case Material.SUGAR_CANE             => () => SugarCane(data)
      case Material.SUSPICIOUS_STEW        => () => null
      case Material.SWEET_BERRIES          => () => SweetBerries(data)
      case Material.TALL_GRASS             => () => Grass(tall = true, data)
      case Material.TNT                    => () => TNT(data)
      case Material.TORCH                  => () => Torch(data)
      case Material.TOTEM_OF_UNDYING       => () => TotemOfUndying(data)
      case Material.TRIDENT                => () => Trident(data)
      case Material.TRIPWIRE_HOOK          => () => TripwireHook(data)
      case Material.TROPICAL_FISH          => () => TropicalFish(data)
      case Material.TURTLE_EGG             => () => TurtleEgg(data)
      case Material.TURTLE_HELMET          => () => TurtleHelmet(data)
      case Material.VINE                   => () => Vine(data)
      case Material.WET_SPONGE             => () => Sponge(wet = true, data)
      case Material.WHEAT                  => () => Wheat(data)
      case Material.WHEAT_SEEDS            => () => null
      case Material.WRITABLE_BOOK          => () => null
      case Material.WRITTEN_BOOK           => () => null

      // FLOWER
      case Material.ALLIUM | Material.AZURE_BLUET | Material.BLUE_ORCHID |
           Material.CORNFLOWER | Material.DANDELION | Material.LILY_OF_THE_VALLEY |
           Material.ORANGE_TULIP | Material.OXEYE_DAISY | Material.PINK_TULIP |
           Material.POPPY | Material.RED_TULIP | Material.WHITE_TULIP |
           Material.WITHER_ROSE => () =>
        Flower(v[FlowerVariant], data)

      // PLANT TODO split these up into 4 items?
      case Material.LILAC | Material.PEONY | Material.ROSE_BUSH |
           Material.SUNFLOWER => () =>
        Plant(v[PlantVariant], data)

      // QUARTZ_BLOCK
      case Material.QUARTZ_BLOCK | Material.CHISELED_QUARTZ_BLOCK |
           Material.SMOOTH_QUARTZ => () =>
        QuartzBlock(v[QuartzVariant], data)

      case _ => item.getType.name match {

        // matched first to avoid name overlap when sorted alphabetically
        case r".*PICKAXE"      => () => Pickaxe(m[ToolMaterial], data)
        case r"STRIPPED.*LOG"  => () => Log(m[WoodMaterial], stripped = true, data)
        case r"STRIPPED.*WOOD" => () => Wood(m[WoodMaterial], stripped = true, data)

        case r".*ANDESITE"          => () => Andesite(v[StoniteVariant], data)
        case r".*ANVIL"             => () => Anvil(v[AnvilVariant], data)
        case r".*ARROW"             => () => Arrow(v[ArrowVariant], data)
        case r".*AXE"               => () => Axe(m[ToolMaterial], data)
        case r".*BANNER"            => () => Banner(color, data)
        case r".*BANNER_PATTERN"    => () => BannerPattern(v[BannerPatternVariant], data)
        case r".*BED"               => () => Bed(color, data)
        case r".*BOAT"              => () => Boat(m[WoodMaterial], data)
        case r".*BOOTS"             => () => Boots(m[ArmorMaterial], data)
        case r".*BRICK"             => () => Brick(m[BrickMaterial], data)
        case r".*BUCKET"            => () => Bucket(v[BucketVariant], data)
        case r".*BUTTON"            => () => Button(m[ButtonMaterial], data)
        case r".*CARPET"            => () => Carpet(color, data)
        case r".*CHEST"             => () => Chest(v[ChestVariant], data)
        case r".*CHESTPLATE"        => () => Chestplate(m[ArmorMaterial], data)
        case r".*COMMAND_BLOCK"     => () => CommandBlock(v[CommandBlockVariant], data)
        case r".*CONCRETE"          => () => Concrete(color, data)
        case r".*CONCRETE_POWDER"   => () => ConcretePowder(color, data)
        case r".*CORAL"             => () => Coral(v[CoralVariant], data)
        case r".*CORAL_BLOCK"       => () => CoralBlock(v[CoralVariant], data)
        case r".*CORAL_FAN"         => () => CoralFan(v[CoralVariant], data)
        case r".*DIORITE"           => () => Diorite(v[StoniteVariant], data)
        case r".*DOOR"              => () => Door(m[DoorMaterial], data)
        case r".*DYE"               => () => Dye(color, data)
        case r"END_STONE.*"         => () => EndStone(m[EndStoneMaterial], data)
        case r".*FENCE"             => () => Fence(m[FenceMaterial], data)
        case r".*GATE"              => () => Gate(m[WoodMaterial], data)
        case r".*GLASS"             => () => Glass(Some(color), data)
        case r".*GLASS_PANE"        => () => GlassPane(Some(color), data)
        case r".*GLAZED_TERRACOTTA" => () => GlazedTerracotta(color, data)
        // TODO merge GlazedTerracotta and Terracotta like Log and Wood with stripped?
        case r".*GRANITE" => () => Granite(v[StoniteVariant], data)
        // TODO or split Log and Wood into StrippedLog and StrippedWood instead?
        case r".*HEAD"           => () => MobHead(v[MobHeadVariant], data)
        case r".*HELMET"         => () => Helmet(m[ArmorMaterial], data)
        case r".*HOE"            => () => Hoe(m[ToolMaterial], data)
        case r".*HORSE_ARMOR"    => () => HorseArmor(v[HorseArmorVariant], data)
        case r".*ICE"            => () => Ice(v[IceVariant], data)
        case r".*LEAVES"         => () => Leaves(m[WoodMaterial], data)
        case r".*LEGGINGS"       => () => Leggings(m[ArmorMaterial], data)
        case r".*LOG"            => () => Log(m[WoodMaterial], stripped = false, data)
        case r".*MINECART"       => () => Minecart(v[MinecartVariant], data)
        case r".*MUSHROOM"       => () => Mushroom(v[MushroomVariant], data)
        case r".*MUSHROOM_BLOCK" => () => MushroomBlock(v[MushroomBlockVariant], data)
        case r"MUSIC_DISC.*"     => () => MusicDisc(v[MusicDiscVariant], data)
        case r".*PILLAR"         => () => Pillar(m[PillarMaterial], data)
        case r".*PLANKS"         => () => Planks(m[WoodMaterial], data)
        case r".*PRESSURE_PLATE" => () => PressurePlate(m[PressurePlateMaterial], data)
        case r".*RAIL"           => () => Rails(v[RailsVariant], data) // TODO rename Rail?
        case r".*SAND"           => () => Sand(m[SandMaterial], data)
        case r".*SANDSTONE"      => () => Sandstone(m[SandstoneMaterial], v[SandstoneVariant], data)
        case r".*SAPLING"        => () => Sapling(v[SaplingVariant], data)
        // TODO SaplingVariant.BAMBOO is illegal on an item, make separate SaplingVariant for items?
        case r".*SHOVEL"      => () => Shovel(m[ToolMaterial], data)
        case r".*SHULKER_BOX" => () => ShulkerBox(Some(color), data)
        case r".*SIGN"        => () => Sign(m[WoodMaterial], data)
        case r".*SKULL"       => () => MobHead(v[MobHeadVariant], data)
        case r".*SLAB"        => () => Slab(m[SlabMaterial], Some(v[SlabVariant]), data)
        case r".*SPAWN_EGG"   => () => SpawnEgg(v[SpawnEggVariant], data)
        case r".*STAIRS"      => () => Stairs(m[StairsMaterial], Some(v[StairsVariant]), data)
        case r".*SWORD"       => () => Sword(m[ToolMaterial], data)
        case r".*TERRACOTTA"  => () => Terracotta(Some(color), data)
        case r".*TRAPDOOR"    => () => TrapDoor(m[TrapdoorMaterial], data)
        case r".*WALL"        => () => Wall(m[WallMaterial], Some(v[WallVariant]), data)
        case r".*WOOD"        => () => Wood(m[WoodMaterial], stripped = false, data)
        case r".*WOOL"        => () => Wool(color, data)

        case r"INFESTED.*" => () =>
          InfestedBlock(m[InfestedMaterial], Some(v[InfestedVariant]), data)

        case r".*WEIGHTED_PRESSURE_PLATE" => () =>
          WeightedPressurePlate(v[WeightedPressurePlateVariant], data)

        case _ => throw new IllegalArgumentException(s"${item.getType}")
      }
    })
  }

  def map(item: Item): SpigotItemStack = {
    val spigotItem: SpigotItemStack = item match {
      case _ => null
    }

    item match {case StackableItem =>}
    spigotItem.setAmount(item.size)
    spigotItem
  }
}
