package gg.warcraft.monolith.spigot.world.item

import gg.warcraft.monolith.api.world.block.variant._
import gg.warcraft.monolith.api.world.block.BlockColor
import gg.warcraft.monolith.api.world.item._
import gg.warcraft.monolith.api.world.item.variant.{StructureBlockVariant, _}
import gg.warcraft.monolith.spigot.Extensions._
import javax.inject.Inject
import org.bukkit.Material
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.meta.Damageable

class SpigotItemMapper @Inject() (
    private val colorMapper: SpigotItemColorMapper,
    private val variantMapper: SpigotItemVariantMapper
) {
  def map(item: SpigotItemStack): Option[Item] = {
    // return None if Air
    val material = item.getType
    if (material.name.endsWith("AIR")) return None

    // Set common item data
    val meta = item.getItemMeta
    val name = meta.getDisplayName
    val tooltip: Array[String] = Array() // TODO item.getItemMeta.getLore
    val attr = Set.empty[String] // TODO map attributes
    val hideAttr = meta.hasItemFlag(ItemFlag.HIDE_ATTRIBUTES)

    // Lazily compute generic item data
    lazy val color = colorMapper.map(material)
    lazy val count = item.getAmount
    lazy val durability = material.getMaxDurability -
      meta.asInstanceOf[Damageable].getDamage
    lazy val unbreakable = meta.isUnbreakable
    lazy val hideUnbreakable = meta.hasItemFlag(ItemFlag.HIDE_UNBREAKABLE)
    lazy val variant = variantMapper.map(item)

    // Lazily construct tuples for all the different types of parameter sets
    lazy val params = (name, tooltip, count, attr, hideAttr)
    lazy val colorParams = (color, name, tooltip, count, attr, hideAttr)
    lazy val colorableParams = (Option(color), name, tooltip, count, attr, hideAttr)
    lazy val singleParams = (name, tooltip, attr, hideAttr)
    lazy val durableParams =
      (name, tooltip, attr, hideAttr, durability, unbreakable, hideUnbreakable)
    def v[T <: ItemVariant]: T = variant.asInstanceOf[T]
    def p[T <: ItemVariant] =
      (v[T], name, tooltip, count, attr, hideAttr)
    def e[T <: ItemVariant] =
      (v[T], name, tooltip, attr, hideAttr, durability, unbreakable, hideUnbreakable)

    // Map item
    Some(material match {
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

      // FIREWORK_STAR
      case Material.FIREWORK_STAR =>
        // TODO FIREWORK_STAR doesn't store color data in material
        //  BlockColorMapper has no idea what to do with it
        FireworkStar(BlockColor.RED, name, tooltip, count, attr, hideAttr)

      // GOLDEN_APPLE TODO move ENCHANTED and CARVED onto Apple/PumpkinVariant?
      case Material.GOLDEN_APPLE =>
        GoldenApple(enchanted = false, name, tooltip, count, attr, hideAttr)

      case Material.ENCHANTED_GOLDEN_APPLE =>
        GoldenApple(enchanted = true, name, tooltip, count, attr, hideAttr)

      // MAP
      case Material.MAP =>
        Map(filled = false, name, tooltip, count, attr, hideAttr)

      // TODO does a filled map have lots of extra data? Make own item?
      case Material.FILLED_MAP =>
        Map(filled = true, name, tooltip, count, attr, hideAttr)

      // PUMPKIN
      case Material.PUMPKIN =>
        Pumpkin(carved = false, name, tooltip, count, attr, hideAttr)

      case Material.CARVED_PUMPKIN =>
        Pumpkin(carved = true, name, tooltip, count, attr, hideAttr)

      case m if m.isAndesite         => Andesite.tupled(p[AndesiteVariant])
      case m if m.isAnvil            => Anvil.tupled(p[AnvilVariant])
      case m if m.isArrow            => Arrow.tupled(p[ArrowVariant])
      case m if m.isAxe              => Axe.tupled(e[AxeVariant])
      case m if m.isBanner           => Banner.tupled(colorParams)
      case m if m.isBed              => Bed.tupled(colorParams)
      case m if m.isBeef             => Beef.tupled(p[BeefVariant])
      case m if m.isBoat             => Boat.tupled(p[BoatVariant])
      case m if m.isBoots            => Boots.tupled(e[BootsVariant])
      case m if m.isBrick            => Brick.tupled(p[BrickVariant])
      case m if m.isBrickBlock       => BrickBlock.tupled(p[BrickBlockVariant])
      case m if m.isBucket           => Bucket.tupled(p[BucketVariant])
      case m if m.isButton           => Button.tupled(p[ButtonVariant])
      case m if m.isCarpet           => Carpet.tupled(colorParams)
      case m if m.isCobblestone      => Cobblestone.tupled(p[CobblestoneVariant])
      case m if m.isChest            => Chest.tupled(p[ChestVariant])
      case m if m.isChestplate       => Chestplate.tupled(e[ChestplateVariant])
      case m if m.isChicken          => Chicken.tupled(p[ChickenVariant])
      case m if m.isChorusFruit      => ChorusFruit.tupled(p[ChorusFruitVariant])
      case m if m.isCod              => Cod.tupled(p[CodVariant])
      case m if m.isCommandBlock     => CommandBlock.tupled(p[CommandBlockVariant])
      case m if m.isConcrete         => Concrete.tupled(colorParams)
      case m if m.isConcretePowder   => ConcretePowder.tupled(colorParams)
      case m if m.isCoral            => Coral.tupled(p[CoralVariant])
      case m if m.isCoralBlock       => CoralBlock.tupled(p[CoralBlockVariant])
      case m if m.isCoralFan         => CoralFan.tupled(p[CoralFanVariant])
      case m if m.isDiorite          => Diorite.tupled(p[DioriteVariant])
      case m if m.isDirt             => Dirt.tupled(p[DirtVariant])
      case m if m.isDoor             => Door.tupled(p[DoorVariant])
      case m if m.isDye              => Dye.tupled(colorParams)
      case m if m.isEndStone         => EndStone.tupled(p[EndStoneVariant])
      case m if m.isFence            => Fence.tupled(p[FenceVariant])
      case m if m.isFenceGate        => FenceGate.tupled(p[FenceGateVariant])
      case m if m.isFern             => Fern.tupled(p[FernVariant])
      case m if m.isFlower           => Flower.tupled(p[FlowerVariant])
      case m if m.isGlass            => Glass.tupled(colorableParams)
      case m if m.isGlassPane        => GlassPane.tupled(colorableParams)
      case m if m.isGlazedTerracotta => GlazedTerracotta.tupled(colorParams)
      case m if m.isGranite          => Granite.tupled(p[GraniteVariant])
      case m if m.isGrass            => Grass.tupled(p[GrassVariant])
      case m if m.isHelmet           => Helmet.tupled(e[HelmetVariant])
      case m if m.isHoe              => Hoe.tupled(e[HoeVariant])
      case m if m.isIce              => Ice.tupled(p[IceVariant])
      case m if m.isInfestedBlock    => InfestedBlock.tupled(p[InfestedBlockVariant])
      case m if m.isLeaves           => Leaves.tupled(p[LeavesVariant])
      case m if m.isLeggings         => Leggings.tupled(e[LeggingsVariant])
      case m if m.isLog              => Log.tupled(p[LogVariant])
      case m if m.isMinecart         => Minecart.tupled(p[MinecartVariant])
      case m if m.isMobHead          => MobHead.tupled(p[MobHeadVariant])
      case m if m.isMushroom         => Mushroom.tupled(p[MushroomVariant])
      case m if m.isMushroomBlock    => MushroomBlock.tupled(p[MushroomBlockVariant])
      case m if m.isMusicDisc        => MusicDisc.tupled(p[MusicDiscVariant])
      case m if m.isMutton           => Mutton.tupled(p[MuttonVariant])
      case m if m.isPickaxe          => Pickaxe.tupled(e[PickaxeVariant])
      case m if m.isPillar           => Pillar.tupled(p[PillarVariant])
      case m if m.isPiston           => Piston.tupled(p[PistonVariant])
      case m if m.isPlanks           => Planks.tupled(p[PlanksVariant])
      case m if m.isPlant            => Plant.tupled(p[PlantVariant])
      case m if m.isPorkchop         => Porkchop.tupled(p[PorkchopVariant])
      case m if m.isPotato           => Potato.tupled(p[PotatoVariant])
      case m if m.isPressurePlate    => PressurePlate.tupled(p[PressurePlateVariant])
      case m if m.isPrismarine       => Prismarine.tupled(p[PrismarineVariant])
      case m if m.isQuartzBlock      => QuartzBlock.tupled(p[QuartzBlockVariant])
      case m if m.isRabbit           => Rabbit.tupled(p[RabbitVariant])
      case m if m.isRail             => Rail.tupled(p[RailVariant])
      case m if m.isSalmon           => Salmon.tupled(p[SalmonVariant])
      case m if m.isSand             => Sand.tupled(p[SandVariant])
      case m if m.isSandstone        => Sandstone.tupled(p[SandstoneVariant])
      case m if m.isSapling          => Sapling.tupled(p[SaplingVariant])
      case m if m.isSeeds            => Seeds.tupled(p[SeedsVariant])
      case m if m.isShulkerBox       => ShulkerBox.tupled(colorableParams)
      case m if m.isShovel           => Shovel.tupled(e[ShovelVariant])
      case m if m.isSign             => Sign.tupled(p[SignVariant])
      case m if m.isSlab             => Slab.tupled(p[SlabVariant])
      case m if m.isSpawnEgg         => SpawnEgg.tupled(p[SpawnEggVariant])
      case m if m.isSpiderEye        => SpiderEye.tupled(p[SpiderEyeVariant])
      case m if m.isSponge           => Sponge.tupled(p[SpongeVariant])
      case m if m.isStairs           => Stairs.tupled(p[StairsVariant])
      case m if m.isStew             => Stew.tupled(p[StewVariant])
      case m if m.isStone            => Stone.tupled(p[StoneVariant])
      case m if m.isStructureBlock   => StructureBlock.tupled(p[StructureBlockVariant])
      case m if m.isSword            => Sword.tupled(e[SwordVariant])
      case m if m.isTerracotta       => Terracotta.tupled(colorableParams)
      case m if m.isTrapdoor         => Trapdoor.tupled(p[TrapdoorVariant])
      case m if m.isWall             => Wall.tupled(p[WallVariant])
      case m if m.isWood             => Wood.tupled(p[WoodVariant])
      case m if m.isWool             => Wool.tupled(colorParams)

      case m if m.isBannerPattern =>
        BannerPattern(v[BannerPatternVariant], name, tooltip, attr, hideAttr)

      case m if m.isHorseArmor =>
        HorseArmor(v[HorseArmorVariant], name, tooltip, attr, hideAttr)

      case m if m.isPotion =>
        val hideEffects = meta.hasItemFlag(ItemFlag.HIDE_POTION_EFFECTS)
        Potion(v[PotionVariant], hideEffects, name, tooltip, attr, hideAttr)

      case m if m.isWeightedPressurePlate =>
        WeightedPressurePlate.tupled(p[WeightedPressurePlateVariant])
    })
  }

  def map(item: Item): SpigotItemStack = {
    val material = item match {
      case it: ColoredItem     => colorMapper.map(it)
      case it: ColorableItem   => colorMapper.map(it)
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

      case it =>
        it.`type` match {
          // TODO the ItemType name for CLAY_BLOCK and DRIED_KELP is inconsistent with blocks
          case ItemType.APPLE                => Material.APPLE
          case ItemType.ARMOR_STAND          => Material.ARMOR_STAND
          case ItemType.BAMBOO               => Material.BAMBOO
          case ItemType.BARREL               => Material.BARREL
          case ItemType.BARRIER              => Material.BARRIER
          case ItemType.BEACON               => Material.BEACON
          case ItemType.BEDROCK              => Material.BEDROCK
          case ItemType.BEETROOT             => Material.BEETROOT
          case ItemType.BELL                 => Material.BELL
          case ItemType.BLAST_FURNACE        => Material.BLAST_FURNACE
          case ItemType.BLAZE_POWDER         => Material.BLAZE_POWDER
          case ItemType.BLAZE_ROD            => Material.BLAZE_ROD
          case ItemType.BONE                 => Material.BONE
          case ItemType.BONE_BLOCK           => Material.BONE_BLOCK
          case ItemType.BONE_MEAL            => Material.BONE_MEAL
          case ItemType.BOTTLE_OF_ENCHANTING => Material.EXPERIENCE_BOTTLE
          case ItemType.BOOK                 => Material.BOOK
          case ItemType.BOOKSHELF            => Material.BOOKSHELF
          case ItemType.BOW                  => Material.BOW
          case ItemType.BOWL                 => Material.BOWL
          case ItemType.BREAD                => Material.BREAD
          case ItemType.BREWING_STAND        => Material.BREWING_STAND
          case ItemType.CACTUS               => Material.CACTUS
          case ItemType.CAKE                 => Material.CAKE
          case ItemType.CAMPFIRE             => Material.CAMPFIRE
          case ItemType.CARROT               => Material.CARROT
          case ItemType.CARROT_ON_A_STICK    => Material.CARROT_ON_A_STICK
          case ItemType.CARTOGRAPHY_TABLE    => Material.CARTOGRAPHY_TABLE
          case ItemType.CAULDRON             => Material.CAULDRON
          case ItemType.CHARCOAL             => Material.CHARCOAL
          case ItemType.CHORUS_FLOWER        => Material.CHORUS_FLOWER
          case ItemType.CHORUS_PLANT         => Material.CHORUS_PLANT
          case ItemType.CLAY                 => Material.CLAY_BALL
          case ItemType.CLAY_BLOCK           => Material.CLAY
          case ItemType.CLOCK                => Material.CLOCK
          case ItemType.COAL                 => Material.COAL
          case ItemType.COAL_BLOCK           => Material.COAL_BLOCK
          case ItemType.COAL_ORE             => Material.COAL_ORE
          case ItemType.COBWEB               => Material.COBWEB
          case ItemType.COCOA_BEANS          => Material.COCOA_BEANS
          case ItemType.COMPARATOR           => Material.COMPARATOR
          case ItemType.COMPASS              => Material.COMPASS
          case ItemType.COMPOSTER            => Material.COMPOSTER
          case ItemType.CONDUIT              => Material.CONDUIT
          case ItemType.COOKIE               => Material.COOKIE
          case ItemType.CRAFTING_TABLE       => Material.CRAFTING_TABLE
          case ItemType.CROSSBOW             => Material.CROSSBOW
          case ItemType.DAYLIGHT_DETECTOR    => Material.DAYLIGHT_DETECTOR
          case ItemType.DEAD_BUSH            => Material.DEAD_BUSH
          case ItemType.DEBUG_STICK          => Material.DEBUG_STICK
          case ItemType.DIAMOND              => Material.DIAMOND
          case ItemType.DIAMOND_BLOCK        => Material.DIAMOND_BLOCK
          case ItemType.DIAMOND_ORE          => Material.DIAMOND_ORE
          case ItemType.DIRT                 => Material.DIRT
          case ItemType.DISPENSER            => Material.DISPENSER
          case ItemType.DRAGON_BREATH        => Material.DRAGON_BREATH
          case ItemType.DRAGON_EGG           => Material.DRAGON_EGG
          case ItemType.DRIED_KELP           => Material.DRIED_KELP
          case ItemType.DRIED_KELP_BLOCK     => Material.DRIED_KELP_BLOCK
          case ItemType.DROPPER              => Material.DROPPER
          case ItemType.EGG                  => Material.EGG
          case ItemType.ELYTRA               => Material.ELYTRA
          case ItemType.EMERALD              => Material.EMERALD
          case ItemType.EMERALD_BLOCK        => Material.EMERALD_BLOCK
          case ItemType.EMERALD_ORE          => Material.EMERALD_ORE
          case ItemType.ENCHANTED_BOOK       => Material.ENCHANTED_BOOK
          case ItemType.ENCHANTING_TABLE     => Material.ENCHANTING_TABLE
          case ItemType.END_CRYSTAL          => Material.END_CRYSTAL
          case ItemType.END_PORTAL_FRAME     => Material.END_PORTAL_FRAME
          case ItemType.END_ROD              => Material.END_ROD
          case ItemType.ENDER_EYE            => Material.ENDER_EYE
          case ItemType.ENDER_PEARL          => Material.ENDER_PEARL
          case ItemType.FARMLAND             => Material.FARMLAND
          case ItemType.FEATHER              => Material.FEATHER
          case ItemType.FIRE_CHARGE          => Material.FIRE_CHARGE
          case ItemType.FISHING_ROD          => Material.FISHING_ROD
          case ItemType.FLETCHING_TABLE      => Material.FLETCHING_TABLE
          case ItemType.FLINT                => Material.FLINT
          case ItemType.FLINT_AND_STEEL      => Material.FLINT_AND_STEEL
          case ItemType.FLOWER_POT           => Material.FLOWER_POT
          case ItemType.FIREWORK_ROCKET      => Material.FIREWORK_ROCKET
          case ItemType.FURNACE              => Material.FURNACE
          case ItemType.KNOWLEDGE_BOOK       => Material.KNOWLEDGE_BOOK
          case ItemType.GLOWSTONE            => Material.GLOWSTONE
          case ItemType.GOLD_BLOCK           => Material.GOLD_BLOCK
          case ItemType.GOLD_INGOT           => Material.GOLD_INGOT
          case ItemType.GOLD_NUGGET          => Material.GOLD_NUGGET
          case ItemType.GOLD_ORE             => Material.GOLD_ORE
          case ItemType.GOLDEN_CARROT        => Material.GOLDEN_CARROT
          case ItemType.GOLDEN_MELON_SLICE   => Material.GLISTERING_MELON_SLICE
          case ItemType.GHAST_TEAR           => Material.GHAST_TEAR
          case ItemType.GLASS_BOTTLE         => Material.GLASS_BOTTLE
          case ItemType.GLOWSTONE_DUST       => Material.GLOWSTONE_DUST
          case ItemType.GRASS_BLOCK          => Material.GRASS_BLOCK
          case ItemType.GRASS_PATH           => Material.GRASS_PATH
          case ItemType.GRAVEL               => Material.GRAVEL
          case ItemType.GRINDSTONE           => Material.GRINDSTONE
          case ItemType.GUNPOWDER            => Material.GUNPOWDER
          case ItemType.HAY_BALE             => Material.HAY_BLOCK
          case ItemType.HEART_OF_THE_SEA     => Material.HEART_OF_THE_SEA
          case ItemType.HOPPER               => Material.HOPPER
          case ItemType.INK_SAC              => Material.INK_SAC
          case ItemType.IRON_BLOCK           => Material.IRON_BLOCK
          case ItemType.IRON_BARS            => Material.IRON_BARS
          case ItemType.IRON_INGOT           => Material.IRON_INGOT
          case ItemType.IRON_NUGGET          => Material.IRON_NUGGET
          case ItemType.IRON_ORE             => Material.IRON_ORE
          case ItemType.ITEM_FRAME           => Material.ITEM_FRAME
          case ItemType.JACK_OF_THE_LANTERN  => Material.JACK_O_LANTERN
          case ItemType.JIGSAW_BLOCK         => Material.JIGSAW
          case ItemType.JUKEBOX              => Material.JUKEBOX
          case ItemType.KELP                 => Material.KELP
          case ItemType.LADDER               => Material.LADDER
          case ItemType.LANTERN              => Material.LANTERN
          case ItemType.LAPIS                => Material.LAPIS_LAZULI
          case ItemType.LAPIS_BLOCK          => Material.LAPIS_BLOCK
          case ItemType.LAPIS_ORE            => Material.LAPIS_ORE
          case ItemType.LEAD                 => Material.LEAD
          case ItemType.LEATHER              => Material.LEATHER
          case ItemType.LECTERN              => Material.LECTERN
          case ItemType.LEVER                => Material.LEVER
          case ItemType.LILY_PAD             => Material.LILY_PAD
          case ItemType.LOOM                 => Material.LOOM
          case ItemType.MAGMA_BLOCK          => Material.MAGMA_BLOCK
          case ItemType.MAGMA_CREAM          => Material.MAGMA_CREAM
          case ItemType.MELON                => Material.MELON
          case ItemType.MELON_SLICE          => Material.MELON_SLICE
          case ItemType.MYCELIUM             => Material.MYCELIUM
          case ItemType.NAME_TAG             => Material.NAME_TAG
          case ItemType.NAUTILUS_SHELL       => Material.NAUTILUS_SHELL
          case ItemType.NETHER_STAR          => Material.NETHER_STAR
          case ItemType.NETHERRACK           => Material.NETHERRACK
          case ItemType.NETHER_WART          => Material.NETHER_WART
          case ItemType.NETHER_WART_BLOCK    => Material.NETHER_WART_BLOCK
          case ItemType.NOTE_BLOCK           => Material.NOTE_BLOCK
          case ItemType.OBSERVER             => Material.OBSERVER
          case ItemType.OBSIDIAN             => Material.OBSIDIAN
          case ItemType.PAINTING             => Material.PAINTING
          case ItemType.PAPER                => Material.PAPER
          case ItemType.PHANTOM_MEMBRANE     => Material.PHANTOM_MEMBRANE
          case ItemType.PODZOL               => Material.PODZOL
          case ItemType.PRISMARINE_CRYSTALS  => Material.PRISMARINE_CRYSTALS
          case ItemType.PRISMARINE_SHARD     => Material.PRISMARINE_SHARD
          case ItemType.PUFFERFISH           => Material.PUFFERFISH
          case ItemType.PUMPKIN              => Material.PUMPKIN
          case ItemType.PUMPKIN_PIE          => Material.PUMPKIN_PIE
          case ItemType.PURPUR_BLOCK         => Material.PURPUR_BLOCK
          case ItemType.QUARTZ               => Material.QUARTZ
          case ItemType.QUARTZ_ORE           => Material.NETHER_QUARTZ_ORE
          case ItemType.RABBIT_FOOT          => Material.RABBIT_FOOT
          case ItemType.RABBIT_HIDE          => Material.RABBIT_HIDE
          case ItemType.REDSTONE             => Material.REDSTONE
          case ItemType.REDSTONE_BLOCK       => Material.REDSTONE_BLOCK
          case ItemType.REDSTONE_LAMP        => Material.REDSTONE_LAMP
          case ItemType.REDSTONE_ORE         => Material.REDSTONE_ORE
          case ItemType.REDSTONE_TORCH       => Material.REDSTONE_TORCH
          case ItemType.REPEATER             => Material.REPEATER
          case ItemType.ROTTEN_FLESH         => Material.ROTTEN_FLESH
          case ItemType.SCAFFOLDING          => Material.SCAFFOLDING
          case ItemType.SEAGRASS             => Material.SEAGRASS
          case ItemType.SEA_LANTERN          => Material.SEA_LANTERN
          case ItemType.SEA_PICKLE           => Material.SEA_PICKLE
          case ItemType.SADDLE               => Material.SADDLE
          case ItemType.SCUTE                => Material.SCUTE
          case ItemType.SHEARS               => Material.SHEARS
          case ItemType.SHIELD               => Material.SHIELD
          case ItemType.SHULKER_SHELL        => Material.SHULKER_SHELL
          case ItemType.SLIME_BLOCK          => Material.SLIME_BLOCK
          case ItemType.SLIMEBALL            => Material.SLIME_BALL
          case ItemType.SMITHING_TABLE       => Material.SMITHING_TABLE
          case ItemType.SMOKER               => Material.SMOKER
          case ItemType.SNOW                 => Material.SNOW
          case ItemType.SNOW_BLOCK           => Material.SNOW_BLOCK
          case ItemType.SNOWBALL             => Material.SNOWBALL
          case ItemType.SPAWNER              => Material.SPAWNER
          case ItemType.SOUL_SAND            => Material.SOUL_SAND
          case ItemType.STICK                => Material.STICK
          case ItemType.STONECUTTER          => Material.STONECUTTER
          case ItemType.STRING               => Material.STRING
          case ItemType.SUGAR_CANE           => Material.SUGAR_CANE
          case ItemType.SWEET_BERRIES        => Material.SWEET_BERRIES
          case ItemType.SUGAR                => Material.SUGAR
          case ItemType.TNT                  => Material.TNT
          case ItemType.TORCH                => Material.TORCH
          case ItemType.TOTEM_OF_UNDYING     => Material.TOTEM_OF_UNDYING
          case ItemType.TRIDENT              => Material.TRIDENT
          case ItemType.TRIPWIRE_HOOK        => Material.TRIPWIRE_HOOK
          case ItemType.TROPICAL_FISH        => Material.TROPICAL_FISH
          case ItemType.TURTLE_HELMET        => Material.TURTLE_HELMET
          case ItemType.TURTLE_EGG           => Material.TURTLE_EGG
          case ItemType.VINE                 => Material.VINE
          case ItemType.BOOK_AND_QUILL       => Material.WRITABLE_BOOK
          case ItemType.WRITTEN_BOOK         => Material.WRITTEN_BOOK
          case ItemType.WHEAT                => Material.WHEAT

          case _ => throw new IllegalArgumentException(s"$item")
        }
    }

    val spigotItem = new SpigotItemStack(material)

    // TODO set item values
    // TODO map skull meta

    spigotItem
  }
}
