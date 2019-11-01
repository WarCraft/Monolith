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
    lazy val colorParams = (color, name, tooltip, count, attr, hideAttr)
    lazy val singleParams = (name, tooltip, attr, hideAttr)
    lazy val durableParams =
      (name, tooltip, attr, hideAttr, durability, unbreakable, hideUnbreakable)

    def variantParams[T <: ItemVariant] =
      (variant.asInstanceOf[T], name, tooltip, count, attr, hideAttr)

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
      case Material.CAKE                   => Cake.tupled(singleParams)
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
      case Material.DEBUG_STICK            => DebugStick.tupled(singleParams)
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
      case Material.ENCHANTED_BOOK         => EnchantedBook.tupled(singleParams)
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
      case Material.FIREWORK_STAR          => FireworkStar.tupled(colorParams)
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
      case Material.KNOWLEDGE_BOOK         => KnowledgeBook.tupled(singleParams)
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
      case Material.SADDLE                 => Saddle.tupled(singleParams)
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
      case Material.TOTEM_OF_UNDYING       => TotemOfUndying.tupled(singleParams)
      case Material.TRIDENT                => Trident.tupled(durableParams)
      case Material.TRIPWIRE_HOOK          => TripwireHook.tupled(params)
      case Material.TROPICAL_FISH          => TropicalFish.tupled(params)
      case Material.TURTLE_EGG             => TurtleEgg.tupled(params)
      case Material.TURTLE_HELMET          => TurtleHelmet.tupled(durableParams)
      case Material.VINE                   => Vine.tupled(params)
      case Material.WHEAT                  => Wheat.tupled(params)
      case Material.WRITABLE_BOOK          => BookAndQuill.tupled(singleParams)
      case Material.WRITTEN_BOOK           => WrittenBook.tupled(params)

      // BRICK
      case Material.BRICK | Material.NETHER_BRICK =>
        Brick.tupled(variantParams[BrickVariant])

      // BRICK_BLOCK
      case Material.BRICKS | Material.NETHER_BRICKS | Material.RED_NETHER_BRICKS =>
        BrickBlock.tupled(variantParams[BrickBlockVariant])

      // FLOWER
      case Material.ALLIUM | Material.AZURE_BLUET | Material.BLUE_ORCHID |
          Material.CORNFLOWER | Material.DANDELION | Material.LILY_OF_THE_VALLEY |
          Material.ORANGE_TULIP | Material.OXEYE_DAISY | Material.PINK_TULIP |
          Material.POPPY | Material.RED_TULIP | Material.WHITE_TULIP |
          Material.WITHER_ROSE =>
        Flower.tupled(variantParams[FlowerVariant])

      // PLANT
      case Material.LILAC | Material.PEONY | Material.ROSE_BUSH |
          Material.SUNFLOWER =>
        Plant.tupled(variantParams[PlantVariant])

      // PRISMARINE
      case Material.PRISMARINE | Material.PRISMARINE_BRICKS |
          Material.DARK_PRISMARINE =>
        Prismarine.tupled(variantParams[PrismarineVariant])

      // QUARTZ_BLOCK
      case Material.QUARTZ_BLOCK | Material.CHISELED_QUARTZ_BLOCK |
          Material.SMOOTH_QUARTZ =>
        QuartzBlock.tupled(variantParams[QuartzBlockVariant])

        // TODO andesite etc are split in items, but merged in block, choose

        /*
        case r".*PICKAXE" =>
            Pickaxe(variantAs[ToolVariant], name, tooltip, count, attr, hideAttr)
          case r"STRIPPED.*LOG" =>
            Log(variantAs[WoodVariant], stripped = true, name, tooltip, count, attr, hideAttr)
          case r"STRIPPED.*WOOD" =>
            Wood(variantAs[WoodVariant], stripped = true, name, tooltip, count, attr, hideAttr)

          case r".*SANDSTONE" =>
            Sandstone(variantAs[SandstoneVariant], variantAs[SandstoneVariant], name, tooltip, count, attr, hideAttr)

          case r".*ANDESITE" =>
            Andesite(variantAs[StoniteVariant], name, tooltip, count, attr, hideAttr)
          case r".*ANVIL" =>
            Anvil(variantAs[AnvilVariant], name, tooltip, count, attr, hideAttr)
          case r".*ARROW" =>
            Arrow(variantAs[ArrowVariant], name, tooltip, count, attr, hideAttr)
          case r".*AXE" =>
            Axe(variantAs[ToolVariant], name, tooltip, count, attr, hideAttr)
          case r".*BANNER" => Banner(color, name, tooltip, count, attr, hideAttr)
          case r".*BANNER_PATTERN" =>
            BannerPattern(variantAs[BannerPatternVariant], name, tooltip, attr, hideAttr)
          case r".*BED" => Bed(color, name, tooltip, count, attr, hideAttr)
          case r".*BOAT" =>
            Boat(variantAs[WoodVariant], name, tooltip, count, attr, hideAttr)
          case r".*BOOTS" =>
            Boots(variantAs[ArmorVariant], name, tooltip, count, attr, hideAttr)
          case r".*BUCKET" =>
            Bucket(variantAs[BucketVariant], name, tooltip, count, attr, hideAttr)
          case r".*BUTTON" =>
            Button(variantAs[ButtonVariant], name, tooltip, count, attr, hideAttr)
          case r".*CARPET" => Carpet(color, name, tooltip, count, attr, hideAttr)
          case r".*CHEST" =>
            Chest(variantAs[ChestVariant], name, tooltip, count, attr, hideAttr)
          case r".*CHESTPLATE" =>
            Chestplate(variantAs[ArmorVariant], name, tooltip, count, attr, hideAttr)
          case r".*COBBLESTONE" =>
            Cobblestone(variantAs[CobblestoneVariant], name, tooltip, count, attr, hideAttr)
          case r".*COMMAND_BLOCK" =>
            CommandBlock(variantAs[CommandBlockVariant], name, tooltip, count, attr, hideAttr)
          case r".*CONCRETE" => Concrete(color, name, tooltip, count, attr, hideAttr)
          case r".*CONCRETE_POWDER" =>
            ConcretePowder(color, name, tooltip, count, attr, hideAttr)
          case r".*CORAL" =>
            Coral(variantAs[CoralVariant], name, tooltip, count, attr, hideAttr)
          case r".*CORAL_BLOCK" =>
            CoralBlock(variantAs[CoralVariant], name, tooltip, count, attr, hideAttr)
          case r".*CORAL_FAN" =>
            CoralFan(variantAs[CoralVariant], name, tooltip, count, attr, hideAttr)
          case r".*DIORITE" =>
            Diorite(variantAs[StoniteVariant], name, tooltip, count, attr, hideAttr)
          case r".*DOOR" =>
            Door(variantAs[DoorVariant], name, tooltip, count, attr, hideAttr)
          case r".*DYE" => Dye(color, name, tooltip, count, attr, hideAttr)
          case r"END_STONE.*" =>
            EndStone(variantAs[EndStoneVariant], name, tooltip, count, attr, hideAttr)
          case r".*FENCE" =>
            Fence(variantAs[FenceVariant], name, tooltip, count, attr, hideAttr)
          case r".*GATE" =>
            Gate(variantAs[WoodVariant], name, tooltip, count, attr, hideAttr)
          case r".*GLASS" => Glass(Some(color), name, tooltip, count, attr, hideAttr)
          case r".*GLASS_PANE" =>
            GlassPane(Some(color), name, tooltip, count, attr, hideAttr)
          case r".*GLAZED_TERRACOTTA" =>
            GlazedTerracotta(color, name, tooltip, count, attr, hideAttr)
          case r".*GRANITE" =>
            Granite(variantAs[StoniteVariant], name, tooltip, count, attr, hideAttr)
          case r".*HEAD" =>
            MobHead(variantAs[MobHeadVariant], name, tooltip, count, attr, hideAttr)
          case r".*HELMET" =>
            Helmet(variantAs[ArmorVariant], name, tooltip, count, attr, hideAttr)
          case r".*HOE" =>
            Hoe(variantAs[ToolVariant], name, tooltip, count, attr, hideAttr)
          case r".*HORSE_ARMOR" =>
            HorseArmor(variantAs[HorseArmorVariant], name, tooltip, count, attr, hideAttr)
          case r".*ICE" => Ice(variantAs[IceVariant], name, tooltip, count, attr, hideAttr)
          case r".*LEAVES" =>
            Leaves(variantAs[WoodVariant], name, tooltip, count, attr, hideAttr)
          case r".*LEGGINGS" =>
            Leggings(variantAs[ArmorVariant], name, tooltip, count, attr, hideAttr)
          case r".*LOG" =>
            Log(variantAs[WoodVariant], stripped = false, name, tooltip, count, attr, hideAttr)
          case r".*MINECART" =>
            Minecart(variantAs[MinecartVariant], name, tooltip, count, attr, hideAttr)
          case r".*MUSHROOM" =>
            Mushroom(variantAs[MushroomVariant], name, tooltip, count, attr, hideAttr)
          case r".*MUSHROOM_BLOCK" =>
            MushroomBlock(variantAs[MushroomBlockVariant], name, tooltip, count, attr, hideAttr)
          case r"MUSIC_DISC.*" =>
            MusicDisc(variantAs[MusicDiscVariant], name, tooltip, count, attr, hideAttr)
          case r".*PILLAR" =>
            Pillar(variantAs[PillarVariant], name, tooltip, count, attr, hideAttr)
          case r".*PLANKS" =>
            Planks(variantAs[WoodVariant], name, tooltip, count, attr, hideAttr)
          case r".*POTION" =>
            Potion(variantAs[PotionVariant], hideEffects, name, tooltip, attr, hideAttr)
          case r".*PRESSURE_PLATE" =>
            PressurePlate(
              variantAs[PressurePlateVariant], name, tooltip, count, attr, hideAttr)
          case r".*RAIL" =>
            Rail(variantAs[RailVariant], name, tooltip, count, attr, hideAttr)
          case r".*SAND" =>
            Sand(variantAs[SandVariant], name, tooltip, count, attr, hideAttr)
          case r".*SAPLING" =>
            Sapling(variantAs[SaplingVariant], name, tooltip, count, attr, hideAttr)
          case r".*SEEDS" =>
            Seeds(variantAs[SeedsVariant], name, tooltip, count, attr, hideAttr)
          case r".*SHOVEL" =>
            Shovel(variantAs[ToolVariant], name, tooltip, count, attr, hideAttr)
          case r".*SHULKER_BOX" =>
            ShulkerBox(Some(color), name, tooltip, count, attr, hideAttr)
          case r".*SIGN" =>
            Sign(variantAs[WoodVariant], name, tooltip, count, attr, hideAttr)
          case r".*SKULL" =>
            MobHead(variantAs[MobHeadVariant], name, tooltip, count, attr, hideAttr)
          case r".*SLAB" =>
            Slab(variantAs[SlabVariant], name, tooltip, count, attr, hideAttr)
          case r".*SPAWN_EGG" =>
            SpawnEgg(variantAs[SpawnEggVariant], name, tooltip, count, attr, hideAttr)
          case r".*STEW" =>
            Stew(variantAs[StewVariant], name, tooltip, count, attr, hideAttr)
          case r".*STONE" =>
            Stone(variantAs[StoneVariant], name, tooltip, count, attr, hideAttr)
          case r"STRUCTURE.*" =>
            StructureBlock(variantAs[StructureBlockVariant], name, tooltip, count, attr, hideAttr)
          case r".*SWORD" =>
            Sword(variantAs[ToolVariant], name, tooltip, count, attr, hideAttr)
          case r".*TERRACOTTA" =>
            Terracotta(Some(color), name, tooltip, count, attr, hideAttr)
          case r".*TRAPDOOR" =>
            Trapdoor(variantAs[TrapdoorVariant], name, tooltip, count, attr, hideAttr)
          case r".*WALL" =>
            Wall(variantAs[WallVariant], name, tooltip, count, attr, hideAttr)
          case r".*WOOD" =>
            Wood(variantAs[WoodVariant], stripped = false, name, tooltip, count, attr, hideAttr)
          case r".*WOOL" => Wool(color, name, tooltip, count, attr, hideAttr)

          case r"INFESTED.*" =>
            InfestedBlock(variantAs[InfestedVariant], name, tooltip, count, attr, hideAttr)

          case r".*STAIRS" =>
            Stairs(variantAs[StairsVariant], name, tooltip, count, attr, hideAttr)

          case r".*WEIGHTED_PRESSURE_PLATE" =>
            WeightedPressurePlate(variantAs[WeightedPressurePlateVariant], name, tooltip, count, attr, hideAttr)
         */


      // format: off
      case Material.MUTTON  => Mutton(cooked = false, name, tooltip, count, attr, hideAttr)

      case Material.BAKED_POTATO => Potato(cooked = true, name, tooltip, count, attr, hideAttr)
      case Material.BEEF => Beef(cooked = false, name, tooltip, count, attr, hideAttr)
      case Material.CARVED_PUMPKIN => Pumpkin(carved = true, name, tooltip, count, attr, hideAttr)
      case Material.CHICKEN => Chicken(cooked = false, name, tooltip, count, attr, hideAttr)
      case Material.CHORUS_FRUIT => ChorusFruit(popped = false, name, tooltip, count, attr, hideAttr)
      case Material.COARSE_DIRT => Dirt(coarse = true, name, tooltip, count, attr, hideAttr)
      case Material.COD => Cod(cooked = false, name, tooltip, count, attr, hideAttr)
      case Material.COOKED_BEEF => Beef(cooked = true, name, tooltip, count, attr, hideAttr)
      case Material.COOKED_CHICKEN => Chicken(cooked = true, name, tooltip, count, attr, hideAttr)
      case Material.COOKED_COD => Cod(cooked = true, name, tooltip, count, attr, hideAttr)
      case Material.COOKED_MUTTON => Mutton(cooked = true, name, tooltip, count, attr, hideAttr)
      case Material.COOKED_PORKCHOP => Porkchop(cooked = true, name, tooltip, count, attr, hideAttr)
      case Material.COOKED_RABBIT => Rabbit(cooked = true, name, tooltip, count, attr, hideAttr)
      case Material.COOKED_SALMON => Salmon(cooked = true, name, tooltip, count, attr, hideAttr)
      case Material.DIRT => Dirt(coarse = false, name, tooltip, count, attr, hideAttr)
      case Material.ENCHANTED_GOLDEN_APPLE => GoldenApple(enchanted = true, name, tooltip, count, attr, hideAttr)
      case Material.FERMENTED_SPIDER_EYE => SpiderEye(fermented = true, name, tooltip, count, attr, hideAttr)
      case Material.FERN       => Fern(tall = false, name, tooltip, count, attr, hideAttr)
      case Material.FILLED_MAP => Map(filled = true, name, tooltip, count, attr, hideAttr)
      case Material.GOLDEN_APPLE => GoldenApple(enchanted = false, name, tooltip, count, attr, hideAttr)
      case Material.GRASS      => Grass(tall = false, name, tooltip, count, attr, hideAttr)
      case Material.LARGE_FERN => Fern(tall = true, name, tooltip, count, attr, hideAttr)
      case Material.MAP        => Map(filled = false, name, tooltip, count, attr, hideAttr)
      case Material.PISTON => Piston(sticky = false, name, tooltip, count, attr, hideAttr)
      case Material.POPPED_CHORUS_FRUIT => ChorusFruit(popped = true, name, tooltip, count, attr, hideAttr)
      case Material.PORKCHOP => Porkchop(cooked = false, name, tooltip, count, attr, hideAttr)
      case Material.POTATO => Potato(cooked = false, name, tooltip, count, attr, hideAttr)
      case Material.PUMPKIN => Pumpkin(carved = false, name, tooltip, count, attr, hideAttr)
      case Material.RABBIT => Rabbit(cooked = false, name, tooltip, count, attr, hideAttr)
      case Material.SALMON => Salmon(cooked = false, name, tooltip, count, attr, hideAttr)
      case Material.SPIDER_EYE => SpiderEye(fermented = false, name, tooltip, count, attr, hideAttr)
      case Material.SPONGE => Sponge(wet = false, name, tooltip, count, attr, hideAttr)
      case Material.STICKY_PISTON => Piston(sticky = true, name, tooltip, count, attr, hideAttr)
      case Material.TALL_GRASS => Grass(tall = true, name, tooltip, count, attr, hideAttr)
      case Material.WET_SPONGE => Sponge(wet = true, name, tooltip, count, attr, hideAttr)
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
