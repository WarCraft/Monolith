package gg.warcraft.monolith.spigot.world.item

import gg.warcraft.monolith.api.world.block.variant._
import gg.warcraft.monolith.api.world.item._
import gg.warcraft.monolith.api.world.item.variant.{StructureBlockVariant, _}
import gg.warcraft.monolith.spigot.Extensions._
import org.bukkit.Material
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.meta.Damageable

class SpigotItemMapper(
    private implicit val typeMapper: SpigotItemTypeMapper,
    private implicit val variantMapper: SpigotItemVariantMapper
) {
  def map(item: SpigotItemStack): Option[Item] = {
    // return None if Air
    val material = item.getType
    if (material.name.endsWith("AIR")) return None

    // Set common item data
    val meta = item.getItemMeta
    val name = meta.getDisplayName
    val tooltip: List[String] = List.empty // TODO item.getItemMeta.getLore
    val attr = Set.empty[String] // TODO map attributes
    val hideAttr = meta.hasItemFlag(ItemFlag.HIDE_ATTRIBUTES)

    // Lazily compute generic item data
    lazy val count = item.getAmount
    lazy val durability = material.getMaxDurability -
      meta.asInstanceOf[Damageable].getDamage
    lazy val unbreakable = meta.isUnbreakable
    lazy val hideUnbreakable = meta.hasItemFlag(ItemFlag.HIDE_UNBREAKABLE)
    lazy val variant = variantMapper.map(item)

    // Lazily construct tuples for all the different types of parameter sets
    lazy val params = (name, tooltip, count, attr, hideAttr)
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
      case Material.END_STONE              => EndStone.tupled(params)
      case Material.END_STONE_BRICKS       => EndStoneBrick.tupled(params)
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
        FireworkStar(FireworkStarVariant.RED, name, tooltip, count, attr, hideAttr)

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

      case m if m.isAndesite       => Andesite.tupled(p[AndesiteVariant])
      case m if m.isAnvil          => Anvil.tupled(p[AnvilVariant])
      case m if m.isArrow          => Arrow.tupled(p[ArrowVariant])
      case m if m.isAxe            => Axe.tupled(e[AxeVariant])
      case m if m.isBanner         => Banner.tupled(p[BannerVariant])
      case m if m.isBed            => Bed.tupled(p[BedVariant])
      case m if m.isBeef           => Beef.tupled(p[BeefVariant])
      case m if m.isBoat           => Boat.tupled(p[BoatVariant])
      case m if m.isBoots          => Boots.tupled(e[BootsVariant])
      case m if m.isBrick          => Brick.tupled(p[BrickVariant])
      case m if m.isBrickBlock     => BrickBlock.tupled(p[BrickBlockVariant])
      case m if m.isBucket         => Bucket.tupled(p[BucketVariant])
      case m if m.isButton         => Button.tupled(p[ButtonVariant])
      case m if m.isCarpet         => Carpet.tupled(p[CarpetVariant])
      case m if m.isCobblestone    => Cobblestone.tupled(p[CobblestoneVariant])
      case m if m.isChest          => Chest.tupled(p[ChestVariant])
      case m if m.isChestplate     => Chestplate.tupled(e[ChestplateVariant])
      case m if m.isChicken        => Chicken.tupled(p[ChickenVariant])
      case m if m.isChorusFruit    => ChorusFruit.tupled(p[ChorusFruitVariant])
      case m if m.isCod            => Cod.tupled(p[CodVariant])
      case m if m.isCommandBlock   => CommandBlock.tupled(p[CommandBlockVariant])
      case m if m.isConcrete       => Concrete.tupled(p[ConcreteVariant])
      case m if m.isConcretePowder => ConcretePowder.tupled(p[ConcretePowderVariant])
      case m if m.isCoral          => Coral.tupled(p[CoralVariant])
      case m if m.isCoralBlock     => CoralBlock.tupled(p[CoralBlockVariant])
      case m if m.isCoralFan       => CoralFan.tupled(p[CoralFanVariant])
      case m if m.isDiorite        => Diorite.tupled(p[DioriteVariant])
      case m if m.isDirt           => Dirt.tupled(p[DirtVariant])
      case m if m.isDoor           => Door.tupled(p[DoorVariant])
      case m if m.isDye            => Dye.tupled(p[DyeVariant])
      case m if m.isFence          => Fence.tupled(p[FenceVariant])
      case m if m.isFenceGate      => FenceGate.tupled(p[FenceGateVariant])
      case m if m.isFern           => Fern.tupled(p[FernVariant])
      case m if m.isFlower         => Flower.tupled(p[FlowerVariant])
      case m if m.isGlass          => Glass.tupled(p[GlassVariant])
      case m if m.isGlassPane      => GlassPane.tupled(p[GlassPaneVariant])
      case m if m.isGranite        => Granite.tupled(p[GraniteVariant])
      case m if m.isGrass          => Grass.tupled(p[GrassVariant])
      case m if m.isHelmet         => Helmet.tupled(e[HelmetVariant])
      case m if m.isHoe            => Hoe.tupled(e[HoeVariant])
      case m if m.isIce            => Ice.tupled(p[IceVariant])
      case m if m.isInfestedBlock  => InfestedBlock.tupled(p[InfestedBlockVariant])
      case m if m.isLeaves         => Leaves.tupled(p[LeavesVariant])
      case m if m.isLeggings       => Leggings.tupled(e[LeggingsVariant])
      case m if m.isLog            => Log.tupled(p[LogVariant])
      case m if m.isMinecart       => Minecart.tupled(p[MinecartVariant])
      case m if m.isMobHead        => MobHead.tupled(p[MobHeadVariant])
      case m if m.isMushroom       => Mushroom.tupled(p[MushroomVariant])
      case m if m.isMushroomBlock  => MushroomBlock.tupled(p[MushroomBlockVariant])
      case m if m.isMusicDisc      => MusicDisc.tupled(p[MusicDiscVariant])
      case m if m.isMutton         => Mutton.tupled(p[MuttonVariant])
      case m if m.isPickaxe        => Pickaxe.tupled(e[PickaxeVariant])
      case m if m.isPillar         => Pillar.tupled(p[PillarVariant])
      case m if m.isPiston         => Piston.tupled(p[PistonVariant])
      case m if m.isPlanks         => Planks.tupled(p[PlanksVariant])
      case m if m.isPlant          => Plant.tupled(p[PlantVariant])
      case m if m.isPorkchop       => Porkchop.tupled(p[PorkchopVariant])
      case m if m.isPotato         => Potato.tupled(p[PotatoVariant])
      case m if m.isPressurePlate  => PressurePlate.tupled(p[PressurePlateVariant])
      case m if m.isPrismarine     => Prismarine.tupled(p[PrismarineVariant])
      case m if m.isQuartzBlock    => QuartzBlock.tupled(p[QuartzBlockVariant])
      case m if m.isRabbit         => Rabbit.tupled(p[RabbitVariant])
      case m if m.isRail           => Rail.tupled(p[RailVariant])
      case m if m.isSalmon         => Salmon.tupled(p[SalmonVariant])
      case m if m.isSand           => Sand.tupled(p[SandVariant])
      case m if m.isSandstone      => Sandstone.tupled(p[SandstoneVariant])
      case m if m.isSapling        => Sapling.tupled(p[SaplingVariant])
      case m if m.isSeeds          => Seeds.tupled(p[SeedsVariant])
      case m if m.isShulkerBox     => ShulkerBox.tupled(p[ShulkerBoxVariant])
      case m if m.isShovel         => Shovel.tupled(e[ShovelVariant])
      case m if m.isSign           => Sign.tupled(p[SignVariant])
      case m if m.isSlab           => Slab.tupled(p[SlabVariant])
      case m if m.isSpawnEgg       => SpawnEgg.tupled(p[SpawnEggVariant])
      case m if m.isSpiderEye      => SpiderEye.tupled(p[SpiderEyeVariant])
      case m if m.isSponge         => Sponge.tupled(p[SpongeVariant])
      case m if m.isStairs         => Stairs.tupled(p[StairsVariant])
      case m if m.isStew           => Stew.tupled(p[StewVariant])
      case m if m.isStone          => Stone.tupled(p[StoneVariant])
      case m if m.isStoneBrick     => StoneBrick.tupled(p[StoneBrickVariant])
      case m if m.isStructureBlock => StructureBlock.tupled(p[StructureBlockVariant])
      case m if m.isSword          => Sword.tupled(e[SwordVariant])
      case m if m.isTerracotta     => Terracotta.tupled(p[TerracottaVariant])
      case m if m.isTrapdoor       => Trapdoor.tupled(p[TrapdoorVariant])
      case m if m.isWall           => Wall.tupled(p[WallVariant])
      case m if m.isWood           => Wood.tupled(p[WoodVariant])
      case m if m.isWool           => Wool.tupled(p[WoolVariant])

      case m if m.isBannerPattern =>
        BannerPattern(v[BannerPatternVariant], name, tooltip, attr, hideAttr)

      case m if m.isGlazedTerracotta =>
        GlazedTerracotta.tupled(p[GlazedTerracottaVariant])

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

    val spigotItem = new SpigotItemStack(material)

    // TODO set item values
    // TODO map skull meta

    spigotItem
  }
}
