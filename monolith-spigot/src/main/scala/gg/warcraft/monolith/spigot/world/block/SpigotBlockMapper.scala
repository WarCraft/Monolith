package gg.warcraft.monolith.spigot.world.block

import com.google.inject.Inject
import gg.warcraft.monolith.api.world.block.{Rail, _}
import gg.warcraft.monolith.api.world.block.shape.{RailsShape, StairsShape}
import gg.warcraft.monolith.api.world.block.state._
import gg.warcraft.monolith.api.world.block.variant._
import gg.warcraft.monolith.spigot.Extensions._
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper
import org.bukkit.{Bukkit, Material}
import org.bukkit.block.data._
import org.bukkit.block.data.`type`.Door.{Hinge => SpigotDoorHinge}
import org.bukkit.block.data.`type`.Switch

class SpigotBlockMapper @Inject() (
    private val locationMapper: SpigotLocationMapper,
    private val attachmentMapper: SpigotBlockAttachmentMapper,
    private val bisectionMapper: SpigotBlockBisectionMapper,
    private val colorMapper: SpigotBlockColorMapper,
    private val extensionMapper: SpigotBlockExtensionMapper,
    private val faceMapper: SpigotBlockFaceMapper,
    private val orientationMapper: SpigotBlockOrientationMapper,
    private val rotationMapper: SpigotBlockRotationMapper,
    private val shapeMapper: SpigotBlockShapeMapper,
    private val stateMapper: SpigotBlockStateMapper,
    private val variantMapper: SpigotBlockVariantMapper
) {
  def map(block: SpigotBlock): Block = {
    val loc = locationMapper.map(block.getLocation).toBlockLocation
    val spigotState = block.getState
    val spigotData = spigotState.getBlockData

    // Lazily compute generic block data
    lazy val color = colorMapper.map(block.getType)
    lazy val flooded = spigotState.asInstanceOf[Waterlogged].isWaterlogged
    lazy val lit = spigotState.asInstanceOf[Lightable].isLit
    lazy val open = spigotState.asInstanceOf[Openable].isOpen
    lazy val powered = spigotState.asInstanceOf[Powerable].isPowered
    lazy val shape = shapeMapper.map(block)
    lazy val snowy = spigotState.asInstanceOf[Snowable].isSnowy
    lazy val state = stateMapper.map(block)
    lazy val variant = variantMapper.map(block)

    lazy val attached = {
      // TODO properly map Grindstone
      if (block.getType == Material.GRINDSTONE) {
        BlockAttachment.FLOOR
      } else {
        val switch = spigotState.asInstanceOf[Switch]
        attachmentMapper.map(switch)
      }
    }

    lazy val bisection = {
      val bisected = spigotState.asInstanceOf[Bisected]
      bisectionMapper.map(bisected.getHalf)
    }

    lazy val dir = {
      val directional = spigotState.asInstanceOf[Directional]
      faceMapper.map(directional.getFacing)
    }

    lazy val extensions = {
      val multipleFacing = spigotState.asInstanceOf[MultipleFacing]
      extensionMapper.map(multipleFacing.getFaces)
    }

    lazy val orientation = {
      val orientable = spigotState.asInstanceOf[Orientable]
      orientationMapper.map(orientable.getAxis)
    }

    lazy val rotation = {
      val rotatable = spigotState.asInstanceOf[Rotatable]
      rotationMapper.map(rotatable.getRotation)
    }

    // Map block
    def v[T <: BlockVariant]: T = variant.asInstanceOf[T]
    def s[T <: BlockState]: T = state.asInstanceOf[T]
    def shapeAs[T <: BlockShape]: T = shape.asInstanceOf[T]
    def dataAs[T <: SpigotBlockData]: T = spigotData.asInstanceOf[T]

    block.getType match {
      case Material.BARREL              => Barrel(loc, dir, open)
      case Material.BARRIER             => Barrier(loc)
      case Material.BEACON              => Beacon(loc)
      case Material.BEDROCK             => Bedrock(loc)
      case Material.BEETROOTS           => Beetroots(loc, s[BeetrootState])
      case Material.BELL                => Bell(loc, dir)
      case Material.BLAST_FURNACE       => BlastFurnace(loc, dir, lit)
      case Material.BONE_BLOCK          => BoneBlock(loc, orientation)
      case Material.BOOKSHELF           => Bookshelf(loc)
      case Material.BREWING_STAND       => BrewingStand(loc)
      case Material.CACTUS              => Cactus(loc, s[CactusState])
      case Material.CAKE                => Cake(loc, s[CakeState])
      case Material.CARTOGRAPHY_TABLE   => CartographyTable(loc)
      case Material.CARROTS             => Carrots(loc, s[CarrotState])
      case Material.CAULDRON            => Cauldron(loc, s[CauldronState])
      case Material.CHORUS_FLOWER       => ChorusFlower(loc, s[ChorusFlowerState])
      case Material.CHORUS_PLANT        => ChorusPlant(loc, extensions)
      case Material.CLAY                => Clay(loc)
      case Material.COAL_BLOCK          => CoalBlock(loc)
      case Material.COAL_ORE            => CoalOre(loc)
      case Material.COBWEB              => Cobweb(loc)
      case Material.COCOA               => CocoaPod(loc, s[CocoaState], dir)
      case Material.COMPARATOR          => Comparator(loc, v[ComparatorVariant], dir, powered)
      case Material.COMPOSTER           => Composter(loc, s[ComposterState])
      case Material.CONDUIT             => Conduit(loc, flooded)
      case Material.CRAFTING_TABLE      => CraftingTable(loc)
      case Material.DAYLIGHT_DETECTOR   => DaylightDetector(loc)
      case Material.DEAD_BUSH           => DeadBush(loc)
      case Material.DIAMOND_BLOCK       => DiamondBlock(loc)
      case Material.DIAMOND_ORE         => DiamondOre(loc)
      case Material.DISPENSER           => Dispenser(loc, dir, powered)
      case Material.DRAGON_EGG          => DragonEgg(loc)
      case Material.DRIED_KELP_BLOCK    => DriedKelp(loc)
      case Material.DROPPER             => Dropper(loc, dir, powered)
      case Material.EMERALD_BLOCK       => EmeraldBlock(loc)
      case Material.EMERALD_ORE         => EmeraldOre(loc)
      case Material.ENCHANTING_TABLE    => EnchantingTable(loc)
      case Material.END_GATEWAY         => EndGateway(loc)
      case Material.END_PORTAL          => EndPortal(loc)
      case Material.END_ROD             => EndRod(loc, dir)
      case Material.FARMLAND            => Farmland(loc)
      case Material.FIRE                => Fire(loc)
      case Material.FLETCHING_TABLE     => FletchingTable(loc)
      case Material.FROSTED_ICE         => Frost(loc, s[FrostState])
      case Material.FURNACE             => Furnace(loc, dir, lit)
      case Material.GLOWSTONE           => Glowstone(loc)
      case Material.GOLD_BLOCK          => GoldBlock(loc)
      case Material.GOLD_ORE            => GoldOre(loc)
      case Material.GRASS_BLOCK         => GrassBlock(loc, snowy)
      case Material.GRASS_PATH          => GrassPath(loc)
      case Material.GRAVEL              => Gravel(loc)
      case Material.GRINDSTONE          => Grindstone(loc, dir, attached)
      case Material.HAY_BLOCK           => HayBale(loc, orientation)
      case Material.IRON_BLOCK          => IronBlock(loc)
      case Material.IRON_BARS           => IronBars(loc, extensions, flooded)
      case Material.IRON_ORE            => IronOre(loc)
      case Material.JIGSAW              => JigsawBlock(loc, dir)
      case Material.LADDER              => Ladder(loc, dir, flooded)
      case Material.LAPIS_BLOCK         => LapisBlock(loc)
      case Material.LAPIS_ORE           => LapisOre(loc)
      case Material.LAVA                => Lava(loc, s[LavaState])
      case Material.LEVER               => Lever(loc, dir, attached, powered)
      case Material.LILY_PAD            => LilyPad(loc)
      case Material.LOOM                => Loom(loc, dir)
      case Material.MAGMA_BLOCK         => MagmaBlock(loc)
      case Material.MELON               => Melon(loc)
      case Material.MYCELIUM            => Mycelium(loc, snowy)
      case Material.NETHERRACK          => Netherrack(loc)
      case Material.NETHER_PORTAL       => NetherPortal(loc, orientation)
      case Material.NETHER_WART         => NetherWarts(loc, s[NetherWartState])
      case Material.NETHER_WART_BLOCK   => NetherWartBlock(loc)
      case Material.OBSERVER            => Observer(loc, dir, powered)
      case Material.OBSIDIAN            => Obsidian(loc)
      case Material.PODZOL              => Podzol(loc, snowy)
      case Material.POTATOES            => Potatoes(loc, s[PotatoState])
      case Material.PURPUR_BLOCK        => PurpurBlock(loc)
      case Material.NETHER_QUARTZ_ORE   => QuartzOre(loc)
      case Material.REDSTONE_BLOCK      => RedstoneBlock(loc)
      case Material.REDSTONE_LAMP       => RedstoneLamp(loc, lit)
      case Material.REDSTONE_ORE        => RedstoneOre(loc)
      case Material.REDSTONE_TORCH      => RedstoneTorch(loc, None, lit)
      case Material.REDSTONE_WALL_TORCH => RedstoneTorch(loc, Some(dir), lit)
      case Material.REDSTONE_WIRE       => RedstoneWire(loc, s[RedstoneWireState])
      case Material.SCAFFOLDING         => Scaffolding(loc)
      case Material.SEA_LANTERN         => SeaLantern(loc)
      case Material.SEA_PICKLE          => SeaPickle(loc, s[SeaPickleState], flooded)
      case Material.SLIME_BLOCK         => SlimeBlock(loc)
      case Material.SMITHING_TABLE      => SmithingTable(loc)
      case Material.SMOKER              => Smoker(loc, dir, lit)
      case Material.SNOW                => Snow(loc)
      case Material.SNOW_BLOCK          => SnowBlock(loc)
      case Material.SPAWNER             => Spawner(loc)
      case Material.STONECUTTER         => Stonecutter(loc, dir)
      case Material.SUGAR_CANE          => SugarCane(loc, s[SugarCaneState])
      case Material.TURTLE_EGG          => TurtleEgg(loc, s[TurtleEggState])
      case Material.VINE                => Vine(loc, extensions)
      case Material.WATER               => Water(loc, s[WaterState])
      case Material.WHEAT               => Wheat(loc, s[WheatState])

      // BAMBOO
      case Material.BAMBOO =>
        val thick = dataAs[SpigotBamboo].getAge == 1
        Bamboo(loc, v[BambooVariant], s[BambooState], thick)

      // BUBBLE_COLUMN
      case Material.BUBBLE_COLUMN =>
        val drag = dataAs[SpigotBubbleColumn].isDrag
        BubbleColumn(loc, drag)

      // CAMPFIRE
      case Material.CAMPFIRE =>
        val signal = dataAs[SpigotCampfire].isSignalFire
        Campfire(loc, dir, flooded, lit, signal)

      // END_PORTAL_FRAME
      case Material.END_PORTAL_FRAME =>
        val eye = dataAs[SpigotEndPortalFrame].hasEye
        EndPortalFrame(loc, dir, eye)

      // FERN TODO should section be an Option?
      case Material.FERN       => Fern(loc, v[FernVariant], BlockBisection.BOTTOM)
      case Material.LARGE_FERN => Fern(loc, v[FernVariant], bisection)

      // GRASS
      case Material.GRASS      => Grass(loc, v[GrassVariant], BlockBisection.BOTTOM)
      case Material.TALL_GRASS => Grass(loc, v[GrassVariant], bisection)

      // HOPPER
      case Material.HOPPER =>
        val enabled = dataAs[SpigotHopper].isEnabled
        Hopper(loc, dir, !enabled)

      // JUKEBOX
      case Material.JUKEBOX =>
        val record = dataAs[SpigotJukebox].hasRecord
        Jukebox(loc, record)

      // KELP
      case Material.KELP       => Kelp(loc, s[KelpState])
      case Material.KELP_PLANT => Kelp(loc, KelpState.AGE_25)

      // LANTERN
      case Material.LANTERN =>
        val hanging = spigotData.asInstanceOf[SpigotLantern].isHanging
        Lantern(loc, hanging)

      // LECTERN
      case Material.LECTERN =>
        val book = dataAs[SpigotLectern].hasBook
        Lectern(loc, dir, powered, book)

      // MELON_STEM
      case Material.MELON_STEM =>
        MelonStem(loc, s[MelonStemState], None)

      case Material.ATTACHED_MELON_STEM =>
        MelonStem(loc, s[MelonStemState], Some(dir))

      // NOTE_BLOCK
      case Material.NOTE_BLOCK =>
        NoteBlock(loc, v[NoteBlockVariant], s[NoteBlockState], powered)

      // PISTON
      case Material.PISTON | Material.STICKY_PISTON =>
        val extended = dataAs[SpigotPiston].isExtended
        Piston(loc, v[PistonVariant], dir, extended)

      case Material.MOVING_PISTON | Material.PISTON_HEAD =>
        throw new IllegalArgumentException("Technical block")

      // PUMPKIN
      case Material.PUMPKIN =>
        Pumpkin(loc, None, lit = false, carved = false)

      case Material.CARVED_PUMPKIN =>
        Pumpkin(loc, Some(dir), lit = false, carved = true)

      case Material.JACK_O_LANTERN =>
        Pumpkin(loc, Some(dir), lit = true, carved = true)

      // PUMPKIN_STEM
      case Material.PUMPKIN_STEM =>
        PumpkinStem(loc, s[PumpkinStemState], None)

      case Material.ATTACHED_PUMPKIN_STEM =>
        PumpkinStem(loc, s[PumpkinStemState], Some(dir))

      // REPEATER
      case Material.REPEATER =>
        val locked = dataAs[SpigotRepeater].isLocked
        Repeater(loc, s[RepeaterState], dir, powered, locked)

      // SEAGRASS
      case Material.SEAGRASS      => Seagrass(loc, BlockBisection.BOTTOM, tall = false)
      case Material.TALL_SEAGRASS => Seagrass(loc, bisection, tall = true)

      // SWEET_BERRY_BUSH
      case Material.SWEET_BERRY_BUSH =>
        SweetBerryBush(loc, s[SweetBerryState])

      // TNT
      case Material.TNT =>
        val unstable = dataAs[SpigotTNT].isUnstable
        TNT(loc, unstable)

      // TORCH
      case Material.TORCH      => Torch(loc, BlockFace.UP, wall = false)
      case Material.WALL_TORCH => Torch(loc, dir, wall = true)

      // TRIPWIRE
      case Material.TRIPWIRE => // TODO
        throw new IllegalArgumentException(s"${block.getType}")
      case Material.TRIPWIRE_HOOK => // TODO
        throw new IllegalArgumentException(s"${block.getType}")

      case m if m.isAir              => Air(loc, v[AirVariant])
      case m if m.isAnvil            => Anvil(loc, v[AnvilVariant], dir)
      case m if m.isBanner           => Banner(loc, color, Some(rotation), None)
      case m if m.isWallBanner       => Banner(loc, color, None, Some(dir))
      case m if m.isBrickBlock       => BrickBlock(loc, v[BrickBlockVariant])
      case m if m.isButton           => Button(loc, v[ButtonVariant], dir, attached, powered)
      case m if m.isCarpet           => Carpet(loc, color)
      case m if m.isCobblestone      => Cobblestone(loc, v[CobblestoneVariant])
      case m if m.isChest            => Chest(loc, v[ChestVariant], dir)
      case m if m.isConcrete         => Concrete(loc, color)
      case m if m.isConcretePowder   => ConcretePowder(loc, color)
      case m if m.isCoral            => Coral(loc, v[CoralVariant], flooded)
      case m if m.isCoralBlock       => CoralBlock(loc, v[CoralBlockVariant])
      case m if m.isCoralFan         => CoralFan(loc, v[CoralFanVariant], None, flooded)
      case m if m.isDirt             => Dirt(loc, v[DirtVariant])
      case m if m.isEndStone         => EndStone(loc, v[EndStoneVariant])
      case m if m.isFence            => Fence(loc, v[FenceVariant], extensions, flooded)
      case m if m.isFlower           => Flower(loc, v[FlowerVariant])
      case m if m.isFlowerPot        => FlowerPot(loc, v[FlowerPotVariant])
      case m if m.isGlass            => Glass(loc, Some(color))
      case m if m.isGlassPane        => GlassPane(loc, Some(color), extensions, flooded)
      case m if m.isGlazedTerracotta => GlazedTerracotta(loc, color)
      case m if m.isIce              => Ice(loc, v[IceVariant])
      case m if m.isInfestedBlock    => InfestedBlock(loc, v[InfestedBlockVariant])
      case m if m.isLeaves           => Leaves(loc, v[LeavesVariant])
      case m if m.isLog              => Log(loc, v[LogVariant], orientation)
      case m if m.isMobHead          => MobHead(loc, v[MobHeadVariant], None, Some(rotation))
      case m if m.isWallMobHead      => MobHead(loc, v[MobHeadVariant], Some(dir), None)
      case m if m.isMushroom         => Mushroom(loc, v[MushroomVariant])
      case m if m.isMushroomBlock    => MushroomBlock(loc, v[MushroomBlockVariant])
      case m if m.isPillar           => Pillar(loc, v[PillarVariant])
      case m if m.isPlanks           => Planks(loc, v[PlanksVariant])
      case m if m.isPlant            => Plant(loc, v[PlantVariant], bisection)
      case m if m.isPrismarine       => Prismarine(loc, v[PrismarineVariant])
      case m if m.isQuartzBlock      => QuartzBlock(loc, v[QuartzBlockVariant])
      case m if m.isRail             => Rail(loc, v[RailVariant], shapeAs[RailsShape], powered)
      case m if m.isSand             => Sand(loc, v[SandVariant])
      case m if m.isSandstone        => Sandstone(loc, v[SandstoneVariant])
      case m if m.isSapling          => Sapling(loc, v[SaplingVariant], s[SaplingState])
      case m if m.isShulkerBox       => ShulkerBox(loc, Some(color))
      case m if m.isSlab             => Slab(loc, v[SlabVariant], bisection)
      case m if m.isSponge           => Sponge(loc, v[SpongeVariant])
      case m if m.isStone            => Stone(loc, v[StoneVariant])
      case m if m.isStonite          => Stonite(loc, v[StoniteVariant])
      case m if m.isStructureBlock   => StructureBlock(loc, v[StructureBlockVariant])
      case m if m.isTerracotta       => Terracotta(loc, Some(color))
      case m if m.isWall             => Wall(loc, v[WallVariant], extensions)
      case m if m.isWood             => Wood(loc, v[WoodVariant])
      case m if m.isWool             => Wool(loc, color)

      // BED
      case m if m.isBed => // TODO use occupied flag
        val occupied = dataAs[SpigotBed].isOccupied
        Bed(loc, color, dir, bisection)

      // COMMAND_BLOCK
      case m if m.isCommandBlock =>
        val conditional = dataAs[SpigotCommandBlock].isConditional
        CommandBlock(loc, v[CommandBlockVariant], dir, conditional)

      // CORAL_WALL_FAN
      case m if m.isCoralWallFan =>
        CoralFan(loc, v[CoralFanVariant], Some(dir), flooded)

      // DOOR
      case m if m.isDoor =>
        val hinge = mapDoorHinge(dataAs[SpigotDoor].getHinge)
        Door(loc, v[DoorVariant], dir, hinge, bisection, open, powered)

      // FENCE_GATE
      case m if m.isFenceGate =>
        FenceGate(loc, v[FenceGateVariant], dir, open, powered, wall = false)

      // PRESSURE_PLATE
      case m if m.isPressurePlate =>
        PressurePlate(loc, v[PressurePlateVariant], powered)

      // SIGN
      case m if m.isSign =>
        val sign = spigotState.asInstanceOf[SpigotSign]
        val lines = sign.getLines.toList // TODO keep as array? seem to be immutable
        val editable = sign.isEditable
        Sign(loc, v[SignVariant], Some(dir), None, flooded, lines, editable)

      case m if m.isWallSign =>
        val sign = spigotState.asInstanceOf[SpigotSign]
        val lines = sign.getLines.toList
        val editable = sign.isEditable
        Sign(loc, v[SignVariant], None, Some(rotation), flooded, lines, editable)

      // STAIRS
      case m if m.isStairs =>
        Stairs(loc, v[StairsVariant], shapeAs[StairsShape], dir, bisection, flooded)

      // TRAPDOOR
      case m if m.isTrapdoor =>
        Trapdoor(loc, v[TrapdoorVariant], dir, bisection, powered, flooded, open)

      // WEIGHTED_PRESSURE_PLATE
      case m if m.isWeightedPressurePlate =>
        val _variant = v[WeightedPressurePlateVariant]
        val _state = s[WeightedPressurePlateState]
        WeightedPressurePlate(loc, _variant, _state)
    }
  }

  def map(block: Block): SpigotBlockData = {
    // Create new block data object
    val material = block match {
      case it: ColoredBlock     => colorMapper.map(it)
      case it: VariableBlock[_] => variantMapper.map(it)

      case _: Barrel           => Material.BARREL
      case _: Barrier          => Material.BARRIER
      case _: Beacon           => Material.BEACON
      case _: Bedrock          => Material.BEDROCK
      case _: Beetroots        => Material.BEETROOTS
      case _: Bell             => Material.BELL
      case _: BlastFurnace     => Material.BLAST_FURNACE
      case _: BoneBlock        => Material.BONE_BLOCK
      case _: Bookshelf        => Material.BOOKSHELF
      case _: BrewingStand     => Material.BREWING_STAND
      case _: BubbleColumn     => Material.BUBBLE_COLUMN
      case _: Cactus           => Material.CACTUS
      case _: Cake             => Material.CAKE
      case _: Campfire         => Material.CAMPFIRE
      case _: Carrots          => Material.CARROTS
      case _: CartographyTable => Material.CARTOGRAPHY_TABLE
      case _: Cauldron         => Material.CAULDRON
      case _: ChorusFlower     => Material.CHORUS_FLOWER
      case _: ChorusPlant      => Material.CHORUS_PLANT
      case _: Clay             => Material.CLAY
      case _: CoalBlock        => Material.COAL_BLOCK
      case _: CoalOre          => Material.COAL_ORE
      case _: Cobweb           => Material.COBWEB
      case _: CocoaPod         => Material.COCOA
      case _: Composter        => Material.COMPOSTER
      case _: Conduit          => Material.CONDUIT
      case _: CraftingTable    => Material.CRAFTING_TABLE
      case _: DaylightDetector => Material.DAYLIGHT_DETECTOR
      case _: DeadBush         => Material.DEAD_BUSH
      case _: DiamondBlock     => Material.DIAMOND_BLOCK
      case _: DiamondOre       => Material.DIAMOND_ORE
      case _: Dirt             => Material.DIRT
      case _: Dispenser        => Material.DISPENSER
      case _: DragonEgg        => Material.DRAGON_EGG
      case _: DriedKelp        => Material.DRIED_KELP_BLOCK
      case _: Dropper          => Material.DROPPER
      case _: EmeraldBlock     => Material.EMERALD_BLOCK
      case _: EmeraldOre       => Material.EMERALD_ORE
      case _: EnchantingTable  => Material.ENCHANTING_TABLE
      case _: EndGateway       => Material.END_GATEWAY
      case _: EndPortal        => Material.END_PORTAL
      case _: EndPortalFrame   => Material.END_PORTAL_FRAME
      case _: EndRod           => Material.END_ROD
      case _: Farmland         => Material.FARMLAND
      case _: Fire             => Material.FIRE
      case _: FletchingTable   => Material.FLETCHING_TABLE
      case _: Furnace          => Material.FURNACE
      case _: Glowstone        => Material.GLOWSTONE
      case _: GoldBlock        => Material.GOLD_BLOCK
      case _: GoldOre          => Material.GOLD_ORE
      case _: GrassBlock       => Material.GRASS_BLOCK
      case _: GrassPath        => Material.GRASS_PATH
      case _: Gravel           => Material.GRAVEL
      case _: Grindstone       => Material.GRINDSTONE
      case _: HayBale          => Material.HAY_BLOCK
      case _: Hopper           => Material.HOPPER
      case _: IronBlock        => Material.IRON_BLOCK
      case _: IronBars         => Material.IRON_BARS
      case _: IronOre          => Material.IRON_ORE
      case _: JigsawBlock      => Material.JIGSAW
      case _: Jukebox          => Material.JUKEBOX
      case _: Ladder           => Material.LADDER
      case _: Lantern          => Material.LANTERN
      case _: LapisBlock       => Material.LAPIS_BLOCK
      case _: LapisOre         => Material.LAPIS_ORE
      case _: Lava             => Material.LAVA
      case _: Lectern          => Material.LECTERN
      case _: Lever            => Material.LEVER
      case _: LilyPad          => Material.LILY_PAD
      case _: Loom             => Material.LOOM
      case _: MagmaBlock       => Material.MAGMA_BLOCK
      case _: Melon            => Material.MELON
      case _: MelonStem        => Material.MELON_STEM
      case _: Mycelium         => Material.MYCELIUM
      case _: NetherPortal     => Material.NETHER_PORTAL
      case _: Netherrack       => Material.NETHERRACK
      case _: NetherWarts      => Material.NETHER_WART
      case _: NetherWartBlock  => Material.NETHER_WART_BLOCK
      case _: Observer         => Material.OBSERVER
      case _: Obsidian         => Material.OBSIDIAN
      case _: Podzol           => Material.PODZOL
      case _: Potatoes         => Material.POTATOES
      case _: Pumpkin          => Material.PUMPKIN
      case _: PumpkinStem      => Material.PUMPKIN_STEM
      case _: PurpurBlock      => Material.PURPUR_BLOCK
      case _: QuartzOre        => Material.NETHER_QUARTZ_ORE
      case _: RedstoneBlock    => Material.REDSTONE_BLOCK
      case _: RedstoneLamp     => Material.REDSTONE_LAMP
      case _: RedstoneOre      => Material.REDSTONE_ORE
      case _: RedstoneWire     => Material.REDSTONE_WIRE
      case _: Repeater         => Material.REPEATER
      case _: Scaffolding      => Material.SCAFFOLDING
      case _: SeaLantern       => Material.SEA_LANTERN
      case _: SeaPickle        => Material.SEA_PICKLE
      case _: SlimeBlock       => Material.SLIME_BLOCK
      case _: SmithingTable    => Material.SMITHING_TABLE
      case _: Smoker           => Material.SMOKER
      case _: Snow             => Material.SNOW
      case _: SnowBlock        => Material.SNOW_BLOCK
      case _: Spawner          => Material.SPAWNER
      case _: Stonecutter      => Material.STONECUTTER
      case _: SugarCane        => Material.SUGAR_CANE
      case _: SweetBerryBush   => Material.SWEET_BERRY_BUSH
      case _: TNT              => Material.TNT
      case _: Torch            => Material.TORCH
      case _: TurtleEgg        => Material.TURTLE_EGG
      case _: Vine             => Material.VINE
      case _: Water            => Material.WATER
      case _: Wheat            => Material.WHEAT

      case it: Kelp =>
        if (it.state == KelpState.AGE_25) Material.KELP_PLANT
        else Material.KELP

      case it: RedstoneTorch =>
        if (it.direction.isEmpty) Material.REDSTONE_TORCH
        else Material.REDSTONE_WALL_TORCH

      case it: Seagrass =>
        if (it.tall) Material.TALL_SEAGRASS
        else Material.SEAGRASS
    }

    val data = Bukkit.createBlockData(material)

    // Lazily compute generic block data
    def blockAs[T <: Block]: T = block.asInstanceOf[T]

    lazy val flooded = blockAs[FloodableBlock].flooded
    lazy val lit = blockAs[LightableBlock].lit
    lazy val open = blockAs[OpenableBlock].open
    lazy val powered = blockAs[PowerableBlock].powered
    lazy val snowy = blockAs[SnowableBlock].snowy

    lazy val attached = {
      val attachment = blockAs[AttachedBlock].attachment
      attachmentMapper.map(attachment)
    }

    lazy val bisection = {
      val bisection = blockAs[BisectedBlock].section
      bisectionMapper.map(bisection)
    }

    lazy val direction = block match {
      case it: DirectedBlock   => faceMapper.map(it.direction)
      case it: DirectableBlock => faceMapper.map(it.direction.get)
    }

    lazy val extensions = {
      val extensions = blockAs[ExtendableBlock].extensions
      extensionMapper.map(extensions)
    }

    lazy val orientation = {
      val orientation = blockAs[OrientedBlock].orientation
      orientationMapper.map(orientation)
    }

    lazy val rotation = {
      val rotation = blockAs[RotatableBlock].rotation.orNull
      rotationMapper.map(rotation)
    }

    // Set generic block data
    data match { case it: Bisected    => it.setHalf(bisection) }
    data match { case it: Directional => it.setFacing(direction) }
    data match { case it: Lightable   => it.setLit(lit) }
    data match { case it: Openable    => it.setOpen(open) }
    data match { case it: Orientable  => it.setAxis(orientation) }
    data match { case it: Powerable   => it.setPowered(powered) }
    data match { case it: Rotatable   => it.setRotation(rotation) }
    data match { case it: Snowable    => it.setSnowy(snowy) }
    data match { case it: Switch      => it.setFace(attached) }
    data match { case it: Waterlogged => it.setWaterlogged(flooded) }

    data match {
      case it: MultipleFacing =>
        it.getAllowedFaces.forEach(face => it.setFace(face, false)) // TODO check if this is
        // necessary on fresh BlockData objects
        extensions.forEach(face => it.setFace(face, true))
    }

    // Set specific block data
    def dataAs[T <: SpigotBlockData]: T = data.asInstanceOf[T]

    block match { case it: StatefulBlock[_] => stateMapper.map(it, data) }
    block match { case it: VariableBlock[_] => variantMapper.map(it, data) }

    block match {
      case it: BubbleColumn   => dataAs[SpigotBubbleColumn].setDrag(it.drag)
      case it: Campfire       => dataAs[SpigotCampfire].setSignalFire(it.signal)
      case it: EndPortalFrame => dataAs[SpigotEndPortalFrame].setEye(it.eye)
      case it: Hopper         => dataAs[SpigotHopper].setEnabled(it.powered)
      case it: Piston         => dataAs[SpigotPiston].setExtended(it.extended)
      case it: Repeater       => dataAs[SpigotRepeater].setLocked(it.locked)
      case it: TNT            => dataAs[SpigotTNT].setUnstable(it.unstable)

      case it: Bamboo =>
        val age = if (it.thick) 1 else 0
        dataAs[SpigotBamboo].setAge(age)

      case it: CommandBlock =>
        dataAs[SpigotCommandBlock].setConditional(it.conditional)

      case it: Lantern =>
        data.asInstanceOf[SpigotLantern].setHanging(it.hanging)
    }

    // Return block data object
    data
  }

  def map(block: Block, spigotState: SpigotBlockState): Unit = {
    def stateAs[T <: SpigotBlockState]: T = spigotState.asInstanceOf[T]

    block match {
      case Sign(_, _, _, _, _, lines, editable) =>
        val sign = stateAs[SpigotSign]
        lines.zipWithIndex foreach { case (line, i) => sign.setLine(i, line) }
        sign.setEditable(editable)
    }
  }

  def mapDoorHinge(hinge: SpigotDoorHinge): BlockHinge = hinge match {
    case SpigotDoorHinge.LEFT  => BlockHinge.LEFT
    case SpigotDoorHinge.RIGHT => BlockHinge.RIGHT
  }

  def mapDoorHinge(hinge: BlockHinge): SpigotDoorHinge = hinge match {
    case BlockHinge.LEFT  => SpigotDoorHinge.LEFT
    case BlockHinge.RIGHT => SpigotDoorHinge.RIGHT
  }
}
