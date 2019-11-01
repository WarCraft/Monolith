package gg.warcraft.monolith.spigot.world.item

import gg.warcraft.monolith.api.core.Extensions.Regex
import gg.warcraft.monolith.api.world.block.variant._
import gg.warcraft.monolith.api.world.item._
import gg.warcraft.monolith.api.world.item.variant._
import javax.inject.Inject
import org.bukkit.Material
import org.bukkit.inventory.ItemFlag

class SpigotItemMapper @Inject() (
    private val colorMapper: SpigotItemColorMapper,
    private val materialMapper: SpigotItemMaterialMapper,
    private val variantMapper: SpigotItemVariantMapper
) {
  def map(item: SpigotItemStack): Option[Item] = {
    if (item.getType.name.endsWith("AIR")) return None
    val meta = item.getItemMeta

    val name = item.getItemMeta.getDisplayName
    val tooltip: Array[String] = Array() // item.getItemMeta.getLore
    val count = item.getAmount
    val attr = Set.empty[String]
    val hideAttr = item.getItemMeta.hasItemFlag(ItemFlag.HIDE_ATTRIBUTES)

    lazy val color = colorMapper.map(item.getType)
    lazy val durability = item.getDurability.toInt // TODO keep int or let be short?
    lazy val unbreakable = meta.isUnbreakable
    lazy val hideUnbreakable = meta.hasItemFlag(ItemFlag.HIDE_UNBREAKABLE)
    lazy val hideEffects = meta.hasItemFlag(ItemFlag.HIDE_POTION_EFFECTS)
    lazy val variant = variantMapper.map(item)

    lazy val params = (name, tooltip, count, attr, hideAttr)
    lazy val singletonParams = (name, tooltip, attr, hideAttr)
    lazy val durableParams =
      (name, tooltip, attr, hideAttr, durability, unbreakable, hideUnbreakable)

    Some(item.getType match {
      case Material.APPLE                  => Apple.tupled(params)
      case Material.ARMOR_STAND            => ArmorStand.tupled(params)
      case Material.BAMBOO                 => Bamboo.tupled(params)
      case Material.BARREL                 => Barrel.tupled(params)
      case Material.BARRIER                => Barrier.tupled(params)
      case Material.BEACON                 => Beacon.tupled(params)
      case Material.BEDROCK                => Bedrock.tupled(params)
      case Material.BEETROOT               => Beetroot.tupled(params)
      case Material.BELL                   => Bell.tupled(params)
      case Material.BLAST_FURNACE          => BlastFurnace.tupled(params)
      case Material.BLAZE_POWDER           => BlazeRod.tupled(params)
      case Material.BLAZE_ROD              => BlazeRod.tupled(params)
      case Material.BONE                   => Bone.tupled(params)
      case Material.BONE_BLOCK             => BoneBlock.tupled(params)
      case Material.BONE_MEAL              => BoneMeal.tupled(params)
      case Material.BOOK                   => Book.tupled(params)
      case Material.BOOKSHELF              => Bookshelf.tupled(params)
      case Material.BOW                    => Bow.tupled(durableParams)
      case Material.BOWL                   => Bowl.tupled(params)
      case Material.BREAD                  => Bread.tupled(params)
      case Material.BREWING_STAND          => BrewingStand.tupled(params)
      case Material.CACTUS                 => Cactus.tupled(params)
      case Material.CAKE                   => Cake.tupled(singletonParams)
      case Material.CAMPFIRE               => Campfire.tupled(params)
      case Material.CARROT                 => Carrot.tupled(params)
      case Material.CARROT_ON_A_STICK      => CarrotOnAStick.tupled(durableParams)
      case Material.CARTOGRAPHY_TABLE      => CartographyTable.tupled(params)
      case Material.CAULDRON               => Cauldron.tupled(params)
      case Material.CHARCOAL               => Charcoal.tupled(params)
      case Material.CHORUS_FLOWER          => ChorusFlower.tupled(params)
      case Material.CHORUS_PLANT           => ChorusPlant.tupled(params)
      case Material.CLAY                   => ClayBlock.tupled(params)
      case Material.CLAY_BALL              => Clay.tupled(params)
      case Material.CLOCK                  => Clock.tupled(params)
      case Material.COAL                   => Coal.tupled(params)
      case Material.COAL_BLOCK             => CoalBlock.tupled(params)
      case Material.COAL_ORE               => CoalOre.tupled(params)
      case Material.COBWEB                 => Cobweb.tupled(params)
      case Material.COCOA_BEANS            => CocoaBeans.tupled(params)
      case Material.COMPARATOR             => Comparator.tupled(params)
      case Material.COMPASS                => Compass.tupled(params)
      case Material.COMPOSTER              => Composter.tupled(params)
      case Material.CONDUIT                => Conduit.tupled(params)
      case Material.COOKIE                 => Cookie.tupled(params)
      case Material.CRAFTING_TABLE         => CraftingTable.tupled(params)
      case Material.CROSSBOW               => Crossbow.tupled(durableParams)
      case Material.DAYLIGHT_DETECTOR      => DaylightDetector.tupled(params)
      case Material.DEAD_BUSH              => DeadBush.tupled(params)
      case Material.DEBUG_STICK            => DebugStick.tupled(singletonParams)
      case Material.DIAMOND                => Diamond.tupled(params)
      case Material.DIAMOND_BLOCK          => DiamondBlock.tupled(params)
      case Material.DIAMOND_ORE            => DiamondOre.tupled(params)
      case Material.DISPENSER              => Dispenser.tupled(params)
      case Material.DRAGON_BREATH          => DragonBreath.tupled(params)
      case Material.DRAGON_EGG             => DragonEgg.tupled(params)
      case Material.DRIED_KELP             => DriedKelp.tupled(params)
      case Material.DRIED_KELP_BLOCK       => DriedKelpBlock.tupled(params)
      case Material.DROPPER                => Dropper.tupled(params)
      case Material.EGG                    => Egg.tupled(params)
      case Material.ELYTRA                 => Elytra.tupled(durableParams)
      case Material.EMERALD                => Emerald.tupled(params)
      case Material.EMERALD_BLOCK          => EmeraldBlock.tupled(params)
      case Material.EMERALD_ORE            => EmeraldOre.tupled(params)
      case Material.ENCHANTED_BOOK         => EnchantedBook.tupled(singletonParams)
      case Material.ENCHANTING_TABLE       => EnchantingTable.tupled(params)
      case Material.ENDER_EYE              => EnderEye.tupled(params)
      case Material.ENDER_PEARL            => EnderPearl.tupled(params)
      case Material.END_CRYSTAL            => EndCrystal.tupled(params)
      case Material.END_PORTAL_FRAME       => EndPortalFrame.tupled(params)
      case Material.END_ROD                => EndRod.tupled(params)
      case Material.EXPERIENCE_BOTTLE      => BottleOfEnchanting.tupled(params)
      case Material.FARMLAND               => Farmland.tupled(params)
      case Material.FEATHER                => Feather.tupled(params)
      case Material.FISHING_ROD            => FishingRod.tupled(durableParams)
      case Material.FIREWORK_ROCKET        => FireworkRocket.tupled(params)
      case Material.FIREWORK_STAR          => FireworkStar(color, params)
      case Material.FIRE_CHARGE            => FireCharge.tupled(params)
      case Material.FLETCHING_TABLE        => FletchingTable.tupled(params)
      case Material.FLINT                  => Flint.tupled(params)
      case Material.FLINT_AND_STEEL        => FlintAndSteel.tupled(durableParams)
      case Material.FLOWER_POT             => FlowerPot.tupled(params)
      case Material.FURNACE                => Furnace.tupled(params)
      case Material.GHAST_TEAR             => GhastTear.tupled(params)
      case Material.GLASS_BOTTLE           => GlassBottle.tupled(params)
      case Material.GLISTERING_MELON_SLICE => GoldenMelonSlice.tupled(params)
      case Material.GLOWSTONE              => Glowstone.tupled(params)
      case Material.GLOWSTONE_DUST         => GlowstoneDust.tupled(params)
      case Material.GOLDEN_CARROT          => GoldenCarrot.tupled(params)
      case Material.GOLD_BLOCK             => GoldBlock.tupled(params)
      case Material.GOLD_INGOT             => GoldIngot.tupled(params)
      case Material.GOLD_NUGGET            => GoldNugget.tupled(params)
      case Material.GOLD_ORE               => GoldOre.tupled(params)
      case Material.GRASS_BLOCK            => GrassBlock.tupled(params)
      case Material.GRASS_PATH             => GrassPath.tupled(params)
      case Material.GRAVEL                 => Gravel.tupled(params)
      case Material.GRINDSTONE             => Grindstone.tupled(params)
      case Material.GUNPOWDER              => Gunpowder.tupled(params)
      case Material.HAY_BLOCK              => HayBale.tupled(params)
      case Material.HEART_OF_THE_SEA       => HeartOfTheSea.tupled(params)
      case Material.HOPPER                 => Hopper.tupled(params)
      case Material.INK_SAC                => InkSac.tupled(params)
      case Material.IRON_BARS              => IronBars.tupled(params)
      case Material.IRON_BLOCK             => IronBlock.tupled(params)
      case Material.IRON_INGOT             => IronIngot.tupled(params)
      case Material.IRON_NUGGET            => IronNugget.tupled(params)
      case Material.IRON_ORE               => IronOre.tupled(params)
      case Material.ITEM_FRAME             => ItemFrame.tupled(params)
      case Material.JACK_O_LANTERN         => JackOfTheLantern.tupled(params)
      case Material.JIGSAW                 => JigsawBlock.tupled(params)
      case Material.JUKEBOX                => Jukebox.tupled(params)
      case Material.KELP                   => Kelp.tupled(params)
      case Material.KNOWLEDGE_BOOK         => KnowledgeBook.tupled(singletonParams)
      case Material.LADDER                 => Ladder.tupled(params)
      case Material.LANTERN                => Lantern.tupled(params)
      case Material.LAPIS_BLOCK            => LapisBlock.tupled(params)
      case Material.LAPIS_LAZULI           => Lapis.tupled(params)
      case Material.LAPIS_ORE              => LapisOre.tupled(params)
      case Material.LEAD                   => Lead.tupled(params)
      case Material.LEATHER                => Leather.tupled(params)
      case Material.LECTERN                => Lectern.tupled(params)
      case Material.LEVER                  => Lever.tupled(params)
      case Material.LILY_PAD               => LilyPad.tupled(params)
      case Material.LOOM                   => Loom.tupled(params)
      case Material.MAGMA_BLOCK            => MagmaBlock.tupled(params)
      case Material.MAGMA_CREAM            => MagmaCream.tupled(params)
      case Material.MELON                  => Melon.tupled(params)
      case Material.MELON_SLICE            => MelonSlice.tupled(params)
      case Material.MYCELIUM               => Mycelium.tupled(params)
      case Material.NAME_TAG               => NameTag.tupled(params)
      case Material.NAUTILUS_SHELL         => NautilusShell.tupled(params)
      case Material.NETHERRACK             => Netherrack.tupled(params)
      case Material.NETHER_QUARTZ_ORE      => QuartzOre.tupled(params)
      case Material.NETHER_STAR            => NetherStar.tupled(params)
      case Material.NETHER_WART            => NetherWart.tupled(params)
      case Material.NETHER_WART_BLOCK      => NetherWartBlock.tupled(params)
      case Material.NOTE_BLOCK             => NoteBlock.tupled(params)
      case Material.OBSERVER               => Observer.tupled(params)
      case Material.OBSIDIAN               => Obsidian.tupled(params)
      case Material.PAINTING               => Painting.tupled(params)
      case Material.PAPER                  => Paper.tupled(params)
      case Material.PHANTOM_MEMBRANE       => PhantomMembrane.tupled(params)
      case Material.PODZOL                 => Podzol.tupled(params)
      case Material.POISONOUS_POTATO       => PoisonousPotato.tupled(params)
      case Material.PRISMARINE_CRYSTALS    => PrismarineCrystals.tupled(params)
      case Material.PRISMARINE_SHARD       => PrismarineShard.tupled(params)
      case Material.PUFFERFISH             => Pufferfish.tupled(params)
      case Material.PUMPKIN_PIE            => PumpkinPie.tupled(params)
      case Material.PURPUR_BLOCK           => PurpurBlock.tupled(params)
      case Material.QUARTZ                 => Quartz.tupled(params)
      case Material.RABBIT_FOOT            => RabbitFoot.tupled(params)
      case Material.RABBIT_HIDE            => RabbitHide.tupled(params)
      case Material.REDSTONE               => Redstone.tupled(params)
      case Material.REDSTONE_BLOCK         => RedstoneBlock.tupled(params)
      case Material.REDSTONE_LAMP          => RedstoneLamp.tupled(params)
      case Material.REDSTONE_ORE           => RedstoneOre.tupled(params)
      case Material.REDSTONE_TORCH         => RedstoneTorch.tupled(params)
      case Material.REPEATER               => Repeater.tupled(params)
      case Material.ROTTEN_FLESH           => RottenFlesh.tupled(params)
      case Material.SADDLE                 => Saddle.tupled(singletonParams)
      case Material.SCAFFOLDING            => Scaffolding.tupled(params)
      case Material.SCUTE                  => Scute.tupled(params)
      case Material.SEAGRASS               => Seagrass.tupled(params)
      case Material.SEA_LANTERN            => SeaLantern.tupled(params)
      case Material.SEA_PICKLE             => SeaPickle.tupled(params)
      case Material.SHEARS                 => Shears.tupled(durableParams)
      case Material.SHIELD                 => Shield.tupled(durableParams)
      case Material.SHULKER_SHELL          => ShulkerShell.tupled(params)
      case Material.SLIME_BALL             => Slimeball.tupled(params)
      case Material.SLIME_BLOCK            => SlimeBlock.tupled(params)
      case Material.SMITHING_TABLE         => SmithingTable.tupled(params)
      case Material.SMOKER                 => Smoker.tupled(params)
      case Material.SNOW                   => Snow.tupled(params)
      case Material.SNOWBALL               => Snowball.tupled(params)
      case Material.SNOW_BLOCK             => SnowBlock.tupled(params)
      case Material.SOUL_SAND              => SoulSand.tupled(params)
      case Material.SPAWNER                => Spawner.tupled(params)
      case Material.STICK                  => Stick.tupled(params)
      case Material.STONECUTTER            => Stonecutter.tupled(params)
      case Material.STRING                 => PieceOfString.tupled(params)
      case Material.SUGAR                  => Sugar.tupled(params)
      case Material.SUGAR_CANE             => SugarCane.tupled(params)
      case Material.SWEET_BERRIES          => SweetBerries.tupled(params)
      case Material.TNT                    => TNT.tupled(params)
      case Material.TORCH                  => Torch.tupled(params)
      case Material.TOTEM_OF_UNDYING       => TotemOfUndying.tupled(singletonParams)
      case Material.TRIDENT                => Trident.tupled(durableParams)
      case Material.TRIPWIRE_HOOK          => TripwireHook.tupled(params)
      case Material.TROPICAL_FISH          => TropicalFish.tupled(params)
      case Material.TURTLE_EGG             => TurtleEgg.tupled(params)
      case Material.TURTLE_HELMET          => TurtleHelmet.tupled(durableParams)
      case Material.VINE                   => Vine.tupled(params)
      case Material.WHEAT                  => Wheat.tupled(params)
      case Material.WRITABLE_BOOK          => BookAndQuill.tupled(singletonParams)
      case Material.WRITTEN_BOOK           => WrittenBook.tupled(params)

      // format: off
      case Material.PRISMARINE => Prismarine(variantAs[PrismarineVariant], params)
      case Material.PRISMARINE_BRICKS => Prismarine(variantAs[PrismarineVariant], params)
      case Material.DARK_PRISMARINE => Prismarine(variantAs[PrismarineVariant], params)
        
      case Material.MUTTON  => Mutton(cooked = false, params)

      case Material.BAKED_POTATO => Potato(cooked = true, params)
      case Material.BEEF => Beef(cooked = false, params)
      case Material.BRICKS => BrickBlock(variantAs[BrickVariant], params)
      case Material.CARVED_PUMPKIN => Pumpkin(carved = true, params)
      case Material.CHICKEN => Chicken(cooked = false, params)
      case Material.CHORUS_FRUIT => ChorusFruit(popped = false, params)
      case Material.COARSE_DIRT => Dirt(coarse = true, params)
      case Material.COD => Cod(cooked = false, params)
      case Material.COOKED_BEEF => Beef(cooked = true, params)
      case Material.COOKED_CHICKEN => Chicken(cooked = true, params)
      case Material.COOKED_COD => Cod(cooked = true, params)
      case Material.COOKED_MUTTON => Mutton(cooked = true, params)
      case Material.COOKED_PORKCHOP => Porkchop(cooked = true, params)
      case Material.COOKED_RABBIT => Rabbit(cooked = true, params)
      case Material.COOKED_SALMON => Salmon(cooked = true, params)
      case Material.DIRT => Dirt(coarse = false, params)
      case Material.ENCHANTED_GOLDEN_APPLE => GoldenApple(enchanted = true, params)
      case Material.FERMENTED_SPIDER_EYE => SpiderEye(fermented = true, params)
      case Material.FERN       => Fern(tall = false, params)
      case Material.FILLED_MAP => Map(filled = true, params)
      case Material.GOLDEN_APPLE => GoldenApple(enchanted = false, params)
      case Material.GRASS      => Grass(tall = false, params)
      case Material.LARGE_FERN => Fern(tall = true, params)
      case Material.MAP        => Map(filled = false, params)
      case Material.NETHER_BRICKS => BrickBlock(variantAs[BrickVariant], params)
      case Material.PISTON => Piston(sticky = false, params)
      case Material.POPPED_CHORUS_FRUIT => ChorusFruit(popped = true, params)
      case Material.PORKCHOP => Porkchop(cooked = false, params)
      case Material.POTATO => Potato(cooked = false, params)
      case Material.PUMPKIN => Pumpkin(carved = false, params)
      case Material.RABBIT => Rabbit(cooked = false, params)
      case Material.RED_NETHER_BRICKS => BrickBlock(variantAs[BrickVariant], params)
      case Material.SALMON => Salmon(cooked = false, params)
      case Material.SPIDER_EYE => SpiderEye(fermented = false, params)
      case Material.SPONGE => Sponge(wet = false, params)
      case Material.STICKY_PISTON => Piston(sticky = true, params)
      case Material.TALL_GRASS => Grass(tall = true, params)
      case Material.WET_SPONGE => Sponge(wet = true, params)

      // FLOWER
      case Material.ALLIUM | AZURE_BLUET | BLUE_ORCHID | CORNFLOWER | DANDELION |
          LILY_OF_THE_VALLEY | ORANGE_TULIP | OXEYE_DAISY | PINK_TULIP | POPPY |
          RED_TULIP | WHITE_TULIP | WITHER_ROSE =>
        Flower(variantAs[FlowerVariant], params)

      // PLANT TODO split these up into 4 items?
      case Material.LILAC | PEONY | ROSE_BUSH | SUNFLOWER =>
        Plant(variantAs[PlantVariant], params)

      // QUARTZ_BLOCK
      case Material.QUARTZ_BLOCK | CHISELED_QUARTZ_BLOCK | SMOOTH_QUARTZ =>
        QuartzBlock(variantAs[QuartzBlockVariant], params)

      case _ =>
        item.getType.name match {
          // matched first to avoid name overlap when sorted alphabetically
          case r".*PICKAXE" =>
            Pickaxe(variantAs[ToolVariant], params)
          case r"STRIPPED.*LOG" =>
            Log(
              variantAs[WoodVariant],
              stripped = true,
              name,
              tooltip,
              count,
              attr,
              hideAttr
            )
          case r"STRIPPED.*WOOD" =>
            Wood(
              variantAs[WoodVariant],
              stripped = true,
              name,
              tooltip,
              count,
              attr,
              hideAttr
            )

          case r".*SANDSTONE" =>
            Sandstone(
              variantAs[SandstoneVariant],
              variantAs[SandstoneVariant],
              name,
              tooltip,
              count,
              attr,
              hideAttr
            )

          // TODO SaplingVariant.BAMBOO is illegal on an item, make separate SaplingVariant for items?
          // TODO merge GlazedTerracotta and Terracotta like Log and Wood with stripped?
          // TODO or split Log and Wood into StrippedLog and StrippedWood instead?
          case r".*ANDESITE" =>
            Andesite(variantAs[StoniteVariant], params)
          case r".*ANVIL" =>
            Anvil(variantAs[AnvilVariant], params)
          case r".*ARROW" =>
            Arrow(variantAs[ArrowVariant], params)
          case r".*AXE" =>
            Axe(variantAs[ToolVariant], params)
          case r".*BANNER" => Banner(color, params)
          case r".*BANNER_PATTERN" =>
            BannerPattern(variantAs[BannerPatternVariant], name, tooltip, attr, hideAttr)
          case r".*BED" => Bed(color, params)
          case r".*BOAT" =>
            Boat(variantAs[WoodVariant], params)
          case r".*BOOTS" =>
            Boots(variantAs[ArmorVariant], params)
          case r".*BRICK" =>
            Brick(variantAs[BrickVariant], params)
          case r".*BUCKET" =>
            Bucket(variantAs[BucketVariant], params)
          case r".*BUTTON" =>
            Button(variantAs[ButtonVariant], params)
          case r".*CARPET" => Carpet(color, params)
          case r".*CHEST" =>
            Chest(variantAs[ChestVariant], params)
          case r".*CHESTPLATE" =>
            Chestplate(variantAs[ArmorVariant], params)
          case r".*COBBLESTONE" =>
            Cobblestone(
              variantAs[CobblestoneVariant],
              name,
              tooltip,
              count,
              attr,
              hideAttr
            )
          case r".*COMMAND_BLOCK" =>
            CommandBlock(
              variantAs[CommandBlockVariant],
              name,
              tooltip,
              count,
              attr,
              hideAttr
            )
          case r".*CONCRETE" => Concrete(color, params)
          case r".*CONCRETE_POWDER" =>
            ConcretePowder(color, params)
          case r".*CORAL" =>
            Coral(variantAs[CoralVariant], params)
          case r".*CORAL_BLOCK" =>
            CoralBlock(variantAs[CoralVariant], params)
          case r".*CORAL_FAN" =>
            CoralFan(variantAs[CoralVariant], params)
          case r".*DIORITE" =>
            Diorite(variantAs[StoniteVariant], params)
          case r".*DOOR" =>
            Door(variantAs[DoorVariant], params)
          case r".*DYE" => Dye(color, params)
          case r"END_STONE.*" =>
            EndStone(variantAs[EndStoneVariant], params)
          case r".*FENCE" =>
            Fence(variantAs[FenceVariant], params)
          case r".*GATE" =>
            Gate(variantAs[WoodVariant], params)
          case r".*GLASS" => Glass(Some(color), params)
          case r".*GLASS_PANE" =>
            GlassPane(Some(color), params)
          case r".*GLAZED_TERRACOTTA" =>
            GlazedTerracotta(color, params)
          case r".*GRANITE" =>
            Granite(variantAs[StoniteVariant], params)
          case r".*HEAD" =>
            MobHead(variantAs[MobHeadVariant], params)
          case r".*HELMET" =>
            Helmet(variantAs[ArmorVariant], params)
          case r".*HOE" =>
            Hoe(variantAs[ToolVariant], params)
          case r".*HORSE_ARMOR" =>
            HorseArmor(variantAs[HorseArmorVariant], params)
          case r".*ICE" => Ice(variantAs[IceVariant], params)
          case r".*LEAVES" =>
            Leaves(variantAs[WoodVariant], params)
          case r".*LEGGINGS" =>
            Leggings(variantAs[ArmorVariant], params)
          case r".*LOG" =>
            Log(
              variantAs[WoodVariant],
              stripped = false,
              name,
              tooltip,
              count,
              attr,
              hideAttr
            )
          case r".*MINECART" =>
            Minecart(variantAs[MinecartVariant], params)
          case r".*MUSHROOM" =>
            Mushroom(variantAs[MushroomVariant], params)
          case r".*MUSHROOM_BLOCK" =>
            MushroomBlock(
              variantAs[MushroomBlockVariant],
              name,
              tooltip,
              count,
              attr,
              hideAttr
            )
          case r"MUSIC_DISC.*" =>
            MusicDisc(variantAs[MusicDiscVariant], params)
          case r".*PILLAR" =>
            Pillar(variantAs[PillarVariant], params)
          case r".*PLANKS" =>
            Planks(variantAs[WoodVariant], params)
          case r".*POTION" =>
            Potion(variantAs[PotionVariant], hideEffects, name, tooltip, attr, hideAttr)
          case r".*PRESSURE_PLATE" =>
            PressurePlate(
              variantAs[PressurePlateVariant],
              name,
              tooltip,
              count,
              attr,
              hideAttr
            )
          case r".*RAIL" =>
            Rail(variantAs[RailVariant], params) // TODO rename
          case r".*SAND" =>
            Sand(variantAs[SandVariant], params)
          case r".*SAPLING" =>
            Sapling(variantAs[SaplingVariant], params)
          case r".*SEEDS" =>
            Seeds(variantAs[SeedsVariant], params)
          case r".*SHOVEL" =>
            Shovel(variantAs[ToolVariant], params)
          case r".*SHULKER_BOX" =>
            ShulkerBox(Some(color), params)
          case r".*SIGN" =>
            Sign(variantAs[WoodVariant], params)
          case r".*SKULL" =>
            MobHead(variantAs[MobHeadVariant], params)
          case r".*SLAB" =>
            Slab(
              variantAs[SlabVariant],
              Some(variantAs[SlabVariant]),
              name,
              tooltip,
              count,
              attr,
              hideAttr
            )
          case r".*SPAWN_EGG" =>
            SpawnEgg(variantAs[SpawnEggVariant], params)
          case r".*STEW" =>
            Stew(variantAs[StewVariant], params)
          case r".*STONE" =>
            Stone(
              variantAs[StoneVariant],
              variantAs[StoneVariant],
              name,
              tooltip,
              count,
              attr,
              hideAttr
            )
          case r"STRUCTURE.*" =>
            StructureBlock(
              variantAs[StructureBlockVariant],
              name,
              tooltip,
              count,
              attr,
              hideAttr
            )
          case r".*SWORD" =>
            Sword(variantAs[ToolVariant], params)
          case r".*TERRACOTTA" =>
            Terracotta(Some(color), params)
          case r".*TRAPDOOR" =>
            Trapdoor(variantAs[TrapdoorVariant], params)
          case r".*WALL" =>
            Wall(
              variantAs[WallVariant],
              Some(variantAs[WallVariant]),
              name,
              tooltip,
              count,
              attr,
              hideAttr
            )
          case r".*WOOD" =>
            Wood(
              variantAs[WoodVariant],
              stripped = false,
              name,
              tooltip,
              count,
              attr,
              hideAttr
            )
          case r".*WOOL" => Wool(color, params)

          case r"INFESTED.*" =>
            InfestedBlock(
              variantAs[InfestedVariant],
              Some(variantAs[InfestedVariant]),
              name,
              tooltip,
              count,
              attr,
              hideAttr
            )

          case r".*STAIRS" =>
            Stairs(
              variantAs[StairsVariant],
              Some(variantAs[StairsVariant]),
              name,
              tooltip,
              count,
              attr,
              hideAttr
            )

          case r".*WEIGHTED_PRESSURE_PLATE" =>
            WeightedPressurePlate(
              variantAs[WeightedPressurePlateVariant],
              name,
              tooltip,
              count,
              attr,
              hideAttr
            )
        }
    })
  }

  def map(item: Item): SpigotItemStack = {
    val spigotItem: SpigotItemStack = item match {
      case _ => null
    }

    // TODO map skull meta

    spigotItem
  }
}
